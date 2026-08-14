package kr.fast.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


//@RestController = @Controller + @ResponseBody
//@Controller : 나 컨트롤러 역할 할게 
//@ResponseBody : 내가 보낸 결과를 뷰리졸버를 거치지말고 그냥 내보내. 
//컨트롤러에 있는 모든 메서드가 비동기 통신할 때 @RestController 사용 
@RestController 
public class HomeController {

	
	@GetMapping("/")
	public String main() {
		System.out.println("메인페이지");
		return "index";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	//@PathVariable : url 경로에 있는 값을 가져올 때 사용
	//url에 {변수명} 형태로 설정하면 해당 변수명으로 가져올 수 있음 
	@GetMapping("/page/{page}")
	public String page(@PathVariable("page")int page) {
		
		return page + "페이지 내용입니다.";
	}
	
	@GetMapping("/recieve/object")
	public Map<String, Object> recieveObject(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("age", 21);
		map.put("name", "홍길동");
		return map;
	}
}
