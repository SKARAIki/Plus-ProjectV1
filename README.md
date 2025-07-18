## 1.팀원소개

**팀장**: 이예진 → Open API 수집, 시연영상 제작 

팀원: 김진권→ QueryDSL 을 사용한 커서 기반 페이지네이션 및 필터, READ ME 작성

팀원: 김광수→ 업체 리스트 조회 중 필터 기능(전체평가, 업소상태 필터 적용), csv를 database에 입력하는 코드 만들기, READ ME 작성

팀원: 안상아→회원가입, 로그인, 트러블슈팅과 느낀점 문서화

팀원: 이승은→ Pageable 기반 업체 리스트 조회, 발표자료 준비 및 발표

팀원: 이형준→ csv를 database에 입력하는 코드 만들기, READ ME 작성

## 2.프로젝트 개요

**개발 기간: 2025.07.07~2025.07.15**

TrendSixTown 프로젝트는 여섯 명이 모여 서울시 인터넷 쇼핑몰 데이터를 기반으로 
소비 트렌드를 시각화하고 분석하는 데이터 리서치 프로젝트 입니다.

“여섯 명이 만든 하나의 도시 지도” 라는 의의를 토대로 함께 고민하고 
해결합니다. 서울 속 소비 흐름의 지형도를 떠올려,

어디서 어떤 정보를 원할까?

어떤 자치구가 특정 카테고리에 강할까?

등의 질문들에서 출발해, 
소비자가 궁금해 하는 정보를 직관적으로 보여 주겠다는 목표를 
한 문장으로 압축한 명칭입니다.

## 3.사용기술

JAVA, Spring, JPA

JWT, Mysql, QueryDSL, OpenCsv

Github

## 4.서비스 플로우

1.회원가입& 로그인

- 사용자는 이메일, 이름, 비밀번호를 입력해 회원가입을 진행합니다.
- 비밀번호는 BCRYPT 방식으로 암호화되면, 보안 정책(대소분자+특수문자 포함, 8자 이상)을 따릅니다.
- 가입한 계정으로 로그인 시, JWT토큰이 발급되고, 이후 요청에서 해당 토큰을 통해 인증/인가 처리를 수행합니다.
1. 데이터 수집
- 서울 시 OpenAPI 연동을 통해 최신 쇼핑몰 정보를 수집합니다.
    - 수집 시 지정된 범위만큼 데이터를 파싱하고, 유효한 데이터만 필터링 후 DB에 저장합니다.
    - 키워드 기반 저장 결과 메시지를 함께 응답합니다.
- CSV 업로드 기능도 제공하여 외부 파일 형태의 데이터를 서버로 전송 후 DB에 반영 할 수 있습니다.
1. 쇼핑몰 리스트 조회
- 사용자는 쇼핑몰 리스트를 조회할 수 있으며, 다음과 같은 기능이 적용됩니다.
    - 전체평가, 업소상태 필터: 동시 또는 개별 적용 가능
    - 정렬 기준:  monitoringDate 기준 최신순
    - 페이징 방식: QueryDSL 기반 커서 페이징(monitoringDate+id 복합 커서)

## 5.아키텍처

<img width="2189" height="1210" alt="image" src="https://github.com/user-attachments/assets/dc1e0b25-01e4-4616-a7d4-a2a44b4a61f8" />


## 6.ERD

<img width="878" height="1208" alt="Untitled (2) (1)" src="https://github.com/user-attachments/assets/9bdf4c68-33e1-4bdb-a2a6-3a1b2258271f" />


## 7.[API 명세](https://www.notion.so/API-2292dc3ef51480c99979dd9d1ca2bb15?pvs=21)

## 8.트러블 슈팅

### 개인 트러블 슈팅

### 김광수님

### 문제 1

### 문제 상황

<img width="968" height="472" alt="26만개_6분5초" src="https://github.com/user-attachments/assets/97cac8b0-9f2e-4c9f-a3f9-b4a26c9d7466" />


서울시에서 제공한 csv 파일을 기준으로 추가로 13만 개의 더미 데이터를 만들어 총 27만개의 row를 읽고 저장하는 테스트를 진행 하였을 때

