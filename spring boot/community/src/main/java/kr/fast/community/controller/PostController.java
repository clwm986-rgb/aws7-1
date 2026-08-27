package kr.fast.community.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.community.entity.Post;
import kr.fast.community.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("")
	public ResponseEntity<Object> get(){
		List<Post> list = postService.getPosts();
		return ResponseEntity.ok(list);
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
}
