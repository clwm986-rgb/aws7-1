package kr.fast.boot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.boot.repository.PostRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	
	
}