각 1개씩 읽고 저장하는 기존의 로직에선 약 6분정도의 시간이 소요되었습니다.

---

### 고민 및 결정

사용자가 해당 서비스를 이용하는 목적에 따라 6분이라는 시간이 길 수도 있고, 짧을 수도 있지만 “**빨리빨리**”의 나라 한국에선 일단 소요 시간이 짧으면 짧을 수록 다다익선이기 때문에 어떻게 하면 소요 시간을 줄여볼 수 있을까 고민 했습니다.

JdbcTemplate의 기능을 사용하면 직접 sql문을 insert하니 jpa의 영속성 컨텍스트를 거치지 않아 entityManager의 개입이 불필요해지면서 그만큼 검사 비용(시간)이 줄어들지 않을까 하여서 코드를 바꾸어보았습니다.

---

### 개선 내용

원래는 아래의 코드만 존재하였습니다.

```jsx
 @Transactional
    public ApiResponse<String> csvFileUploadProcess(MultipartFile csvFile) {
     List<Mall> mallInfoList = mallInfoCsvParse.stream()
                    .map(mallInfoCsv -> mallInfoCsv.toEntity())
                    .collect(Collectors.toList());

            mallRepository.saveAll(mallInfoList);
            entityManager.flush();
            entityManager.clear();
            
            ApiResponse<String> success
                    = ApiResponse.success(HttpStatus.CREATED, "CSV파일이 DB에 업로드 되었습니다", "");
            return success;
   }
```

아래는 jdbc템플릿을 이용하여 bulkInsert 추가 하였습니다.

```jsx
@Repository
public class MallCsvCustomRepositoryImpl implements MallCsvRepositoryCustom {
    private final JdbcTemplate jdbcTemplate;

    public MallCsvCustomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bulkSave(List<Mall> malls) {
        batchInsert(100, malls); // 100개씩 묶어서 저장
    } 
 public void batchInsert(int batchSize, List<Mall> malls) {
        String sql = " insert into ~~";
        
         jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
             Mall mall = malls.get(i);
                ps.setString(1, mall.getBusinessName());
                ps.setString(2, mall.getMallName());
				                .
                                .
                ps.setString(32, mall.getMonitoringDate());
            }

            @Override
            public int getBatchSize() {
                return malls.size();
            }
        });

    }
}
```

---

### 효과

<img width="786" height="426" alt="jdbc_bulkInsert_1분" src="https://github.com/user-attachments/assets/42235a63-e345-4a17-9e06-d3d4fc99fe1a" />


같은 파일을 업로드 하였을 때 **6분 5초** 정도 걸리는 시간이 **약 1분** 정도로 **소요시간이 1/6로 줄어드는 효과**가 발생하였습니다. 

게임개발자, BE개발자, FE개발자 모든 개발자들은 프로그램을 만드는 생산자임과 동시에 프로그램을 소비하는 소비자가 될 수 있습니다. 그래서 무언가를 만들 때는 사용자가 우리 프로그램을 어떻게 사용하고 어떤 경험을 가져갈지 이에 대해서 심도 있게 고민하고, 사용자들이 우리 프로그램을 사용하였을 때 긍정적인 경험을 가져갈 수 있게 기획하고 문제를 해결해야 한다고 생각합니다. 

해서 개발을 할 때 단순히 작동만 하면 되는 프로그램이 아닌, “내가 이 프로그램을 사용했을 때” 라는 생각으로 만들고자 노력하겠습니다.

### 김진권님

### 문제1

### 문제 상황

QueryDSL 의존성을 추가하고 Q클래스를 사용하려 했지만, **IDE에서 QMall 클래스가 인식되지 않는 문제**가 발생했습니다.

빌드 디렉토리 내에는 QMall 클래스가 생성되어 있었지만, **IDE에서는 이를 인식하지 못해 import조차 되지 않았습니다.**

---

### 고민 및 결정

처음에는 의존성 설정이 잘못된 줄 알았지만,

```
build/generated/sources/annotationProcessor/java/main
```

경로에 Q 클래스들이 정상적으로 생성되어 있는 것을 확인했습니다.

