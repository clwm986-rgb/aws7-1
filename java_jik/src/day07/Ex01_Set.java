package day07;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Ex01_Set {

	public static void main(String[] args) {
		/* Set
		 * - 중복 허용 X => 순서 보장 X
		 * */
		//중복되지 않은 3개의 숫자를 생성(1~9)
		HashSet<Integer> set = new HashSet<Integer>();
		int min = 1, max = 9;
		
		Random random = new Random();
		//반복 : 3개가 저장될때까지
		while(set.size() < 3) {
			//랜덤 수 생성
			int num = random.nextInt(min, max + 1);
			//셋에 저장
			set.add(num);
		}
		//셋으로 만든 중복되지 않은 3정수를 리스트로 변환
		ArrayList<Integer> list = new ArrayList<Integer>(set);
		
		//숫자들을 섞음
		Collections.shuffle(list);
		
		System.out.println(list);
	}

}
