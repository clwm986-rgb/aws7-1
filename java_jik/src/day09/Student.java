package day09;

import java.util.Objects;

public class Student {
	
	private int grade, classNum, num;
	private String name;
	private int kor, eng, math;
	
	//학생의 학년, 반, 번호, 이름을 이용하여 학생 정보를 생성
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}

	@Override
	public String toString() {
		return grade + "학년 " + classNum + "반 " + num + "번 " + name 
				+ "[국어 : " + kor
				+ ", 영어 : " + eng 
				+ ", 수학 : " + math + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return classNum == other.classNum && grade == other.grade && num == other.num;
	}
	
	
	
}
