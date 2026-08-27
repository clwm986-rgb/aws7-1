package kr.fast.community.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {

	@Id
	private String id;
	
	@Column(name="password")
	private String pw;
	
	@Column(name="email")
	private String email;
	
	@Column(name="role")
	private String role = "USER";

	public Member(String id, String pw, String email) {
		this.id = id;
		this.pw = pw;
		this.email = email;
	}
	
}
