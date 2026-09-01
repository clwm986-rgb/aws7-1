package kr.fast.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {

}
