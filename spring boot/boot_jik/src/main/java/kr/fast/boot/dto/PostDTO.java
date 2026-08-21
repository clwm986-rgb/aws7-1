package kr.fast.boot.dto;

public record PostDTO(
		String title, 
		String content, 
		Integer boardId, 
		String writer) {

}
