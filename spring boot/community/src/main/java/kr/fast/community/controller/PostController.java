package kr.fast.community.controller;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.fast.community.dto.MessageResponse;
import kr.fast.community.dto.PageResponse;
import kr.fast.community.dto.PostRequest;
import kr.fast.community.entity.Post;
import kr.fast.community.security.CustomUserDetails;
import kr.fast.community.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("")
	public ResponseEntity<Object> get(
			@RequestParam(required = false, defaultValue = "all", name="type")String type,
			@RequestParam(required = false, defaultValue = "", name="keyword")String keyword,
			@PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC)
				Pageable pageable){
		PageResponse<Post> pageResponse = postService.getPosts(type, keyword, pageable);
		return ResponseEntity.ok(pageResponse);
	}
	
	@GetMapping("/{게시글번호}")
	public ResponseEntity<Object> idGet(@PathVariable("게시글번호")int 게시글번호){
		try {
			//서비스야 게시글 가져와. 번호 여기있어.
			Post post = postService.getPost(게시글번호);
			return ResponseEntity.ok(post);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@PostMapping("")
	public ResponseEntity<Object> post(
			@RequestPart("post") PostRequest request, //화면에서 보낸 게시글 정보
			@RequestPart(value="files", required = false) List<MultipartFile> files,
			@AuthenticationPrincipal CustomUserDetails userDetails //로그인한 회원 정보
		){
		MessageResponse ms;
		try {
			ms = postService.insertPost(request, userDetails, files);			
		}catch (Exception e) {
			ms = new MessageResponse(false, e.getMessage());
		}
		return ResponseEntity.ok(ms);
	}
}