문제의 원인은 **생성된 경로가 IDE에서 소스 디렉토리로 자동 인식되지 않기 때문**이라는 점이었습니다.

그래서 **IDE가 Q 클래스를 소스 코드로 인식하도록 별도의 설정을 추가하기로 결정**했습니다.

---

### 개선 내용

`build.gradle`에 아래 설정을 추가했습니다:

```java
def querydslDir = "$buildDir/generated/querydsl"

tasks.withType(JavaCompile).configureEach {
    options.annotationProcessorGeneratedSourcesDirectory = file(querydslDir)
}

sourceSets {
    main.java.srcDirs += querydslDir
}
```

annotationProcessorGeneratedSourcesDirectory는  Q 클래스를 `build/generated/querydsl`에 생성하도록 지정하는 역할

sourceSets.main.java.srcDirs += querydslDir는 해당 디렉토리를 소스 디렉토리로 등록하여 IDE가 인식하도록 하는 역할

---

### 효과

- `build/generated/querydsl` 경로에 QMall 클래스가 정상 생성됨을 확인했습니다.
- 이후 IDE에서도 QMall을 인식하고, 레포지토리 클래스에서 `import com.querydsl...QMall`이 정상적으로 동작했습니다.
---


### 안상아님

### 문제1

### **‼️**문제 상황

- Authorization 헤더에서 JWT 토큰을 추출할 때,  직접 null 체크와 문자열 분리 로직을 처리

```java
public String extractToken(String header) {
    if (header != null && header.startsWith(BEARER_PREFIX)) {
        return header.substring(BEARER_PREFIX.length());
    }
    return null;
}
```

---

### ✅  고민 및 결정

- 이 경우 ﻿정상 작동은 하지만, 토큰이 없거나 "Bearer " 접두사가 없는 경우 null을 리턴하게 되고, 이후 로직에서 이를 고려하지 않으면 **NullPointerException이 발생할 가능성**이 있음

---

### 개선 내용

Optional<>을 반환하도록 리팩토링 하여, null 가능성을 명시하고 안정성을 향상 시킨다.

```java
public Optional<String> extracToken(String header) {
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            return Optional.of(header.substring(BEARER_PREFIX.length()));
        }
        return Optional.empty();
    }
```

---

### ✅  효과

- null 가능성 명시하여, 호출 측에서 값을 안전하게 처리하도록 유도할 수 있음
- null을 직접 다루지 않아도 되므로, NullPointerException 방지할 수 있으며, 가독성과 안정성도 향상됨
- 필터 등 인증처리 흐름에서도 조건 분기 처리를 명확하고 일관되게 구성 가능

---

### 문제2

### **‼️**문제 상황

- JWT 토큰에서 사용자 정보를 추출하는 메서드(extractId, extractEmail, extractMemberName)마다 토큰 파싱 로직이 중복되어 있었음(파싱 방식 변경 시 모든 메서드를 수정해야 하는 등 유지보수가 어렵고 비효율적)

```java

    /**
     * 헤더에서 "Bearer <토큰>" 형식에서 토큰만 추출
     */
    public String extractToken(String header) {
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length()); // "Bearer " 제거
        }
        return null;
    }

    /**
     * 토큰에서 사용자 아이디(id)를 추출
     */
    public long extractId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key) //비밀키 설정
                .build() //파서 빌더 빌드
                .parseClaimsJws(token) //토큰 파싱 및 검증
                .getBody(); //파싱 결과에서 실제 내용Body

        return Long.parseLong(claims.getSubject());
    }

    /**
     * 토큰에서 사용자 이름(MemberName)을 추출
     */
    public String extractMemberName(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // setSubject로 저장했던 값 꺼냄
    }

    /**
     * 토큰에서 이메일 추출
     */
    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getBody();

        return claims.get("email", String.class);
    }
```

---

### ✅ 고민 및 결정

- 파싱 로직이 여러 메서드에 흩어져 있어 일관성이 부족
- key 설정이나 파서 구성 방식이 변경될 경우 **여러 메서드를 반복 수정해야 하는 비효율성** 존재
- 또한 Claims를 재사용하려는 시도는 **멀티스레드 환경에서 공유 객체로 인한 동시성 문제** 발생 위험이 있음

      →매번 새로운 Claims 객체를 안전하게 생성하되, **공통 파싱 메서드로 중복을 제거하는 방식**을 선택

