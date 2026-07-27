package day04;

public class Ex09_클래스연습예제 {

	public static void main(String[] args) {
		
		/* 1학년 1반 1번 홍길동 학생 객체(인스턴스)를 생성하세요.*/
		Student std = new Student(1, 1, 1, "홍길동");
		// 홍길동 학생의 정보를 출력하세요.
		std.print();
		// 홍길동 학생의 국어, 영어, 수학점수를 100, 90, 80으로 변경
		std.updateScore(100, 90, 80);
		// 변경된 학생의 정복를 출력하세요.
		std.print();
	}

}
/* 학생의 성적을 관리하기 위한 학생 클래스를 만드세요.
 * - 클래스명 : Student 
 * - 학생의 학년, 반, 번호, 이름, 국어성적, 영어성적, 수학성적을 관리하는 필드를 선언하세요.
 * */

class Student{
	
	//필드
	int grade, classNum, num;
	String name;
	int korScore, engScore, mathScore;
	
	//메서드
	/* 기능 : 학생의 학년, 반, 번호, 이름, 국어, 영어, 수학 성적을 콘솔에 출력하는 메서드
	 * 매개변수 : 없음
	 * 리턴타입 : 없음 => void
	 * 메서드명 : print
	 * */
	void print() {
		System.out.println(grade + "학년 " + classNum +"반 " + num + "번 " + name);
		System.out.println("국어 성적 : " + korScore);
		System.out.println("영어 성적 : " + engScore);
		System.out.println("수학 성적 : " + mathScore);
	}
	/* 기능 : 주어진 국어, 영어, 수학 성적으로 학생의 국어, 영어, 수학 성적을 바꾸는 메서드
	 * 매개변수 : 국어, 영어, 수학 성적 => int korScore1, int engScore1, int mathScore1
	 * 리턴타입 : 없음 => void
	 * 메서드명 : updateScore
	 * */
	void updateScore(int korScore1, int engScore1, int mathScore1) {
		korScore = korScore1;
		engScore = engScore1;
		mathScore = mathScore1;
	}
	
	//생성자
	//학생의 기본 학년, 반, 번호는 1학년 1반 1번
	Student() {
		grade = 1;
		classNum = 1;
		num = 1;
	}

	public Student(int grade1, int classNum1, int num1, String name1) {
		grade = grade1;
		classNum = classNum1;
		num = num1;
		name = name1;
	}
	
}


