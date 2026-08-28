/* ========================
DOM 트리 구조 로딩 후 실행
======================== */ 
document.addEventListener("DOMContentLoaded",async e=>{
	const info = await getMyInfo();
	console.log(info)
});