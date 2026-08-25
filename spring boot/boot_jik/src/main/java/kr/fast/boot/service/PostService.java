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
	public int insertPost(PostDTO dto, String username) {
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
		
		//로그인한 회원이 아니면
		if(username == null || username.equals("anonymousUser")) {
			throw new IllegalArgumentException("로그인 필요합니다.");
		}
		try {
			//Post엔티티 생성
			Post post = new Post(dto.title(), dto.content(), username, dto.boardId());
			//레포야 엔티티 줄게 저장해.
			Post savedPost = postRepository.save(post);
			
			return savedPost.getId();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("쿼리 실행 중 이상이 생겼습니다.");
		}
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
	public void deletePost(int id, String username) {
		//레포야 게시글 가져와 id 줄게. 단, 없으면 예외발생시켜
		Post post 
		= postRepository.findById(id)
			.orElseThrow(()-> new IllegalArgumentException("삭제할 게시글이 없습니다."));
		
		//이미 삭제된 게시글이면 예외를 발생
		if(post.getIsDeleted().equals("Y")) {
			throw new IllegalArgumentException("이미 삭제된 게시글입니다.");
		}
		//삭제하려는 사람이 작성자가 아니면(보통은 이런일이 발생하지 않음)
		if(!post.getMemberId().equals(username)) {
			throw new IllegalArgumentException("작성자가 아닙니다.");
		}
		
		//레포야 게시글 삭제해줘. 게시글 줄게
		//소프트 삭제 : 실제 데이터 안지움
		post.delete();
		
		//직접 데이터 삭제
		//postRepository.delete(post);
	}
	@Transactional
	public void updatePost(int id, PostDTO dto, String username) {
		
		//id와 일치하는 게시글을 가져옴
		Post post = postRepository.findById(id)
				.orElseThrow(()->new IllegalArgumentException("등록되지 않은 게시글입니다."));
		
		if(!post.getMemberId().equals(username)) {
			throw new IllegalArgumentException("작성자가 아닙니다.");
		}
		
		//수정할 제목과 내용 체크
		if(dto == null || !dto.checkTitleValid()) {
			throw new IllegalArgumentException("제목을 입력하세요.");
		}
		if(!dto.checkContentValid()) {
			throw new IllegalArgumentException("내용을 입력하세요.");
		}
		//게시글의 제목과 내용을 수정
		post.update(dto.title(), dto.content());
		
	}
	
	
}
