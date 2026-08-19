package kr.fast.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.boot.service.BoardService;
import lombok.AllArgsConstructor;

@RestController //@ResponseBody + @Controller
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
	
	private final BoardService boardService;
	
	// url : /api/admin/board
	// method : post를 처리하는 메서드를 추가 
	// 리턴 : "OK" 문자열을 리턴 
	@PostMapping("/board")
	public ResponseEntity<String> boardPost(){
		return ResponseEntity.ok("ok");
	}
	
}
