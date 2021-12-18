# 사용자 게시판 구축 프로젝트
## 소개
### 1. 개요
Spring Framework 기반 사용자 게시판 구축 프로젝트

### 2. 개발환경
* JDK 11.0.12 + Gradle
* Spring Boot 2.6.1
* IntelliJ

### 3. 사용 기술
* **Backend**
  * ORM : Spring Data JPA
  * Security : Spring Security
  * Batch : Spring Batch
  * Test : Mockito + Junit
* **DB**
  * MariaDB(메인), Redis(캐시), H2(로컬)
* **Infra**
  * Docker, Cloud Server (TBD)
* **Frontend**
  * Typescript + Vue

## 개발
### 1. 일정 계획
목표 일정은 최대한 타이트하게 잡았다. 유동적일 가능성 100%
* **기획 및 DB 설계** : 2021.12.08 - 2021.12.09
* **BE 개발 및 테스트** : 2021.12.10 - 2021.12.26
   * Entity, Repository 작성 및 연관관계 매핑 : 12.10 - 12.14
   * Dto, Service 작성 : 12.15 - 12.16
   * 도메인별 Rest Controller 작성 및 테스트 : 12.17
   * Spring Security 적용 및 테스트 : 12.18 - 12.20
   * Spring Batch 서비스 개발 : 12.21 - 12.23
* **FE 개발 및 테스트** : 2021.12.27 - 2022.01.03
* **연동 및 통합 테스트** : 2021.01.04 - 2021.01.07

### 2. 기획 및 DB 설계
* **목표**
   * 게시판 사이트 구축
* **세부 기능**
   * 글/댓글 작성 및 좋아요 기능 : 로그인한 사용자는 글과 댓글을 작성하고, 추천할 수 있다.
   * 로그인 기능 : 로그인하지 않아도, 모든 사용자는 글과 댓글을 조회할 수 있다. 
   * 데이터 집계 기능 : 정해진 시간마다 관리자에게 매일 작성된 글과 댓글의 수를 집계하여 보낸다.
* [**DB 설계**](https://www.notion.so/imagineallthepeople/DB-b5ff457d6a884b9c8a929c2962e2510d)

### 3. BE 개발 및 테스트
* [Entity, Repository 작성 및 연관관계 매핑](https://www.notion.so/imagineallthepeople/JPA-Entity-h2-a539aed5a19d4a48ad85cdb2cdd82dde)
* [Dto, Service 작성](https://www.notion.so/imagineallthepeople/JPA-DTO-Service-bf48aec545e141c2abc6a2fed917c75f)
* 도메인별 Rest Controller 작성 및 테스트
* Spring Security 적용 및 테스트
   * [설정](https://www.notion.so/imagineallthepeople/SpringSecurity-UserDetails-2563e62b4d4246fd9853ed850a8457ce)
* [Spring Batch 서비스 개발](https://www.notion.so/imagineallthepeople/Spring-Batch-e8c03f021722427f82788de33a0a1d30)

### 4. FE 개발 및 테스트

### 5. 연동 및 통합 테스트
