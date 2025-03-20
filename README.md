# Sboard Project (게시판 프로젝트)

Spring Boot 기반의 게시판 프로젝트입니다. JPA, QueryDSL, ModelMapper 등 다양한 기술을 활용하여 CRUD 기능, 파일 업로드, 페이징, 검색 기능을 구현했습니다.

## 📌 프로젝트 개요

- **프로젝트 명**: Sboard Project
- **개발 기간**: 2024.09.20 ~ 2024.09.27
- **개발 인원**: 1명(개인 프로젝트)
- **주요 기능**: 게시글 CRUD, 파일 업로드/다운로드, 페이징, 검색, 인터셉터 활용 등

## 🛠 기술 스택

### Backend
- Java 17
- Spring Boot `3.4.1`
- Spring Data JPA 3.4.4
- QueryDSL 4.4.0
- ModelMapper 3.2.0
- Lombok
- MySQL 8.0

### Frontend
- Thymeleaf
- Bootstrap 5
- jQuery

## 📋 주요 기능 및 구현 내용

### 1. 게시판 기능
- 게시글 목록 조회 (페이징 처리)
- 게시글 작성/수정/삭제
- 게시글 검색 (QueryDSL을 활용한 동적 쿼리 구현)

### 2. 파일 관리
- 게시글 작성 시 다중 파일 업로드
- 업로드된 파일 다운로드
- 파일 메타데이터 관리

### 3. 인터셉터 활용
- AppInfoInterceptor를 통한 공통 정보 제공
- 모든 View에 애플리케이션 정보 자동 주입

### 4. QueryDSL 활용
- 타입 안전한 동적 쿼리 구현
- 복잡한 검색 조건 처리

### 5. ModelMapper 활용
- DTO와 Entity 간 효율적인 데이터 변환
- 유연한 객체 매핑 설정

## 🏗 프로젝트 구조

```
com.sboard
├── config
│   ├── AppConfig.java         // 애플리케이션 설정
│   ├── AppInfo.java           // 애플리케이션 정보
│   ├── QueryDslConfig.java    // QueryDSL 설정
│   └── WebMvcConfig.java      // Spring MVC 설정
├── controller
│   └── ArticleController.java  // 게시글 컨트롤러
├── dto
│   ├── ArticleDTO.java        // 게시글 데이터 전송 객체
│   ├── FileDTO.java           // 파일 데이터 전송 객체
│   ├── PageRequestDTO.java    // 페이지 요청 객체
│   └── PageResponseDTO.java   // 페이지 응답 객체
├── entity
│   ├── Article.java           // 게시글 엔티티
│   └── File.java              // 파일 엔티티
├── interceptor
│   └── AppInfoIntercepter.java // 애플리케이션 정보 인터셉터
├── repository
│   ├── ArticleRepository.java  // 게시글 레포지토리
│   └── FileRepository.java     // 파일 레포지토리
└── service
    ├── ArticleService.java     // 게시글 서비스 인터페이스
    ├── ArticleServiceImpl.java // 게시글 서비스 구현체
    ├── FileService.java        // 파일 서비스 인터페이스
    └── FileServiceImpl.java    // 파일 서비스 구현체
```

## 💻 실행 방법

### 요구사항
- JDK 17 이상
- MySQL 8.0 이상
- Maven 3.8 이상

### 설치 및 실행
1. 저장소 클론
```bash
git clone https://github.com/사용자명/sboard-project.git
```

2. 데이터베이스 설정
```bash
# MySQL 접속
mysql -u root -p

# 데이터베이스 생성
CREATE DATABASE sboard;
```

3. application.properties 설정
```properties
# 데이터베이스 설정
spring.datasource.url=jdbc:mysql://localhost:3306/sboard
spring.datasource.username=root
spring.datasource.password=your_password

# JPA 설정
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# 애플리케이션 정보
spring.application.name=Sboard
spring.application.version=1.0.0
```

4. 프로젝트 실행
```bash
cd sboard-project
./mvnw spring-boot:run
```

5. 웹 브라우저에서 접속
```
http://localhost:8080/article/list
```

## 📝 주요 구현 내용 상세 설명

### ModelMapper 구현
ModelMapper를 사용하여 DTO와 Entity 간의 데이터 변환을 자동화했습니다. 특히 `PRIVATE` 접근 수준과 `STRICT` 매칭 전략을 적용하여 정확한 데이터 매핑을 보장했습니다.

```java
@Bean
public ModelMapper getModelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true);
    return modelMapper;
}
```

### QueryDSL 활용
QueryDSL을 통해 타입 안전한 동적 쿼리를 구현했습니다. 이를 통해 다양한 검색 조건에 따른 유연한 데이터 조회가 가능해졌습니다.

### 인터셉터 활용
`AppInfoIntercepter`를 구현하여 모든 뷰에 애플리케이션 정보를 자동으로 제공합니다. 이를 통해 푸터 등에 애플리케이션 이름과 버전을 일관되게 표시할 수 있습니다.

### 파일 업로드/다운로드
게시글 작성 시 다중 파일 업로드 기능을 구현했습니다. 업로드된 파일의 메타데이터는 데이터베이스에 저장하고, 실제 파일은 서버의 지정된 디렉토리에 저장합니다.

© 2024 김민희 Sboard Project
