package day06;

import java.util.Random;
import java.util.Scanner;

public class Ex11_UpDown예제 {

	public static void main(String[] args) {
		
		//임의의 숫자를 정함
		Random random = new Random();
		int min = 1, max = 100;
		int num = random.nextInt(min, max + 1);// 1~100사이의 랜덤한 수를 만듬
		//System.out.println("테스트용 정답 : " + num);
		Scanner scan = new Scanner(System.in);
		//반복
		for( ; ; ) {
			//숫자를 불러야 함 => 숫자를 입력
			System.out.print("입력 : ");
			int user = scan.nextInt();
			
			//입력한 숫자가 1보다 작거나 100보다 크면 판별을 하지 않도록 구현
			if(user < 1 || user > 100) {
				System.out.println("숫자는 1~100사이의 수를 입력해야 합니다.");
				continue;
			}
			
			
			//Up인지 Down인 정답인지 알려줌
			//user가 num보다 크면 Down이라고 출력하고
			if(user > num) {
				System.out.println("Down");
			}
			//user가 num보다 작으면 Up이라고 출력하고
			else if(user < num) {
				System.out.println("Up");
			}
			//아니면 정답입니다라고 출력하고 반복문을 종료
			else {
				System.out.println("정답");
				break;
			}
		}
	}

}