---

### ✅ 개선 내용

- 공통 파싱 메서드 parseClaims(token) 작성
- 각 멤버 정보 추출 메서드는 parseClaims 호출 후 필요한 정보만 추출하도록 변경
- 매번 새로운 Claims 객체 생성 → 요청 간 데이터 독립성 보장

```java
    /**
     *token에서 멤버 정보 파싱
     */
    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    /**
     * 파싱 객체에서 사용자 아이디(id)를 추출
     */
    public long extractId(String token) {
         Claims claims = parseClaims(token);
        return Long.parseLong(claims.getSubject());
    }

    /**
     * 파싱 객체에서 사용자 이름(MemberName)을 추출
     */
    public String extractMemberName(String token) {
        Claims claims = parseClaims(token);
        return claims.get("memberName", String.class); //  저장했던 값 꺼냄
    }

    /**
     * 파싱 객체에서 이메일 추출
     */
    public String extractEmail(String token) {
        Claims claims = parseClaims(token);
        return claims.get("email", String.class);
    }
```

---

### 효과

- 코드 중복 제거로 가독성 및 유지보수성 향상
- 변경 발생 시 단일 책임 메서드만 수정하면 되어 유지보수가 쉬움
- 스레드 안전성 확보로 예기치 않은 에러 방지
- 파싱 중복 제거는 구조적으로 서비스 확장 시 성능 최적화 기반이 될 수 있음(CPU 자원 효율성과 응답 시간 단축 가능성의 장점이 있음)

### 이승은님

### 문제1

### 문제 상황

**Spring Data JPA를 활용하여 특정 필터 조건으로 업체 데이터를 조회하는 기능을 구현하던 중, 다음과 같이 쿼리 메소드를 작성했습니다.**
`List<Mall> findTop10OverallRating(Integer overallRating, Pageable pageable);`

**해당 메소드는 overallRating 값으로 정렬된 상위 10개의 업체를 조회하려는 의도였지만, IntelliJ에서 해당 메소드를 인식하지 못하고 실행 시점에 예외가 발생했습니다.**

---

### 고민 및 결정

Spring Data JPA는 메소드 이름으로부터 자동으로 쿼리를 생성하는데, 

이때는 특정 규칙을 준수해야 합니다.

알아본 결과 By 라는 키워드를 빠뜨려 메소드가 쿼리로 인식되지 않았습니다.

올바른 메소드 명명법은 `findTop10ByOverallRating`이며,

findTop10까지가 반환 개수 한정자이고 

By 이후부터 조건 필드를 나열해야 메소드가 제대로 작동한다는걸 알게됐습니다.

그래서 쿼리 메소드 이름을 바꿔야겠다고 결정했습니다.

---

### 개선 내용

 Spring Data JPA 공식 문서 및 베이직 세션을 참고 하여,

`findTop10OverallRating` 에서 `findTop10ByOverallRating` 로 변경했습니다.

---

### 효과

수정 후 다시 실행해보니 정상적으로 작동하며, 쿼리도 올바르게 생성되었습니다.

쿼리 메소드 규칙을 배웠지만 복습을 소홀히 하여 실수를 한 것 같아 반성하게 된 계기가 되었습니다.

작은 실수가 쿼리 생성에 상당한 영향을 미칠 수 있다는 상황을 직접 경험함으로써 

한층 더 신중한 개발을 할 수 있게 됐다고 생각합니다.

### 이예진님

### 문제1: mainProducts의 슬래시(/) 구분 문제로 인한 필터링

### 문제 상황

- OpenAPI에서 수집한 mainProducts 항목이 슬래시(/)로 구분되어 있었음
    
    예시)
    
    `"mainProducts": "화장품/향수"`
    
- 사용자가 예를 들어 화장품 이라는 키워드로 필터링 할 경우 전체 문자열과 일치하지 않아 오류발생
    
    ---
    

### 고민 및 결정

