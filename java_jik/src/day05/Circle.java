package day05;

public class Circle extends Shape {

	private int centerX, centerY;//중심점
	private int r;//반지름
	
	public Circle(int centerX, int centerY, int r) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.r = r;
		//원을 감싸는 사각형
		this.left = centerX - r;
		this.right = centerX + r;
		this.top = centerY - r;
		this.bottom = centerY + r;
	}
	
	@Override
	public void draw() {
		System.out.println("원입니다.");
		System.out.println("중심점 : (" + centerX + "," + centerY + ")" );
		System.out.println("반지름 : " + r);
	}
	
	
	
	
}
