package day04;

import java.util.Scanner;

public class Ex06_메서드연습예제 {

	public static void main(String[] args) {
		/* 1. 두 정수와 산술 연산자를 입력받는 코드를 작성하세요.
		 * 정수1 입력 : 1
		 * 산술 연산자 입력 : +
		 * 정수2 입력 : 2
		 * */
		Scanner scan = new Scanner(System.in);
		System.out.print("정수1 입력 : ");
		int num1 = scan.nextInt();
		
		System.out.print("산술 연산자 입력 : ");
		char operator = scan.next().charAt(0);
		
		System.out.print("정수2 입력 : ");
		int num2 = scan.nextInt();
		
		/* 2. 연산자가 산술 연산자이면 산술 연산 결과를 출력하는 코드를 작성하세요.ㅣ
		 * + : 더하결과, - : 뺀 결과, / : 나눈 결과(소수점포함), * : 곱한 결과, % : 나머지
		 * 3. 산술연산자가 아니면 연산자는 산술연산자가 아닙니다를 출력하는 코드를 작성하세요.
		 * */
		
		/* 4. 위 코드를 활용해서 두 정수와 산술연산자가 주어졌을 때 결과를 출력하는 메서드를 만드세요.
		 * */
		caculate(num1, num2, operator);
	}
	
	/* 기능 : 두 정수와 산술 연산자가 주어지면 산술연산 결과를 콘솔에 출력하는 메서드
	 * 콘솔에서 두정수와 산술 연산자를 입력받아 산술연산 결과를 콘솔에 출력하는 메서드
	 * 매개변수 : 두정수와 산술연산자 => int num1, int num2, char operator
	 * 리턴타입 : 없음 => void
	 * 메소드명 : caculate
	 * */
	static void caculate(int num1, int num2, char operator) {
		switch(operator) {
		case '+':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 + num2));
			break;
		case '-':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 - num2));
			break;
		case '*':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 * num2));
			break;
		case '/':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 / (double)num2));
			break;
		case '%':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 % num2));
			break;
		default:
			System.out.println(operator+"는 산술 연산자가 아닙니다.");
		}
	}
	/* 기능 : 콘솔에서 두정수와 산술 연산자를 입력받아 산술연산 결과를 콘솔에 출력하는 메서드
	 * 매개변수 : 없음
	 * 리턴타입 : 없음 => void
	 * 메소드명 : caculate2
	 * */
	static void calculate2() {
		Scanner scan = new Scanner(System.in);
		System.out.print("정수1 입력 : ");
		int num1 = scan.nextInt();
		
		System.out.print("산술 연산자 입력 : ");
		char operator = scan.next().charAt(0);
		
		System.out.print("정수2 입력 : ");
		int num2 = scan.nextInt();
		
		switch(operator) {
		case '+':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 + num2));
			break;
		case '-':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 - num2));
			break;
		case '*':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 * num2));
			break;
		case '/':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 / (double)num2));
			break;
		case '%':
			System.out.println("" + num1 + " " + operator + " " + num2 + " = " + (num1 % num2));
			break;
		default:
			System.out.println(operator+"는 산술 연산자가 아닙니다.");
		}
	}
}
