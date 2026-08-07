# 내장함수
# if(수식, 수식1, 수식2) : 수식이 참이면 수식1을, 거짓이면 수식2를 리턴
set @age = 10;
select if(@age > 19, '성인', '미성년자') as '나이는';

# ifnull(수식1, 수식2) : 수식1이 null이 아니면 수식1을, null이면 수식2를 반환 
	# null값일 때 넣을 기본값 설정할 때 사용 
set @age2 = null;
select ifnull(@age2, 0), ifnull(@age,0);

# nullif(수식1, 수식2) : 수식1과 수식2과 같으면 null, 다르면 수식1을 반환 
set @age3 = 10;
select nullif(@age,@age3), nullif(@age,@age2);

# case 컬럼 
# when 값1 then 
#	결과 
# when 값2 then 
#	결과 
# else 
#	결과 
# end 
set @grade = 'A+';
SELECT 
    CASE @grade
        WHEN 'A+' THEN '장학금 가능'
        WHEN 'F' THEN '학고 가능'
        ELSE '??'
    END as 결과;

# case 
# when 조건 then 결과
# else 결과 
# end
SELECT 
    CASE
        WHEN @age > 19 THEN '성인'
        ELSE '미성년자'
    END as 결과;

