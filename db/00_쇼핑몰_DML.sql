# 다음 회원을 추가 
# 아이디 : abc123, 비번 : abc123, 이메일 : abc123@naver.com 
# 번호 : 011-1111-2222
insert into user(id, pw, email, phone)
values('abc123', 'abc123', 'abc123@naver.com', '011-1111-2222' );

# 제품 분류 추가 
# 제품 분류 코드 : ACC, 분류명 : 악세서리 
insert into category(code, name) values('ACC', '악세서리');

# 제품 추가 
# 악세서리 -  제품명 : 18K목걸이, 내용 : 예쁜 목걸이입니다. 가격 : 100000, 썸네일 없음 
insert into product(code, name, content, price, thumbnail, ca_code)
values('ACC001', '18K목걸이', '예쁜 목걸이입니다.', 1000000, null, 'ACC');
# 악세서리 -  제품명 : 18K반지, 내용 : 예쁜 반지입니다. 가격 : 100000, 썸네일 없음 
insert into product(code, name, content, price, thumbnail, ca_code)
values('ACC002', '18K반지', '예쁜 반지입니다.', 1000000, null, 'ACC');

# abc123회원이 ACC001을 2개 장바구니에 담음 
insert into cart(id, code, amount) value('abc123', 'ACC001', 2);

# abc123회원이 ACC001을 1개 장바구니에 담음 
update cart 
set amount = 1
where id = 'abc123' and code = 'ACC001';

# abc123회원이 ACC002를 1개 장바구니에 담음 
insert into cart(id, code, amount) value('abc123', 'ACC002', 1);

# 제품 입고(제품 수량 추가) - 등록된 모든 제품이 10개씩 입고 
update product 
set
	amount = amount + 10
where
	code in('ACC001','ACC002');

# abc123회원이 장바구니에 있는 모든 제품들을 구매
# => 구매 테이블에 구매 내역 추가 => 제품 제고 변경 => 장바구니 삭제 
insert into buy(id, code, amount) 
	values('abc123', 'ACC001', 1);
insert into buy(id, code, amount) 
	values('abc123', 'ACC002', 1);

update product set amount = amount - 1 where code = 'ACC001';    
update product set amount = amount - 1 where code = 'ACC002';

delete from cart where id = 'abc123' and code = 'ACC001';
delete from cart where id = 'abc123' and code = 'ACC002';

# abc123회원이 ACC001, ACC002 제품을 장바구니에 담음 
insert into cart(id, code, amount) value('abc123', 'ACC001', 2);
insert into cart(id, code, amount) value('abc123', 'ACC002', 1);

# 다음 회원을 추가 
# 아이디 : asdasd, 비번 : asdasd, 이메일 : asdasd@naver.com 
# 번호 : 011-9999-9999
insert into user(id, pw, email, phone)
values('asdasd', 'asdasd', 'asdasd@naver.com', '011-9999-9999' );

# asdasd회원이 ACC001 제품 2개를 구매 
insert into buy(code, amount, id) values('ACC001', 2, 'asdasd');

# 제품 수량 변경
update product set amount = amount - 2 where code = 'ACC001';

# 악세서리 -  제품명 : 백금목걸이, 내용 : 백금 목걸이입니다. 가격 : 50000, 썸네일 없음 
insert into product(code, name, content, price, thumbnail, ca_code)
values('ACC003', '백금목걸이', '백금 목걸이입니다.', 500000, null, 'ACC');

# 악세서리 -  제품명 : 커플반지, 내용 : 커플반지입니다. 가격 : 150000, 썸네일 없음 
insert into product(code, name, content, price, thumbnail, ca_code)
values('ACC004', '커플반지', '커플반지입니다', 1500000, null, 'ACC');

# 악세서리 -  제품명 : 돌반지, 내용 : 아이 돌반지입니다. 가격 : 350000, 썸네일 없음 
insert into product(code, name, content, price, thumbnail, ca_code)
values('ACC005', '돌반지', '아이 돌반지입니다.', 3500000, null, 'ACC');

# 모든 제품 제고 입고(각 10개)
update product set amount = amount + 10;