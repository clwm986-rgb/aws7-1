package kr.fast.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {

<<<<<<< HEAD
	public Like findByPostIdAndMemberId(int postId, String memberId);
=======
	Like findByPostIdAndMemberId(int postId, String memberId);
>>>>>>> 25b148e114ce9a6429b0efced042fba573d7c453

}
