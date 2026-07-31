package day08;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

//소켓을 이용하여 문자열을 주고 받는 기능이 있는 클라이언 클래스
public class Ex08_Client {

	private String id;
	private Socket socket;
	
	//받기
	public void recieve() {
		Thread t = new Thread(()->{
			System.out.println("[" + id + "]님의 수신 기능이 활성화 됐습니다.");
			//무한루프로 특정 단어가 입력될때까지 받아서 콘솔에 출력
			try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
				
				Scanner scan = new Scanner(System.in);
				
				while(true) {
					String msg = ois.readUTF();	
					if(msg.equals("EXIT")) {
						break;
					}
					System.out.println(msg);
				}
			}
			catch (Exception e) {
				
			}
			finally {			
				//System.out.println("[" + id + "]님의 수신 기능이 종료 됐습니다.");
				close();
			}
			
			
		});
		t.start();
	}
	
	//보내기
	public void send() {
		
		Thread t = new Thread(()->{
			System.out.println("[" + id + "]님의 송신 기능이 활성화 됐습니다.");
			//무한루프로 특정 단어가 입력될때까지 입력해서 전송
			try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
				
				Scanner scan = new Scanner(System.in);
				
				while(true) {
					String msg = scan.nextLine();
					oos.writeUTF(id + " : " + msg);
					oos.flush(); //버퍼에 있는 내용을 밀어서 전송		
					if(msg.equals("EXIT")) {
						break;
					}
				}
			}
			catch (Exception e) {
				
			}
			finally {
				//System.out.println("[" + id + "]님의 송신 기능이 종료 됐습니다.");				
				close();
			}
			
		});
		t.start();
	}
	
	public Ex08_Client(String id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}
	
	private void close() {
		try {
			if(socket != null && !socket.isClosed()) {
				socket.close();
				System.out.println("[통신을 종료합니다.]");
			}
		}catch(Exception e) {
			
		}
	}
}
