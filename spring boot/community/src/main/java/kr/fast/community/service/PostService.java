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

	public Post getPost(int 게시글번호) {
		//레포야 게시글 가져와. 번호 여기있어 => 게시글 없어? 예외 발생해
		Post post = postRepository.findById(게시글번호)
				.orElseThrow(()->new IllegalArgumentException("존재하지 않은 게시글입니다."));
		//게시글 삭제 됐어? 예외 발생해 
		if(post.getIsDeleted().equals("Y")) {
			throw new IllegalArgumentException("삭제된 게시글입니다.");
		}
		//게시글 반환
		return post;
	}
	
}
