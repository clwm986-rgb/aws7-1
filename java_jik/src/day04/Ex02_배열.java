package day04;

public class Ex02_배열 {

	public static void main(String[] args) {
		
		/* 자바에서는 배열보다 리스트를 많이 쓰기 때문에 배열은 기본 사용법만 기억하자. 
		 * => 리스트는 js의 배열처럼 기능들이 있어서
		 * */
		/* 배열 p.211
		 * - 같은 형태의 자료를 여러개 관리 할 때 사용
		 *   => js는 한 배열에 숫자도 넣고 문자도 넣고 객체도 넣을 수 있음
		 *      let arr = [1, 'a', {}]
		 *   => 자바에서는 숫자들만 넣거나 문자들만 넣거나 문자열만 넣어야 함
		 * - 예 : 학생들의 국어 성적을 관리, 학생 정보를 관리
		 * - 연속된 공간에 배열을 생성 => 탐색이 빠름
		 * - 왜?
		 *   - 반복문을 사용할 수 있기 때문
		 * - 선언 방법 p.212
		 *   1. 자료형 [] 배열명 = new 자료형[크기];
		 *   2. 자료형 배열명 [] = new 자료형[크기];  
		 *   3. 자료형 [] 배열명 = {값1, 값2, 값3};
		 *      => 배열을 선언과 동시에 초기화할때만 사용
		 *   4. 자료형 [] 배열명 = new int[]{값1, 값2, 값3};
		 *      => 언제든 다시 초기화가 가능
		 *   1과 2는 약간 차이가 있음
		 * - 배열 사용 p.213
		 *   - 배열명[번지]를 이용하여 값을 가져오거나 수정
		 *   - 번지는 0번지부터 크기-1번지까지 가능
		 *   - 잘못된 번지를 입력하면 예외가 발생
		 * - 배열의 길이
		 *   - 배열명.length
		 * */
		//korScores1과 num1은 int를 공통으로 사용 => num1은 int num1;//배열이 아님
		int korScores1 [], num1;
		//korScores2와 num2는 int[]을 공통으로 사용 => num2는 int [] num2;//배열
		int [] korScores2, num2;
		
		int [] korScores3 = {1, 2, 3};
		//korScores3 = {4,5,6};//에러 발생. 선언과 동시에 초기화 할때문 사용
		
		int [] koreScores4 = new int[]{1,2,3};
		koreScores4 = new int[] {4,5,6};
		
		koreScores4[0] = 7;
		koreScores4[1] = 8;
		koreScores4[2] = 9;
		//koreScores4[3] = 10;//범위를 벗어나서 예외 발생
		
		for(int i = 0; i < koreScores4.length; i++) {
			System.out.println(koreScores4[i]);
		}
		
	}
}
