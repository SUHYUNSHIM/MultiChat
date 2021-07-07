package login;
//서버
import java.net.*;
import java.io.*;
import java.util.*;



class ServerClass {

	 ArrayList<ThreadServerClass> threadList = new ArrayList<ThreadServerClass>();
	 ArrayList<String> memberList = new ArrayList<String>();
	 
	Socket socket;
	DataOutputStream outputStream;
	String nickname;
	
	
	public ServerClass(int portno) throws IOException {//생성자
	//public ServerClass() throws IOException {

		Socket s1 = null;
		
		//ServerSocket ss1 = new ServerSocket(6788);
		ServerSocket ss1 = new ServerSocket(portno);
		System.out.println("서버가동 중");
		
		
		
	while (true) {
		s1 = ss1.accept();
	System.out.println("접속주소: " + s1.getInetAddress() + " , 접속포트: " + s1.getPort());
			ThreadServerClass tServer1 = new ThreadServerClass(s1);
			tServer1.start();////////
            //ThreadServerClass run 호출
			
			threadList.add(tServer1);//컬렉션 add
			System.out.println("접속자수 : " + threadList.size()+"명");
		}//whle-end 

	}	
		


		
	class ThreadServerClass extends Thread {
		Socket socket1;
		DataInputStream inputStream;
		DataOutputStream outputStream;

		public ThreadServerClass(Socket s1) throws IOException {
			socket1 = s1;
			inputStream = new DataInputStream(s1.getInputStream());
			outputStream = new DataOutputStream(s1.getOutputStream());
		}
		
		
		@Override
		public void run() { //remember !!!!!!  
			String nickname = "";
			try {
				if (inputStream != null) {
					nickname = inputStream.readUTF();
					sendChat(nickname + "님 입장 ");
					memberList.add(nickname.substring(2, nickname.length()));
				}		
				while (inputStream != null) {
					// System.out.println(inputStream.readUTF());
					sendChat(inputStream.readUTF());  
					  
				}
				
			} catch (IOException e) { 
				//e.printStackTrace(); 

			} finally {
				
				for (int i = 0; i < threadList.size(); i++) {
					if (socket1.equals(threadList.get(i).socket1)) {
						threadList.remove(i);
						try {
							sendChat(nickname + " 님 퇴장) ");
							//memberList.remove(i);
						} catch (IOException e) {
							
							//e.printStackTrace();
						}
					}
				}
				System.out.println("접속자 수: " + threadList.size()+"명");
			}//finally-end 

		}//run-end 

	    public void sendChat(String chat) throws IOException{
	        
	        System.out.println(chat);
	        if(chat.split("/귓속말").length != 1) {
	           String[] splitChat = chat.split("/귓속말");
	           chat = "-->";
	           
	           splitChat[1] = splitChat[1].substring(1, splitChat[1].length());
	           
	           String[] splitChat2 = splitChat[1].split(" ");
	           String from = splitChat2[0];
	           String to = splitChat[0].split("-->")[0].trim();
	           
	           for(int i=1; i<splitChat2.length; i++) chat += splitChat2[i] + " ";
	           
	           System.out.println("chat : " + chat);
	           
	           System.out.println("받는사람 : " + from);
	           System.out.println("보내는사람 : " + to);
	           
	           if(from.equals(to)) {
	              for(int i=0; i<threadList.size(); i++) {
	                 if(memberList.get(i).equals(from)) {
	                    String send = "[자신에게는 귓속말을 할 수 없습니다]";
	                    threadList.get(i).outputStream.writeUTF(send);
	                 }
	              }
	           } else {
	              for(int i=0; i<threadList.size(); i++) {
	                 if(memberList.get(i).equals(from)) {
	                    String send = "[From]" + to + chat;
	                    threadList.get(i).outputStream.writeUTF(send);
	                    
	                 } else if(memberList.get(i).equals(to)) {
	                    String send = "[To]" + from + chat;
	                    threadList.get(i).outputStream.writeUTF(send);
	                 }
	              }
	           }
	           
	        } else {
	           for(int i=0; i<threadList.size(); i++) {
	              threadList.get(i).outputStream.writeUTF(chat);
	           }
	        }
	    }
	}//ThreadServerClass-end

	      
}//ServerClass-end



public class TcpMulServer {//TcpMulServer.java 
	public static void main(String args[]) throws IOException {
		if(args.length !=1) {
			
		System.out.println("사용법은: 서버실행은ㅁ \'java 패키지명.파일명 포트번호/' 형식으로 입력 ");
		}
	//	new ServerClass();////////////
		new ServerClass(Integer.parseInt(args[0]));////////////

	}

}

