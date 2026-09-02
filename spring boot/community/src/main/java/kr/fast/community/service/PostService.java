package kr.fast.community.service;


import java.util.List;

import kr.fast.community.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import kr.fast.community.dto.CommentRequest;
import kr.fast.community.dto.MessageResponse;
import kr.fast.community.dto.PageResponse;
import kr.fast.community.dto.PostRequest;
import kr.fast.community.entity.Board;
import kr.fast.community.entity.Comment;
import kr.fast.community.entity.File;
import kr.fast.community.entity.Post;
import kr.fast.community.repository.BoardRepository;
import kr.fast.community.repository.FileRepository;
import kr.fast.community.repository.MemberRepository;
import kr.fast.community.repository.PostRepository;
import kr.fast.community.security.CustomUserDetails;
import kr.fast.community.utils.FileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	private final FileRepository fileRepository;
	
	@Value("${file.path}")
	private String uploadFilePath;

	@PostConstruct //의존성 주입 완료 후 실행
	public void init() {
		//서버에 업로드할 경로가 없으면 경로를 생성
		java.io.File dir = new java.io.File(uploadFilePath);
		//해당 경로가 없으면 
		if(!dir.exists()) {
			//해당 경로에 필요한 폴더들을 만듬
			dir.mkdirs();
		}
	}

	public PageResponse<Post> getPosts(String type, String keyword, Pageable pageable) {
		Page<Post> page;
		//검색어 없으면 타입에 상관없이 전체 검색
		if(keyword == null || keyword.isBlank()) {
			page = postRepository.findAllByIsDeletedContaining("N", pageable);		
		}
		else if("title".equals(type)) {
			page = postRepository.findAllByIsDeletedAndTitleContaining("N",  keyword, pageable);
		}
		else if("writer".equals(type)) {
			page = postRepository.findAllByIsDeletedAndMemberIdContaining("N",  keyword, pageable);
		}
		else{
			page = postRepository.findAllByIsDeletedContaining("N", pageable);
		}
		return new PageResponse<Post>(page, 3);
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

	public MessageResponse insertPost(PostRequest request, CustomUserDetails userDetails, List<MultipartFile> files) {
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
		Post savedPost = postRepository.save(post);
		
		if(files != null) {
			//첨부파일을 등록
			//1. 첨부파일을 서버에 업로드
			for(MultipartFile file : files) {
				String savedName = FileUtils.saveFile(uploadFilePath, file);		
				String originalName = file.getOriginalFilename();
	
				//2. 첨부파일을 이용하여 db에 저장
				//2-1. File 엔티티 객체를 생성
				File fileEntity = new File(originalName, savedName, savedPost.getId());
				//2-2. 저장
				fileRepository.save(fileEntity);
			}
		}
		return new MessageResponse(true, "게시글을 등록했습니다.");
	}

	public List<File> getFiles(int 게시글번호) {
		
		return fileRepository.findAllByPostId(게시글번호);
	}

	public MessageResponse insertComment(int 게시글번호, CommentRequest request, CustomUserDetails userDetails) {
		//게시글 존재 확인
		Post post = postRepository.findById(게시글번호)
				.orElseThrow(()->new RuntimeException("게시글이 존재하지 않습니다."));
		
		if(post == null || post.getIsDeleted().equals("Y") ) {
			return new MessageResponse(false, "게시글이 존재하지 않습니다.");
		}
		//사용자 확인(로그인 했는지 안했는지)
		if(userDetails == null || userDetails.getUsername().isEmpty()) {
			return new MessageResponse(false, "로그인이 필요한 서비스입니다.");
		}
		//댓글 내용 확인
		if(request == null || request.content() == null || request.content().isBlank()) {
			return new MessageResponse(false, "댓글을 입력하세요.");
		}
		
		//댓글 등록
		//1. 엔티티 생성
		Comment comment = 
				new Comment(
						request.content(), //댓글 내용
						post.getId(), //게시글번호
						userDetails.getUsername(),//작성자
						null);//대댓여부. null : 댓글, null이 아니면 대댓
		System.out.println(comment);
		//2. 저장
		commentRepository.save(comment);
		return new MessageResponse(true, "댓글을 등록했습니다.");
	}

	public PageResponse<Comment> getComments(int postId, Pageable pageable) {
		//게시글이 있는지 확인
		Post post = postRepository.findByIdAndIsDeleted(postId, "N");
		
		if(post == null) {
			throw new RuntimeException("존재하지 않은 게시글입니다.");
		}
		
		Page<Comment>page = commentRepository.findAllByPostId(postId, pageable);
		return new PageResponse<Comment>(page, 3);
	}
	
}