슬래시(/) 로 분리된 각 품목을 개별로 검사해야하며 일부 항목은 단일 품목만 있는 경우도 있어 예외처리 필요

---

### 개선 내용

filterByKeyword() 메서드 리펙토링함

```java
public List<MallOpenApiDto> filterByKeyword(List<MallOpenApiDto> mallOpenapiDto, String keyword) {

        // keyword가 없거나 빈 문자열일 경우("") 필터링 안하고 전체 반환
        if (keyword == null || keyword.isBlank()) return mallOpenapiDto;

        // 필터링된거 반환
        return mallOpenapiDto.stream()
                // 조건
                .filter(dto -> {
                    // mainProducts가 null이면 필터 대상에서 제외하고
                    if (dto.getMainProducts() == null) return false;

                    // 화장품/향수 같은 슬래쉬로 구분된 경우에 개별로 나눔
                    String[] parts = dto.getMainProducts().split("/");

                    // 각 품목을 trim 으로 공백제거
                    return Arrays.stream(parts)
                            .map(String::trim)
                            .anyMatch(p -> p.contains(keyword.trim()));
                })
                .toList();
    }
```

---

### 효과

- “화장품/향수” → “화장품”, “향수”로 분리하여 정확한 필터링 가능
- 단일 항목 등 모든 경우 필터링 가능
  
### 이형준님

### 문제1

### 문제 상황

서울쇼핑몰현황 CSV를 리스트로 관리하여 1000개씩 읽고 저장하는 로직을 구현하여도 1분 시간소요

---

### 고민 및 결정

유저가 대기할 수 있는시간은 1분미만 그 후는 사이트를 닫거나 어려움을 겪는경우가 대다수입니다.

그부분을 고려하여 1분보다 더 최적화 할 수 있는방법은 없을까? 만약 있다면 어떤 방법으로 가능할까?

고민을하고 방선희 튜터님께 피드백을 요청하였습니다 선희 튜터님께서는 멀티쓰레드를 추천해주셨고

그걸 활용하면 하나의 쓰레드에서 13만개를 읽는것을 나눠서 만약 2000개씩 읽는다면 2000개씩 쓰레드가 나눠가지고 각자 수행 후 저장할것이다 이야기주셔서 그부분을 구현하기로하였습니다.

---

### 개선 내용

아래 코드는 멀티쓰레드 구현전 코드입니다.

