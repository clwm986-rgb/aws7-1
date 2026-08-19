package kr.fast.boot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.boot.repository.BoardRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;

}
