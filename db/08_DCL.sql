# DCL : 데이터 조작어
	# 사용자 추가/삭제, 권한 부여/회수 
    
# 사용자 추가
	# create user '아이디'@'호스트' identified by '비번';
    # 호스트
		# localhost : 내부에서만 접근 가능 => 다른 PC에서 접근 못함
        # % : 외부에서도 접근 가능
	# root : 기본 localhost

create user 'abc123'@'%' identified by 'abc123';

# 사용자 조회
select user, host from mysql.user;

# 사용자 비번 변경 
# set password for '아이디'@'호스트' = '새비번';

# 사용자 삭제 
# drop user '아이디'@'호스트';
drop user 'abc123'@'%';

# 삭제된 사용자 확인
select user, host from mysql.user;

create user 'abc123'@'%' identified by 'abc123';

# DB 생성 
create database if not exists test;

# 권한 부여
	# 사용자에게 특정 DB의 접근 권한을 부여 
    # 테이블 추가/수정/삭제, 데이터 추가/수정/삭제/조회 등의 권한을 부여 
    # select/insert/update/delete/create/alter/drop/reffences 등
    # all privileges(모든 권한 부여)
# grant 권한 on db명.테이블명 to '아이디'@'호스트';

# test DB에 권한 부여 
grant all privileges on test.* to 'abc123'@'%';

# 권한 확인
# show grants for '아이디'@'호스트';
show grants for 'abc123'@'%';

# 권한 회수
# revoke 권한 on db명.테이블명 from '아이디'@'호스트';
# 쿼리 실행 정상. 왜인지 모르지만 에러 표시 뜸(FROM에)
revoke all privileges on test.* from 'abc123'@'%';





