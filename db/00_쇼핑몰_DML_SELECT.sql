# 쇼핑몰에 가입된 회원을 조회
use shoppingmall;
select * from user where authority = 'USER';

# 모든 제품을 조회
select * from product;
# 분류가 ACC인 제품을 조회
select * from product where ca_code = 'ACC';
# 분류가 악세서리인 제품을 조회 
SELECT 
    product.*
FROM
    product
        JOIN
    category ON ca_code = category.code
WHERE
    category.name = '악세서리';
    
# abc123회원이 장바구니에 담은 제품의 제품명과 수량을 조회 
SELECT 
    *
FROM
    cart
        JOIN
    product ON product.code = cart.code # product using(code)
WHERE
    id = 'abc123';
    
# 검색어를 목걸이로 검색했을 때 결과를 조회 
# like, % 
select * from product where name like '%목걸이%';

# 제품별 판매 수량(판매된 제품만)을 조회 
# 제품명, 제품코드, 가격, 판매 수량 조회 
# group by 이용 
# ACC001, 18K목걸이, 300000, 3
# ACC002, 18K반지, 100000, 1
SELECT 
    code as 제품코드, 
    name as 제품명,
    SUM(buy.amount) as 판매량, 
    SUM(product.price * buy.amount) as 판매액
FROM
    buy
        JOIN
    product USING (code)
GROUP BY code;

# 회원별 제품 구매 횟수 
# 회원아이디, 제품구매횟수
SELECT 
    id as 아이디, COUNT(*) as 횟수
FROM
    buy
GROUP BY id;

# abc123 회원의 장바구니를 조회 
# cart 테이블에 샘플 데이터가 10만개라고 가정
# product 테이블은 샘플 데이터가 1만개라고 가정
# 회원은 1000명이라고 가정 
# 앞에서 했던 쿼리 => 10만개 행을 join
select product.* from cart join product using(code)
where id = 'abc123';

# 서브쿼리 이용 => abc123회원이 구매한 횟수만큼 join 
select product.* from (select * from cart where id = 'abc123') as c
join product using(code);

# 악세서리 중 가장 많이 팔린 제품을 조회
# 고려사항 : 팔린 개수가 같으면 어떻게 처리할건가? 
SELECT 
    code, SUM(buy.amount) as 판매량, SUM(buy.amount * price) 판매액
FROM
    buy
	join product using(code)
GROUP BY code
order by 판매량 desc, 판매액 desc, code
limit 1;










