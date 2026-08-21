package kr.fast.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.boot.dto.PostDTO;
import kr.fast.boot.entity.Board;
import kr.fast.boot.entity.Post;
import kr.fast.boot.service.BoardService;
import kr.fast.boot.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

	private final BoardService boardService;
	
	private final PostService postService;
	
	@GetMapping("/board")
	public ResponseEntity<Object> boardGet(){
		
		//서비스야. 게시판 목록을 가져와
		List<Board> list = boardService.getBoardList();
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> post(@RequestBody PostDTO dto){
		try {
			//서비스야 게시글 정보 줄테니 등록해줘.
			//서비스야.게시글등록해줘(게시글정보);
			postService.insertPost(dto);
			return ResponseEntity.ok("게시글을 등록했습니다.");
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping("")
	public ResponseEntity<Object> get(){
		//게시글 목록 = 서비스야.게시글목록가져와();
		List<Post> list = postService.getPostList();
		return ResponseEntity.ok(list);
		
	}
}
