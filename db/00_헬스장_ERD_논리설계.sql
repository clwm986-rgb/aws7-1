# ERD : https://drive.google.com/file/d/1md5gNRN-9Jm8lgR_eysgRBV3RyRttoCf/view?usp=sharing
# 논리적 설계 

# 회원(회원번호(PK), 이름, 가입일, 연락처)
# 사물함(사물함번호(PK), 위치, 회원번호(FK))
# 트레이너(사번(PK), 이름, 전공, 경력, 팀장사번(FK))
# 프로그램(프로그램코드(PK), 프로그램명, 정원, 수강료, 트레이너사번(FK))

# 수강(수강번호(PK), 신청일자, 결제상태, 회원번호(FK), 프로그램코드(FK))
# 출석(수강번호(PK), 날짜, 출석여부, 회원번호(FK), 프로그램코드(FK))

