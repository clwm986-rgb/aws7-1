package day09;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

	//학생 목록
	List<Student> list;

	//학생 목록이 있으면 가져와서 관리
	public StudentManager(List<Student> list) {
		//넘겨준 학생 목록이 없으면 새 학생 목록을 만듬
		if(list == null) {
			this.list = new ArrayList<Student>();
			return;
		}
		//학생 목록이 있으면 받아와서 사용
		this.list = list;		
		
	}

	public boolean insertStudent(Student std) {
		//리스트에 std가 등록됐지는 확인해서 등록되어 있으면 false를 리턴
		//Student.equals()를 오버라이딩해서 처리해야 함
		if(list.contains(std)) {
			return false;
		}
		//등록이 안되어 있으면 리스트에 학생을 추가하고 true를 리턴
		//List.add()는 추가한 후 추가하면 true를 리턴, 
		//return list.add(std);
		list.add(std);
		return true;
	}

	public void printStudents() {
		for(Student std : list) {
			System.out.println(std);
		}
	}

	public boolean updateScore(Student std, int kor, int eng, int math) {
		if(std == null) {
			return false;
		}
		//등록된 학생이 아니면
		//indexOf는 몇번지에 있는지 알려줌. -1 => 없음
		int index = list.indexOf(std);
		if(index == -1) {
			return false;
		}
		//index 번지에 있는 학생 성적을 수정
		//list.get(index) => 수정할 학생 정보
		list.get(index).updateScore(kor, eng, math);
		return false;
	}
	
	//기능들
	
	
}
