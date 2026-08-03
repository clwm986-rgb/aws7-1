package day09;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class Ex02_로또 {

	public static void main(String[] args) {
		/* 1~45 사이의 중복되지 않는 랜덤한 수 6자리를 맞추는 프로그램
		 * 1. 1~45사이의 중복되지 않은 랜덤한 수 6자리 생성
		 * 2. 1번에서 생성한 번호를 제외한 번호를 하나 랜덤으로 선택
		 * 3. 사용자가 1~45사이의 중복되지 않은 수를 입력
		 * 4. 당첨등수 확인
		 *   - 1등 : 당첨번호 6자리 일치
		 *   - 2등 : 당첨번호 5자리 일치 + 보너스 번호 일치
		 *   - 3등 : 당첨번호 5자리 일치
		 *   - 4등 : 당첨번호 4자리 일치
		 *   - 5등 : 당첨번호 3자리 일치
		 *   - 꽝 : 나머지
		 * */

		//중복되지 않은 1~45사이의 숫자 7개 생성
		ArrayList<Integer> lotto = createRandomArray(1, 45, 7);

		//앞에 6개는 로또 번호, 마지막 뒤는 보너스
		//remove(번지)는 삭제 후 삭제된 값을 돌려줌
		int bonus = lotto.remove(6);
		
		System.out.println("로또 번호 : " + lotto);
		System.out.println("보너스 : " + bonus);
		
		//사용자 입력(예외처리 안함)=>사용자가 항상 제대로 입력했다고 가정
		System.out.print("구매 번호 : ");
		ArrayList<Integer> userNums = inputNumbers(6);
		
		//일치하는 번호가 몇개인지
		int count = getCount(lotto, userNums);
		
		//보너스 번호 맞았는지
		boolean isBonus = checkBonus(bonus, userNums);

		//일치하는 번호 개수와 보너스 번호 일치 여부를 이용해서 등수 출력
		//2등이 출력되야 함
		printLottoResult(count, isBonus);
	}

	private static boolean checkBonus(int bonus, ArrayList<Integer> userNums) {
		//userNums에 bonum가 있으면 true를 없으면 false를 리턴
		//if(userNums.contains(bonus)) {
		//	return true;
		//}
		//return false;
		return userNums.contains(bonus);
	}

	private static int getCount(ArrayList<Integer> lotto, ArrayList<Integer> userNums) {
		int sameCount = 0;
		//향상된 for문으로 userNums에 있는 값들을 하나씩 꺼냄(num)
		for(int num : userNums) {
			//lotto에 num이 있는지 확인해서 있으면 개수를 1 증가
			if(lotto.contains(num)) {
				sameCount++;
			}
		}
		//개수를 리턴
		return sameCount;
	}

	private static ArrayList<Integer> inputNumbers(int size) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		for(int i = 0; i < size; i++) {
			int num = scan.nextInt();
			list.add(num);
		}
		return list;
	}

	private static ArrayList<Integer> createRandomArray(int min, int max, int size) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//min ~ max가 아니라 max~min 경우
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		
		//랜덤하게 나올 수 있는 수의 범위보다 size가 더 큰 경우=>무한루프에 빠지는 경우
		if(max - min + 1 < size) {
			throw new RuntimeException("랜덤한 숫자를 만들 수 없습니다.");
		}
		while(list.size() < size) {
			//min~max사이의 랜덤한 수 생성
			Random random = new Random();
			int r = random.nextInt(min, max+1);
			//없는 수이면 추가
			if(!list.contains(r)) {
				list.add(r);
			}
		}
		
		return list;
	}

	private static void printLottoResult(int count, boolean isBonus) {
		
		switch(count) {
		case 6:	
			System.out.println("1등입니다.");
			break;
		case 5:	
			if(isBonus) {
				System.out.println("2등입니다.");			
			}
			else {
				System.out.println("3등입니다.");
			}
			break;
		case 4:	
			System.out.println("4등입니다.");
			break;
		case 3:	
			System.out.println("5등입니다.");
			break;
		default:
			System.out.println("꽝");
		}
	}
	
	
}
