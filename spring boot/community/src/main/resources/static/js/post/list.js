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
		
		if(!response.ok){
			throw Error("서버 상태 이상");
		}
		//가져오 게시글들을 이용하여 html코드로 구성
		const result = await response.json();
		
		let str = '';
		result.forEach(post=>{
			str += `
				<tr>
			      <td>${post.id}</td>
			      <td><a href="/post/detail.html?num=${post.id}">${post.content}</a></td>
			      <td>${post.memberId}</td>
			      <td>${post.createdAt.slice(0,10)}</td>
			      <td>${post.upCount}/${post.downCount}</td>
			    </tr>
			`;
		});
		
		//table태그 안 body에 html코드를 덮어쓰기
		const 테이블바디 = document.querySelector(".table tbody");
		테이블바디.innerHTML = str;
	}catch(e){
		console.error("게시글 목록 불러오기 실패 : ", e);
	}
}