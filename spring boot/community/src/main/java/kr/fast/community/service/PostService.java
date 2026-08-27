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
		//레포야 게시글 전체를 가져와 : findAll()
		//=> 레포야 삭제 안된 게시글 전체를 가져와 : findAllByIsDeleted("N")
		//=> 레포야 삭제 안된 게시글 전체를 최신순으로 가져와 : findAllByIsDeletedOrderByIdDesc("N")
		List<Post> list = postRepository.findAllByIsDeletedOrderByIdDesc("N");
		
		return list;
	}
	
}
