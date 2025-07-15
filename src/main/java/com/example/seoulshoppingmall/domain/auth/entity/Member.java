package com.example.seoulshoppingmall.domain.auth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;


@Entity
@Table(name = "members")
public class Member {
    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String memberName;


    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    protected Member() {}

    /**
     *MemberCreateRequest 정보를 꺼내와서 entity를 만들 때 사용합니다.
     */
    public Member(String email, String memberName, String password) {
        this.email = email;
        this.memberName = memberName;
        this.password = password;
    }

    /**
     * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
     */


    /**
     * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
     */


    /**
     * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
     */

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMemberName() {
        return memberName;
    }

}
