package kr.fast.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Page<Post> findAllByIsDeletedContaining(String isDeleted, Pageable pageable);

	Page<Post> findAllByIsDeletedAndTitleContaining(String isDeleted, String keyword, Pageable pageable);

	Page<Post> findAllByIsDeletedAndMemberIdContaining(String isDeleted, String keyword, Pageable pageable);

	Post findByIdAndIsDeleted(int postId, String isDeleted);

}
