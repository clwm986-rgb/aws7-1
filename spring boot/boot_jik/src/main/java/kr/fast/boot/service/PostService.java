package kr.fast.boot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.boot.dto.PostDTO;
import kr.fast.boot.entity.Post;
import kr.fast.boot.repository.BoardRepository;
import kr.fast.boot.repository.PostRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	
	private final BoardRepository boardRepository;

	@Transactional
	public void insertPost(PostDTO dto) {
		//입력값 예외처리
		if(dto == null || !dto.checkTitleValid()) {
			throw new IllegalArgumentException("제목을 입력하세요.");
		}
		if(!dto.checkContentValid()) {
			throw new IllegalArgumentException("내용을 입력하세요.");
		}
		//게시판 번호 체크
		if(!boardRepository.existsById(dto.boardId())) {
			throw new IllegalArgumentException("잘못된 게시판입니다.");
		}
		
		//Post엔티티 생성
		Post post = new Post(dto.title(), dto.content(), dto.writer(), dto.boardId());
		//레포야 엔티티 줄게 저장해.
		postRepository.save(post);
	}

	public List<Post> getPostList() {
		//내림차순=>최신 게시글이 제일처음
		List<Post> list = postRepository.findAllByOrderByIdDesc();
		//List<Post> list = postRepository.findAll();//오름차순=>최신 게시글이 제일 마지막
		
		
		return list;
	}
	
	
}
