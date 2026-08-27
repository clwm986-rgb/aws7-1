package kr.fast.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
