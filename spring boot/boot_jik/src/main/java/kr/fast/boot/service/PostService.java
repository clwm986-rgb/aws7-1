package kr.fast.boot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.boot.dto.PostDTO;
import kr.fast.boot.entity.Board;
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
	@Transactional
	public List<Post> getPostList() {
		//내림차순=>최신 게시글이 제일처음
		List<Post> list = postRepository.findAllByIsDeletedOrderByIdDesc("N");
		//List<Post> list = postRepository.findAll();//오름차순=>최신 게시글이 제일 마지막
		
		
		return list;
	}
	@Transactional
	public Post getPost(int id) {
		Post post 
		= postRepository.findById(id)
			.orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
		return post;
	}
	@Transactional
	public void updateView(int id) {
		//게시글 엔티티를 가져옴
		Post post = getPost(id);
		//조회수 증가
		post.updateView();
	}
	@Transactional
	public void deletePost(int id) {
		//레포야 게시글 가져와 id 줄게. 단, 없으면 예외발생시켜
		Post post 
		= postRepository.findById(id)
			.orElseThrow(()-> new IllegalArgumentException("삭제할 게시글이 없습니다."));
		
		//이미 삭제된 게시글이면 예외를 발생
		if(post.getIsDeleted().equals("Y")) {
			throw new IllegalArgumentException("이미 삭제된 게시글입니다.");
		}
		
		//레포야 게시글 삭제해줘. 게시글 줄게
		//소프트 삭제 : 실제 데이터 안지움
		post.delete();
		
		//직접 데이터 삭제
		//postRepository.delete(post);
	}
	
	
}
