package kr.fast.community.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
