# Ureka MQ Study

---

## 1. 스터디 목표 (Study Goals)
   RabbitMQ와 Kafka를 학습하며 병렬처리에 대한 이해와 성능 개선
 
### 🪧 Commit 컨벤션
| 타입       | 설명                            | 예시                                                       |
|----------|-------------------------------|----------------------------------------------------------|
| feat     | 새로운 실습 코드 추가                  | feat-(jira티켓번호): [Kafka-shs] Producer 배치 전송 예제 추가        |
| docs     | 이론 정리, 추가 주석 처리 및 발표 자료 업로드   | docs-(jira티켓번호): [RabbitMQ-shs] RabbitMQ ~~주제 발표 자료 정리   |
| refactor | 실습코드 개선                       | refactor-(jira티켓번호): [Kafka-shs] Producer 배치 전송 예제 수정    |
| test     | 테스트 코드 작성 및 성능 벤치마킹 결과        | test-(jira티켓번호): [Kafka-shs] 파티션 수에 따른 Throughput 측정     |
| fix      | 동작하지 않거나 에러가 있는 실습 코드 수정      | fix-(jira티켓번호): [Kafka-shs] Producer 배치 전송 과정 중 전송 오류 수정 |
| infra    | slack 및 그 외 인프라 관련 코드 추가하는 경우 | infra-(jira티켓번호): slack 코드 리뷰 결과 알림 추가                   |
| chore    | 외부 의존성 버전 수정, 불필요 데이터 삭제 등    | chore: gitignore 내용 추가(.iml)                             |

---

### 🚀 PR (Pull Request) 컨벤션
PR 제목은 작업 단위와 지라(Jira) 티켓 번호를 결합하여 작성합니다.

- **형식:** `[주된 내용(Feature) - 지라티켓번호] 요약 설명`
- **예시:** `[Feature - 123] DLQ 구현`

---

### 🌿 브랜치 전략
주차별 작업 구분을 위해 아래와 같은 네이밍 규칙을 따릅니다.

- **형식:** `week0x-본인 이니셜(이름 성 순서)`
- **예시:** `week01-shs`, `week02-shs`