package kr.fast.community.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