```jsx
 @Transactional
 public ShoppingMall getShoppingMall(String line) {

        //ShoppingMall타입으로 리스트 선언
        //엔티티 타입의 값만 반영 가능
        List<ShoppingMall> shoppingMallList = new ArrayList<>();
        //라인 변수 선언
        String line;
        
        //한번에 읽을 데이터 줄 수 설정
        private static final int BATCH_SIZE  = 2000;

        //받은 파일이 내용이 비워져 있으면 예외처리
        if (file.isEmpty()) {
            throw new FindException();
        }

        //파일을 한줄 씩 읽기 가능하도록 설정
        //받은 CSV 파일 바이트 형태로 받은 후 해당 파일을 문자열로 변환 그 후 버퍼링을 통해 효율적으로 읽도록 설정
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

        //지금 읽는 줄이 첫번째 줄인지 여부 판단하는 boolean플래그 변수 활성화
        boolean isFirst = true;
        //가져온 파일 이름 가져오기
        String fileName = file.getName();

        //첫번째 읽은 값 받은 후 continue로 건너뛴 후 플래그 변수 비활성화
        //받은 문자열 파일 readLine을 통해
            while ((line = reader.readLine()) != null) {
               if (isFirst) {
                    isFirst = false;
                    continue;
                }

        //필드 내 값이 없어도 한 공간으로 판단해서 리스트에 반영 설정 -1이
        //없으면 값 있는 필드만 리스트에 반영
        String[] fields = line.split(",", -1);
        String[] cleanedFields = Arrays.stream(fields)
                .map(s -> s.replace("\"",""))
                .toArray(String[]::new);

        //열이 30개 미만이면 아래 로직 건너뜀 이상이면 진행
        if (fields.length < 33)
            //trim(공백제거)
            //엔티티에 저장된 값 변수에 저장
            return new ShoppingMall(
                    //문자열을 Long 타입으로 변환 & 문자열을 int 타입으로 변환
                    //Long.parseLong(fields[0]),Integer.getInteger(fields[0])
                    cleanedFields[22],cleanedFields[11],cleanedFields[0],cleanedFields[9],
                    cleanedFields[6],cleanedFields[17],cleanedFields[13],cleanedFields[25],
                    cleanedFields[8],cleanedFields[28],cleanedFields[26],cleanedFields[2],
                    cleanedFields[7],cleanedFields[16],cleanedFields[1],cleanedFields[18],cleanedFields[29],
                    cleanedFields[31],cleanedFields[4],cleanedFields[10],
                    cleanedFields[13],cleanedFields[19],cleanedFields[3],cleanedFields[21],cleanedFields[15],
                    cleanedFields[23],cleanedFields[27],cleanedFields[5],cleanedFields[24],cleanedFields[30],
                    cleanedFields[20],cleanedFields[14]
            );

        //엔티티에 저장한 데이터 리스트에 반영
        shoppingMallList.add(shoppingMall);

        //리스트 사이즈가 rageSize보다 크거나 같을때 저장/같지 않거나 작을때 저장 종료
                if(shoppingMallList.size() >= BATCH_SIZE){
                    //데이터베이스에 리스트 내 데이터 저장
                   shoppingMallCsvRepository.saveAll(shoppingMallList);
                    shoppingMallList.clear();
          }

        //리스트에 값이 존재할때 저장 진행/값이 없을때 저장 종료
            if(!shoppingMallList.isEmpty()){
                //남은 데이터 마무리로 저장
                shoppingMallCsvRepository.saveAll(shoppingMallList);
                //모든 요소만 제거(주의 : 내부에 모든 값이 제거되어 사이즈가 0이 됨 isEmpty도 true됨
                //리스트가 null이 되는것은 아님 빈 공간이라도 있으니
                shoppingMallList.clear();
            }
            
    }
}
```

아래는 멀티 쓰레드를 구현한 코드입니다.

