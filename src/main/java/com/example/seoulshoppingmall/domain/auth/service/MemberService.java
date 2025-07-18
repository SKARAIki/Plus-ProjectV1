package com.example.seoulshoppingmall.domain.auth.service;

import com.example.seoulshoppingmall.common.config.JwtTokenProvider;
import com.example.seoulshoppingmall.common.config.PasswordEncoder;
import com.example.seoulshoppingmall.domain.auth.Exception.DuplicateEmailException;
import com.example.seoulshoppingmall.domain.auth.Exception.EmailNotFoundException;
import com.example.seoulshoppingmall.domain.auth.Exception.InvalidPasswordException;
import com.example.seoulshoppingmall.domain.auth.Exception.InvalidPasswordFormatException;
import com.example.seoulshoppingmall.domain.auth.dto.request.LoginRequest;
import com.example.seoulshoppingmall.domain.auth.dto.request.MemberCreateRequest;
import com.example.seoulshoppingmall.domain.auth.dto.response.MemberCreateResponse;
import com.example.seoulshoppingmall.domain.auth.dto.response.LoginTokenResponse;
import com.example.seoulshoppingmall.domain.auth.entity.Member;
import com.example.seoulshoppingmall.domain.auth.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    //속성
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    //생성자
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //기능
    /**
     * 회원 가입 서비스
     */
    @Transactional
    public MemberCreateResponse createMember(MemberCreateRequest requestDto) {
        //1.데이터준비
        String email = requestDto.getEmail();
        String memberName = requestDto.getMemberName();
        String password = requestDto.getPassword();

        //2.검증로직(이메일 중복 체크, 비밀번호 검증)x
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).+$")) {
            throw new InvalidPasswordFormatException("비밀번호는 최소8자, 대소문자, 숫자, 특수문자를 포함해야 합니다.");
        }

        //3.비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        //3.엔티티 준비
        Member member = new Member(email, memberName, encodedPassword);

        //4.저장
        Member savedMember = memberRepository.save(member);
        Long savedMemberId = savedMember.getId();

        //5.responseDto 만들기
        MemberCreateResponse responseDto = new MemberCreateResponse(savedMemberId);
        return responseDto;
    }

    /**
     * 로그인 서비스
     */
    @Transactional
    public LoginTokenResponse login(LoginRequest requestDto) {
        //1.데이터 준비
        String userEmail = requestDto.getEmail();
        String password = requestDto.getPassword();

        //2.검증로직(이메일 확인)
        Member loginMember = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EmailNotFoundException("존재하지 않는 이메일입니다."));

        //3.비밀번호 확인 후, 일치 시 토큰 생성 및 반환
        if (!passwordEncoder.matches(password, loginMember.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        String token = jwtTokenProvider.createToken(loginMember);
        LoginTokenResponse tokenResponse = new LoginTokenResponse(token);
        return tokenResponse;
    }
}
