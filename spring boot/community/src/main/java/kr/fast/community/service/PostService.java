package kr.fast.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.fast.community.entity.Post;
import kr.fast.community.repository.PostRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public List<Post> getPosts() {
		//전체 게시글을 가져오는 코드를 작성 해보세요.
		
		return null;
	}
	
}
