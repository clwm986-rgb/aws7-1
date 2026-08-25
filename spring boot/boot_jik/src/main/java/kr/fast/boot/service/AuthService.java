package kr.fast.boot.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.boot.dto.LoginDTO;
import kr.fast.boot.dto.TokenDTO;
import kr.fast.boot.entity.Member;
import kr.fast.boot.repository.MemberRepository;
import kr.fast.boot.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Log4j2
public class AuthService {
	
	private final MemberRepository memberRepository;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	private final JwtProvider jwtProvider;
	
	@Transactional
	public TokenDTO login(LoginDTO dto) {
		//회원 정보를 가져옴
		Member user = memberRepository.findById(dto.username())
				.orElseThrow(
					()-> new IllegalArgumentException("아이디나 비번이 일치하지 않습니다."));
		//회원의 비번과 화면에서 입력한 비번을 비교해서 다르면 예외 발생
		//matches(원래 비번, 암호화된비번)
		if(!passwordEncoder.matches(dto.password(), user.getPassword())) {
			throw new IllegalArgumentException("아이디나 비번이 일치하지 않습니다.");
		}
		//같으면 토큰을 생성
		String token = jwtProvider.createToken(user.getId(), user.getRole());
		return new TokenDTO("accessToken", token);
	}
	
}
