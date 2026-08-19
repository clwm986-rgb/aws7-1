package kr.fast.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.boot.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
