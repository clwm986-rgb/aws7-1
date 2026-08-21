package kr.fast.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.boot.entity.Board;
import kr.fast.boot.service.BoardService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

	private final BoardService boardService;
	
	@GetMapping("/board")
	public ResponseEntity<Object> boardGet(){
		
		//서비스야. 게시판 목록을 가져와
		List<Board> list = boardService.getBoardList();
		
		return ResponseEntity.ok(list);
	}
}
