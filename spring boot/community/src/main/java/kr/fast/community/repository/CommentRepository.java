package kr.fast.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	List<Comment> findAllByPostId(int postId);

}
