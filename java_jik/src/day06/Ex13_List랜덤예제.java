package day06;

import java.util.ArrayList;
import java.util.Random;

public class Ex13_List랜덤예제 {

	public static void main(String[] args) {
		/* 1~9사이의 랜덤한 수 3개를 생성하여 ArrayList에 저장하고 콘솔에 출력하세요.
		 * */

		
		Random random = new Random();
		int min = 1, max = 9;
		
		//ArrayList를 생성(정수)
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//리스트에 3개가 저장될때까지 반복
		while(list.size() < 3) {
			//랜덤한수를 생성
			int num = random.nextInt(min, max + 1);// 1~9사이의 랜덤한 수를 만듬
			//리스트에 추가
			list.add(num);
			
		}
		//콘솔에 리스트를 출력
		System.out.println(list);
	}

}
