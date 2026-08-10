# 정규화 
	# DB 설계에서 데이터 중복을 최소화하고, 
    # 무결성을 유지하기 위해 데이터를 구조화하는 과정
    # => 테이블 쪼개기

# 제1정규화 
	# 원자값 
    # 한 컬럼의 값이 ,로 여러개로 된 경우
		# 취미 컬럼 : 홍길동 학생의 취미가 야구,배구,축구
			# 취미 테이블을 만들어서 관리 
    
# 제2정규화 
	# 부분 함수 종속 제거 
    # 기본키가 복합키일 때, 컬럼이 기본키의 일부에 종속되면 분리 
    
# 제3정규화 
	# 이행함수 종속 제거 
    # 기본키가 아닌 컬럼들끼리 종속 관계에 있으면 분리 
    
# BCNF 
	# 복합키 내에서 종속성 제거 

# enrollment_unf 테이블은 정규화 되지 않은 테이블. 기본키는 student_id, course_id
use nf;
# 제1정규화 : 원자값 확인
	# phone_number에 번호가 2개 
    # => 번호를 하나만 남김 또는 연락처 테이블을 추가 
# 연락처를 1개만 남김 
UPDATE enrollment_unf 
SET 
    phone_number = '010-1111'
WHERE
    student_id = 101;

# student_name과 phone_number가 student_id(기본키)에 종속됨 => 제2정규화 
# course_name이 course_id(기본키)에 종속됨  => 제2정규화 

create table if not exists student(
	student_id int primary key,
    student_name varchar(20),
    phone_number varchar(100)
);

create table if not exists course(
	course_id char(4) primary key,
    course_name varchar(50)
);
# 새로 만든 student 테이블에 학생 정보들 추가 
insert student(student_id, student_name, phone_number)
values(101, '홍길동', '010-1111'),(102, '김철수', '010-3333');

# 새로 만든 course 테이블에 코스들 추가 
insert course(course_id, course_name)
values('CS01', '데이터베이스'), ('CS02', '알고리즘');

# 기존 enrollment_unf 테이블에 있는 컬럼들 삭제 
# (student_name, phone_number,course_name)
alter table enrollment_unf drop student_name;
alter table enrollment_unf drop phone_number;
alter table enrollment_unf drop course_name;

# professor_room이 professor_name에 종속이 됨 
# 기본키가 아닌 컬럼에 종속 => 제3정규화가 필요 
create table if not exists professor(
	professor_name varchar(20) primary key,
    professor_room varchar(20)
);
# 교수 정보 추가 
insert into professor(professor_name, professor_room)
values('홍길동', '301호'), ('김철수', '302호');

# enrollment_unf에 교수실 컬럼 삭제 
alter table enrollment_unf drop professor_room;

# 제1,2,3 정규화가 진행된 테이블 
select * from enrollment_unf;




    