```jsx
@Service
@RequiredArgsConstructor
@Transactional
public class ShoppingMallCsvService {

    private final ShoppingMallCsvRepository shoppingMallCsvRepository;
    //데이터를 담을 수 있는 최대 사이즈 선언(CSV 줄 수)\
    //하나의  쓰레드가 처리할 CSV 데이터 줄 수 설정
    private static final int CHUNK_SIZE  = 2000;
    private static final int THREAD_COUNT = 4;

    public String uploadCsv(MultipartFile file) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<?>> futures = new ArrayList<>();

        //읽은 CSV 리스트에 저장
        List<String> allLines;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            allLines =
                    reader.lines().
                            skip(1).
                            collect(Collectors.toList());

        }
        List<List<String>> shoppingMallChunks = splitIntoChunks(allLines,CHUNK_SIZE);

        //저장된 리스트데이터 엔티티 리스트로 저장 > 데이터베이스에 저장
        for(List<String> shoppingMallChunk : shoppingMallChunks){
            futures.add(executorService.submit(()->{
                List<ShoppingMall> shoppingMallEntity = shoppingMallChunk.stream()
                        .map(this::getShoppingMall)
                        .filter(Objects::nonNull)
                        .toList();

                shoppingMallCsvRepository.saveAll(shoppingMallEntity);
            }));
        }

        for(Future<?> future : futures){
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e){
               throw new fileException();
            }
        }

        executorService.shutdown();

        return allLines.size()+"건";
    }

    private List<List<String>> splitIntoChunks(List<String> liest,int chunkSize){
        //CSV를 2000줄씩 끊어서 리스트에 저장
        List<List<String>> chunks = new ArrayList<>();

        for(int i = 0 ; i < liest.size() ; i += chunkSize){
            chunks.add(liest.subList(i,Math.min(i+chunkSize, liest.size())));
        }
        return chunks;
    }

    //클라이언트가 업로드 한 CSV 파일 받는다
    public ShoppingMall getShoppingMall(String line) {

        //List<Future<?>> futures = new ArrayList<>();
        //ShoppingMall타입으로 리스트 선언
        //엔티티 타입의 값만 반영 가능
        //List<ShoppingMall> shoppingMallList = new ArrayList<>();
        //라인 변수 선언
        //List<String> lines;
        //String line;

        //받은 파일이 내용이 비워져 있으면 예외처리
//        if (file.isEmpty()) {
//            throw new FindException();
//        }

        //파일을 한줄 씩 읽기 가능하도록 설정
        //받은 CSV 파일 바이트 형태로 받은 후 해당 파일을 문자열로 변환 그 후 버퍼링을 통해 효율적으로 읽도록 설정
        //try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

        //지금 읽는 줄이 첫번째 줄인지 여부 판단하는 boolean플래그 변수 활성화
        //boolean isFirst = true;
        //가져온 파일 이름 가져오기
        //String fileName = file.getName();

        //첫번째 읽은 값 받은 후 continue로 건너뛴 후 플래그 변수 비활성화
        //받은 문자열 파일 readLine을 통해
//            while ((line = reader.readLine()) != null) {
//                if (isFirst) {
//                    isFirst = false;
//                    continue;
//                }

        //필드 내 값이 없어도 한 공간으로 판단해서 리스트에 반영 설정 -1이
        //없으면 값 있는 필드만 리스트에 반영
        String[] fields = line.split(",", -1);
        String[] cleanedFields = Arrays.stream(fields)
                .map(s -> s.replace("\"",""))
                .toArray(String[]::new);

        //열이 30개 미만이면 아래 로직 건너뜀 이상이면 진행
        if (fields.length < 33)
            //trim(공백제거)
            //엔티티에 저장된 값 변수에 저장
            return new ShoppingMall(
                    //문자열을 Long 타입으로 변환 & 문자열을 int 타입으로 변환
                    //Long.parseLong(fields[0]),Integer.getInteger(fields[0])
                    cleanedFields[22],cleanedFields[11],cleanedFields[0],cleanedFields[9],
                    cleanedFields[6],cleanedFields[17],cleanedFields[13],cleanedFields[25],
                    cleanedFields[8],cleanedFields[28],cleanedFields[26],cleanedFields[2],
                    cleanedFields[7],cleanedFields[16],cleanedFields[1],cleanedFields[18],cleanedFields[29],
                    cleanedFields[31],cleanedFields[4],cleanedFields[10],
                    cleanedFields[13],cleanedFields[19],cleanedFields[3],cleanedFields[21],cleanedFields[15],
                    cleanedFields[23],cleanedFields[27],cleanedFields[5],cleanedFields[24],cleanedFields[30],
                    cleanedFields[20],cleanedFields[14]
            );

        //엔티티에 저장한 데이터 리스트에 반영
        //shoppingMallList.add(shoppingMall);

        //리스트 사이즈가 rageSize보다 크거나 같을때 저장/같지 않거나 작을때 저장 종료
//                if(shoppingMallList.size() >= BATCH_SIZE){
//                    //데이터베이스에 리스트 내 데이터 저장
//                    shoppingMallCsvRepository.saveAll(shoppingMallList);
//                    shoppingMallList.clear();
//                }

        //리스트에 값이 존재할때 저장 진행/값이 없을때 저장 종료
//            if(!shoppingMallList.isEmpty()){
//                //남은 데이터 마무리로 저장
//                shoppingMallCsvRepository.saveAll(shoppingMallList);
//                //모든 요소만 제거(주의 : 내부에 모든 값이 제거되어 사이즈가 0이 됨 isEmpty도 true됨
//                //리스트가 null이 되는것은 아님 빈 공간이라도 있으니
//                shoppingMallList.clear();
//            }
        //null사용한 이유는 어차피 읽기 전용 저수준 메서드이기때문(저수준 메서드) 활용
        //비지니스 로직을 담는 도메인과 같은 고수준의 모듈에서는 null 절대 사용 금지
        return null;
    }
}
```

---

### 효과

<img width="1203" height="558" alt="1231234 PNG" src="https://github.com/user-attachments/assets/1923fc41-668d-44ab-8e2a-efba083f23ef" />


