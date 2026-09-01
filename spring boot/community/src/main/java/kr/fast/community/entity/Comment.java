package kr.fast.community.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//DB에서 AUTO_INCREMENT로 기본키 생성
	int id;
	
	String content;
	
	@Column(name="created_at")
	LocalDateTime createdAt;
	
	@Column(name="origin_id")
	int originId;

	@Column(name="is_deleted")
	String isDeleted;

	@Column(name="member_id")
	String memberId;

	@Column(name="post_id")
	int postId;

}
