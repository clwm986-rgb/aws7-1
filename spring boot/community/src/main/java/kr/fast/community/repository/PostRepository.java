package kr.fast.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByIsDeletedOrderByIdDesc(String isDeleted);

}
