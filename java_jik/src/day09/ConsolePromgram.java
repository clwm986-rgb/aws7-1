package day09;

/* 콘솔 프로그램에 들어갈 기능들을 가진 인터페이스 */
public interface ConsolePromgram {

	//메뉴 출력 기능
	void printMenu();
	//선택한 메뉴를 실행하는 기능
	void runMenu(int menu);
	//프로그램 종료 문구를 출력하는 기능
	void printExit();
	//프로그램 실행 전 초기 셋팅을 하는 기능
	void init();
	//저장하기
	default void save(String fileName, Object object) {
		
	}
	//불러오기
	default Object load(String fileName) {
		return null;
	}
	//프로그램 실행
	void run();
	
	
}