멀티쓰레드 구현 전 코드와 후 코드는 크게 다른것은 없으며 병렬기준도 설정한 데이터 줄 수 기준으로

데이터를 읽고 저장하는 로직입니다. 하나 다른것은 쓰레드도 설정하여서 몇개의 쓰레드로 작업할지 결정이 가능하며 

`List<Future<?>> futures = new ArrayList<>();` 각 쓰레드들은 `futures` 작업방에 각자 읽고 저장할 데이터를 가지고 들어오게됩니다. 들어온 쓰레드들은 읽고 데이터베이스에 리스트로 저장하는데 이때 필터를 만나서 널이 아닌 값들만 리턴하여 리스트에 반영하게 됩니다. 각 쓰레드가 작업을 끝 마치면

리스트에서 대기하게 됩니다.

`for(Future<?> future : futures){    try {        future.get();    } catch (InterruptedException | ExecutionException e){       throw new fileException();    }}`

값을 가지고 대기하고있는 쓰레드들은 해당 반복문을 통해 쓰레드가 작업하여 읽고 저장한 데이터를 가져오게됩니다. 만약 여기서 해당 리스트에 없다면 예외처리를 진행하였습니다.(쓰레드가 값을 읽다가 막히거나 타입이 다른경우)

멀티쓰레드를 통해 1분을 7초로 단축하여 CSV를 읽고 저장하는 로직을 구현하였습니다.

### 팀트러블 슈팅

### 문제: Filter 체인 누락으로 인한 조회 API 응답 본문 미반환 이슈

## 문제 상황

**‼️** POSTMAN에서 회원 가입 및 로그인은 정상 작동 하나, 로그인 후 토큰을 포함한 목록 조회 시 200 OK만 반환되고 응답 본문을 불러오지 못함


<img width="1392" height="912" alt="스크린샷 2025-07-11 오후 8 54 46" src="https://github.com/user-attachments/assets/ce65b087-98e0-4ef4-ba7f-c3749abde536" />

✅ 원인 분석

- 코드 병합 과정에서 필수 로직이 주석 처리된 채로 dev 브랜치에 병합되었고,

그 중에서도 **chain.doFilter(request, response); 호출이 누락된 것이 핵심 원인**

→

LoginJwtFilter에서 JWT 토큰의 유효성 검증까지는 정상적으로 수행되었으나,

이후 필터 체인을 통해 요청을 **다음 필터나 컨트롤러로 전달해주는 chain.doFilter(); 호출이 누락**되어

**요청이 컨트롤러 계층으로 전달되지 않았고**, 그 결과 응답 본문 없이 **200 OK 상태만 반환되는 문제**가 발생함.

즉, **인증은 성공했지만 요청 데이터 흐름이 중단된 상태**에서, 실제 로직(데이터 조회 등)이 수행되지 않은 채 응답이 종료된 것.

```java
        // JWT 검증
        if (!jwtTokenProvider.validateToken(token)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT 토큰이 유효하지 않습니다.");
            return;
        }
//       // 멤버 정보 추출 및 저장
//       String email = jwtTokenProvider.extractEmail(token); 
//       String memberName = jwtTokenProvider.extractMemberName(token);
//       long memberId = jwtTokenProvider.extractId(token);
//
//        // 요청 객체에 저장
//        req.setAttribute("email", email);
//        req.setAttribute("memberName", memberName);
//        req.setAttribute("memberId", memberId);
//        
//        chain.doFilter(request, response); <<< 주석 처리 해제
    }   

```

✅ 개선 내용

```java
        chain.doFilter(request, response);
```

- 누락된 chain.doFilter(request, response); 호출 복구
- PR 전 불필요한 주석 정리 및 삭제
- GitHub/GitLab에서 CODEOWNERS 지정 및 리뷰 승인 필수 설정을 적용하여 코드 리뷰 프로세스 강화

문제 해결 후 응답B**ody** 정상 호출되는 모습
<img width="857" height="314" alt="스크린샷 2025-07-14 오후 1 00 47" src="https://github.com/user-attachments/assets/a756ca1c-27ef-4158-8dac-49c5b52f8fe6" />
