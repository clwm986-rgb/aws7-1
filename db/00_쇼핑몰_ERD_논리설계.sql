/*
ERD : https://drive.google.com/file/d/1Mkd2lGNXtGTnTxYpSSSr60jOcPBGOTjo/view?usp=sharing
쇼핑몰 논리 설계

회원(아이디(PK), 비번, 이메일, 전화번호, 상태, 로그인횟수, 권한)
제품(제품코드(PK), 제품명, 제품상세, 썸네일, 제고, 가격, 분류코드(FK))
분류(분류코드(PK), 분류명)

구매(아이디(FK,PK), 제품코드(FK, PK), 수량, 구매일, 구매상태) : 식별관계
장바구니(장바구니번호(PK), 아이디(FK), 제품코드(FK),수량) : 비식별관계 

*/