package kr.fast.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	@Id
	private int id;
	
	private String title;
	
	private String content;
	@Column(name="created_at")
	private String createdAt;
	@Column(name="view_count")
	private int viewCount;
	@Column(name="up_count")
	private int upCount;
	@Column(name="down_count")
	private int downCount;
	@Column(name="is_deleted")
	private char isDeleted;
	@Column(name="member_id")
	private String memeberId;
	@Column(name="post_id")
	private int boardId;
}
