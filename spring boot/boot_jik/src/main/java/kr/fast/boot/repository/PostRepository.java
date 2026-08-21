package kr.fast.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.boot.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
