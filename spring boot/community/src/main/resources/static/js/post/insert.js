/* ========================
DOM 트리 구성이 완료되면 실행
======================== */ 
document.addEventListener("DOMContentLoaded", e=>{
	getBoardsAndDisplay();
});

/* ========================
- 게시판 목록을 가져와서 화면에 배치
======================== */
async function getBoardsAndDisplay(){
	try{
		//게시판 목록 가져오기
		const response = await fetch(`/api/boards`, {
			method : "post",
			headers : {
				"Content-Type" : "application/json"
			},
			body : JSON.stringify(data)
		});
		
		const result = await response.json();
		
		//게시판 목록을 화면에 배치하기
		let html = '<option value="">게시판을 선택하세요.</option>';
		result.forEach(board=>{
			html += `
				<option value="${board.id}">${board.name}</option>
			`;
		});
		document.querySelector("[name=boardId]").innerHTML = html;
	}catch(e){
		console.error(e);
	}
}


async function insertPost(e){
	e.preventDefault();
	
	//입력값(제목, 내용, 게시판) 체크
	if(data.title.trim().length == 0){
		alert("제목을 입력하세요.");
		return;
	}
	
	if(data.boardId.length == 0){
		alert("게시판을 선택하세요.");
		return;
	}
	
	if(data.content.trim().length == 0){
		alert("내용을 입력하세요.");
		return;
	}
	
	try{
		//서버로 게시글 등록 요청
		const response = await authFetch(`/api/posts`, {
			method : "post",
			headers : {
				"Content-Type" : "application/json"
			},
			body : JSON.stringify(data)
		});
		
		const result = await response.json();
		alert(result.message);
		if(result.success){
			location.href = "/post/list.html";
		}
		
	}catch(e){
		console.error(e);
	}
}
const data = {
	title : '',
	content : '',
	boardId : ''
}
/* ========================
- 입력태그(input, seletec, textarea등)에 입력하면 입력된 값들을 가져와서 
  data 객체에 저장하도록 하는 함수
- 단, data 객체는 전역으로 선언이 되어 있어야 한다.
======================== */
function changeInput(e){
	//객체에 있는 값들을 변수에 쉽게 저장하는 방법
	const {name, value}	= e.target;
	//객체에 있는 속성의 값을 변경
	data[name] = value;
}