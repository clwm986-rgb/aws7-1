package kr.fast.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.fast.community.dto.MessageResponse;
import kr.fast.community.dto.PostRequest;
import kr.fast.community.entity.Board;
import kr.fast.community.entity.Post;
import kr.fast.community.repository.BoardRepository;
import kr.fast.community.repository.MemberRepository;
import kr.fast.community.repository.PostRepository;
import kr.fast.community.security.CustomUserDetails;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;

	public List<Post> getPosts() {
		//전체 게시글을 가져오는 코드를 작성 해보세요.
		//레포야 게시글 전체를 가져와 : findAll()
		//=> 레포야 삭제 안된 게시글 전체를 가져와 : findAllByIsDeleted("N")
		//=> 레포야 삭제 안된 게시글 전체를 최신순으로 가져와 : findAllByIsDeletedOrderByIdDesc("N")
		List<Post> list = postRepository.findAllByIsDeletedOrderByIdDesc("N");
		
		return list;
	}

	public Post getPost(int 게시글번호) {
		//레포야 게시글 가져와. 번호 여기있어 => 게시글 없어? 예외 발생해
		Post post = postRepository.findById(게시글번호)
				.orElseThrow(()->new IllegalArgumentException("존재하지 않은 게시글입니다."));
		//게시글 삭제 됐어? 예외 발생해 
		if(post.getIsDeleted().equals("Y")) {
			throw new IllegalArgumentException("삭제된 게시글입니다.");
		}
		//게시글 반환
		return post;
	}

	public MessageResponse insertPost(PostRequest request, CustomUserDetails userDetails) {
		//로그인 했는지 확인
		if(userDetails == null) {
			throw new IllegalArgumentException("로그인이 필요한 서비스입니다.");
		}
		//게시글 항목 확인
		if( request == null 
			|| !request.validTitle()
			|| !request.validContent()
			|| !request.validBoardId()) {
			throw new IllegalArgumentException("입력되지 않은 항목이 있습니다.");
		}
		//유효한 게시판인지 체크
		Board board = boardRepository.findById(request.boardId())
				.orElseThrow(()->new IllegalArgumentException("없는 게시판입니다."));
		if(board == null) {
			throw new IllegalArgumentException("없는 게시판입니다.");
		}
		//사용자 체크
		boolean existsUser = memberRepository.existsById(userDetails.getUsername());
		if(!existsUser) {
			throw new IllegalArgumentException("등록되지 않은 사용자입니다.");
		}
		//게시글 엔티티 생성
		Post post = request.toPost(board, userDetails.getUsername());
		
		//게시글 등록
		postRepository.save(post);
		return new MessageResponse(true, "게시글을 동록했습니다.");
	}
	
}
