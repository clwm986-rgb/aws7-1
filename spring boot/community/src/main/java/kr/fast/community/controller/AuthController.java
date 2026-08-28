package kr.fast.community.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.community.dto.LoginRequest;
import kr.fast.community.dto.MessageResponse;
import kr.fast.community.dto.SignupRequest;
import kr.fast.community.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest){
		
		try {
			MessageResponse messageResponse = authService.signup(signupRequest);
			return ResponseEntity.ok(messageResponse);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(false, e.getMessage()));
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//서비스야 사원증 발급해줘. 아이디 비번 줄게
			String accessToken = authService.login(request);
			
			return ResponseEntity.ok("{}");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(false, e.getMessage()));
		}
		
	}
}
