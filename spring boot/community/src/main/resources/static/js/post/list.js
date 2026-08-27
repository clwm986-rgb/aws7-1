/* ========================
DOM 트리 구성이 완료되면 실행
======================== */ 
document.addEventListener("DOMContentLoaded", e=>{
	getPosts();
});

/* ========================
게시글 목록을 불러와서 화면에 배치하는 함수
======================== */
async function getPosts(){
	
	//서버에 게시글 목록을 요청해서 화면에 배치하는 작업
	
	try{
		//서버에 게시글 목록을 요청
		//url : /api/posts
		//method : get
		/*const response = await fetch("url", {
			method : "방식",
			headers : {},
			body : 보낼값
		});*/
		const response = await fetch("/api/posts");
		
		//가져오 게시글들을 이용하여 html코드로 구성
		
		//table태그 안 body에 html코드를 덮어쓰기
	}catch(e){
		console.error("게시글 목록 불러오기 실패 : ", e);
	}
}