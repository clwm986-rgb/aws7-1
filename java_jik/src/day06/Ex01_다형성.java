package day06;

public class Ex01_다형성 {

	public static void main(String[] args) {
		/* 다형성 p.268
		 * - 하나의 코드가 여러 자료형으로 구현되어 실행되는 것
		 * - 클래스 형변환을 이해해야 됨
		 * 
		 * 매개변수의 다형성
		 * - 메서드의 매개 변수로 자식 클래스들이 들어가야 하고, 메서드 안 내용이 같은 경우
		 *   부모 클래스를 매개변수로 지정하는 것
		 * 객체의 다형성
		 * - 부모 클래스의 객체를 이용하여 다양한 자식 클래스의 객체를 관리할 수 있음
		 * 
		 * intanceof
		 * - 객체가 지정된 클래스로 형변환이 가능한지를 true 또는 false로 알려줌
		 * - 사용법
		 *   - 객체 instanceof 클래스명
		 */

		AirConditioner [] aircons = new AirConditioner[10];
		Tv [] Tvs = new Tv[10];
		int acCount = 0;//에어컨 대수
		int tvCount = 0;//tv 대수
		
		AirConditioner ac1 = new AirConditioner();//공장에서 에어컨 만듬
		AirConditioner ac2 = new AirConditioner();//공장에서 에어컨 만듬
		aircons[acCount] = ac1; //매장에 에어컨 추가
		acCount++; //매장에 있는 에어컨 대수 1 증가
		aircons[acCount] = ac2; //매장에 에어컨 추가
		acCount++; //매장에 있는 에어컨 대수 1 증가
		
		//객체의 다형성
		//다형성을 이용하면 한 종류를 이용하여 여러 종류의 클래스를 관리할 수 있다
		Electronics [] electronics = new Electronics[20];
		int eCount = 0;//전자제품수
		
		electronics[eCount] = ac1;
		eCount++;
		electronics[eCount] = ac2;
		eCount++;
		
		Tv tv1 = new Tv();
		electronics[eCount] = tv1;
		eCount++;
		
		check(ac1);
		check(tv1);
	}
	
	//Tv가 잘 켜지고 꺼지는지 테스트
	/*public static void check(Tv tv) {
		tv.turnOn();
		tv.turnOff();
	}
	//에어컨
	public static void check(AirConditioner ac) {
		ac.turnOn();
		ac.turnOff();
	}*/
	//매개변수의 다형성으로 위 2개의 메서드를 한개의 메서드로 대체
	public static void check(Electronics et) {
		et.turnOn();
		et.turnOff();
	}

}
//전자 제품 클래스
class Electronics{
	//전원
	private boolean power;
	
	//전원 켜기
	public void turnOn() {
		//꺼져 있을 때만 켜기
		if(!power) {
			power = !power;
			System.out.println("전원이 켜졌습니다.");
		}
	}
	//전원 끄기
	public void turnOff() {
		//켜져 있을 때만 끄기
		if(power) {
			power = !power;
			System.out.println("전원이 꺼졌습니다.");
		}
	}
}
//Tv 클래스
class Tv extends Electronics{
	
	private int channel;
	private int volumn;
	
}

class AirConditioner extends Electronics{
	
	private int windPower;
	private int temperature;
	
}














