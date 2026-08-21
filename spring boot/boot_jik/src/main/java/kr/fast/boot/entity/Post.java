package kr.fast.boot.entity;

import java.util.Date;

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
	@Column(name="created_at", nullable = false)
	private Date createdAt = new Date();
	@Column(name="view_count")
	private int viewCount;
	@Column(name="up_count")
	private int upCount;
	@Column(name="down_count")
	private int downCount;
	@Column(name="is_deleted", nullable = false)
	private char isDeleted = 'N';
	@Column(name="member_id")
	private String memberId;
	@Column(name="board_id")
	private int boardId;
	
	public Post(String title, String content, String writer, Integer boardId) {
		this.title = title;
		this.content = content;
		this.memberId = writer;
		this.boardId = boardId;
	}
}
