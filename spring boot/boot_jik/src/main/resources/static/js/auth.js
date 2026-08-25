async function getMyInfo(){
	//토큰으로 회원 정보 가져오기
	const token = localStorage.getItem("accessToken");
	
	if(!token){
		console.log("로그인이 필요합니다.");
		return;
	}
	//토큰을 서버에 전송하여 회원 정보 가져옴
	try{
		const response = await fetch("/api/auth/me", {
			method : "get",
			headers : {
				"Authorization" : `Bearer ${token}`
			}
		});
		
		//토큰이 만료되서 인증에 실패한 경우
		if(response.status === 401 || response.status === 403){
			console.log("인증이 만료되거나 권한이 없습니다.");
			localStorage.removeItem("accessToken");
			return null;
		}
		
		const result = await response.json();
		return result;
	}catch(e){
		console.error(e);
		return null;
	}
}