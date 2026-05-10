# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**cis-api** is a Spring Boot 3.5 REST API that reads from the `ifr_cis_spec` table in PostgreSQL 16 and provides data to the CIS source system.

## Commands

```bash
# 서버 실행
./gradlew bootRun

# 빌드
./gradlew build

# 테스트
./gradlew test
```

Swagger UI: `http://localhost:8080/swagger-ui.html`

## Tech Stack Conventions

- **Build:** Gradle (not Maven)
- **ORM:** MyBatis — SQL은 반드시 `src/main/resources/mapper/**/*.xml`에 작성
- **Pattern:** MVC — Controller → Service interface → ServiceImpl → Mapper interface → XML
- **Lombok:** DTO는 `@Data`, 생성자 주입은 `@RequiredArgsConstructor`

## Architecture

```
controller/
    CisSpecController        ← POST /api/v1/specs
service/
    CisSpecService           ← interface
    impl/CisSpecServiceImpl  ← @Service, @RequiredArgsConstructor
mapper/
    CisSpecMapper            ← @Mapper interface
dto/
    CisSpecRequest           ← 검색 조건 (specCode, specName, specType, useYn)
    CisSpecResponse          ← 응답 데이터
config/
    SwaggerConfig            ← springdoc OpenAPI 설정
resources/mapper/
    CisSpecMapper.xml        ← 실제 SQL (<where> + <if> 동적 쿼리)
```

## API

| Method | Path | Description |
|---|---|---|
| POST | `/api/v1/specs` | 조건 검색 — body 비우면 전체 조회 |

## Database

- **Table:** `ifr_cis_spec`
- **Columns (임시):** `spec_id`, `spec_code`, `spec_name`, `spec_value`, `spec_type`, `use_yn`, `created_at`, `updated_at`
- 실제 스키마 확정 후 `CisSpecResponse.java`와 `CisSpecMapper.xml` 수정 필요

DB 접속 정보는 `src/main/resources/application.yml`에서 설정.
