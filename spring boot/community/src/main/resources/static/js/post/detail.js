/* ========================
DOM 트리 구성이 완료되면 실행
======================== */ 
document.addEventListener("DOMContentLoaded", e=>{
	getPost();
});

/* ========================
게시글을 가져와 화면에 출력하는 함수
======================== */
async function getPost(){
	//url에 있는 게시글 번호를 가져옴
	const urlParams = new URLSearchParams(location.search);
	const postId = urlParams.get("num");
	try{
		//서버에 게시글을 요청하여 게시글을 가져와서 콘솔에 게시글을 출력하는 코드까지 작성하세요.
		//js, 컨트롤러, 서비스, 레포지토리 다 작성 
		
		//게시글을 가져옴
		//서버에 게시글 번호를 주고 게시글을 가져오라고 요청
		//url : /api/posts/게시글번호
		const response = await fetch(`/api/posts/${postId}`);
		//게시글 불러오기 실패
		if(!response.ok){
			const result = await response.text();
			alert(result);
			throw Error("게시글이 없거나 삭제 됨");
		}
		const result = await response.json();
		const post = result.post;
		const files= result.files;
		
		//가져온 게시글을 화면에 출력
		insertValue("[name=title]", post.title);
		insertValue("[name=writer]", post.memberId);
		insertValue("[name=boardName]", post.board.name);
		insertValue("[name=view]", post.viewCount);
		insertValue("[name=createdAt]", post.createdAt.slice(0,10));
		insertValue("[name=content]", post.content);
		document.querySelector(".up-count").textContent = post.upCount;
		document.querySelector(".down-count").textContent = post.downCount;
		
		//수정/추가버튼 보여주기/감추기
		visibleButtons(false);
		
		const 첨부파일박스 = document.querySelector("#files");
		//첨부파일 없으면
		if(!files || files.length == 0){
			첨부파일박스.innerHTML = `<div class="form-control">없음</div>`;
			return;
		}
		let html = '';
		//첨부파일 있으면 
		files.forEach(file=>{
			html += `
				<a 
					class="form-control" href="/api/upload/${file.savedName}" 
					download="${file.originalName}" >
					${file.originalName}
				</a>
			`;
		})
		첨부파일박스.innerHTML = html;
	}catch(e){
		console.error("게시글 가져오기 실패 : ", e);
	}
}
/* ========================
입력 요소의 value를 수정하는 함수
======================== */
function insertValue(selector, value){
	document.querySelector(selector).value = value;
}
/* ========================
게시글 수정/삭제 버튼을 보여줄지를 결정하는 함수
======================== */
function visibleButtons(visible){
	if(!visible){
		document.querySelector(".btns").innerHTML = '';
	}
}


