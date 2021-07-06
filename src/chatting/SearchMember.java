package chatting;
//같은 지역구 주민, 동갑인 사람, 이번 달에 생일인 멤버를 조회하는 클래스.
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JDBCInfo.Member;
import JDBCInfo.MemberDao;
import JDBCInfo.MemberDaoImpl;
import login.Login;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class SearchMember extends JFrame {

	private JPanel contentPane;
	private JTextArea txtRegion;	
	private JTextArea txtFriend;	
	private JTextArea txtBDList;	
	
	static Login lg = new Login();
	static String nickname = lg.getNickName();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchMember frame = new SearchMember(nickname);
					frame.setVisible(true);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		});
	}

	public SearchMember(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtRegion = new JTextArea(); //같은 지역
		txtRegion.setBounds(94, 89, 326, 47);
		contentPane.add(txtRegion);
		
		txtFriend = new JTextArea(); //동갑내기
		txtFriend.setBounds(94, 207, 326, 47);
		contentPane.add(txtFriend);
		
		txtBDList = new JTextArea(); //생일자
		txtBDList.setBounds(94, 338, 326, 47);
		contentPane.add(txtBDList);
			
		
		JButton btnRegion = new JButton("동네 주민 찾기");
		btnRegion.setFont(new Font("나눔스퀘어OTF", Font.BOLD, 15));
		btnRegion.setBackground(new Color(245, 255, 250));
		btnRegion.setBorder(null);
		btnRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//데이터베이스 조회
				//접속중이면 옆에 "*접속중*"이라고 뜨게.
				MemberDao memberDao = new MemberDaoImpl();	
				/*for(int i=0;i<memberDao.getMemberByRegion(nickname).length;i++) {
					txtRegion.setText(memberDao.getMemberByRegion(nickname)[i].getNickName()+'\n');
				}*/
				Member member = memberDao.getMemberByRegion(nickname);
				txtRegion.setText(member.getNickName()+'\n');
				
				//Member member = memberDao.getMemberByRegion("동작구"); //나중에 스캐너든, inputdialog 든 값을 입력받아서 여기에 넣을 것이다. 
				//System.out.println("해당 구에 거주하는 유저의 닉네임은 "+member.getNickName()); -->테스트 용도	
				
				
				
				/*Member[] member = memberDao.getMemberByRegion(nickname);
				//System.out.println("해당 구에 거주하는 유저의 닉네임은 "+member.getNickName()); -->테스트 용도	
				for(int i=0;i<member.length;i++) {
					txtRegion.setText(member[i].getNickName()+'\n');
				}	*/			
			}
		});
		btnRegion.setBounds(180, 36, 143, 27);
		contentPane.add(btnRegion);
		
		JButton btnFriend = new JButton("친구 찾기");
		btnFriend.setFont(new Font("나눔스퀘어OTF", Font.BOLD, 15));
		btnFriend.setBackground(new Color(245, 255, 250));
		btnFriend.setBorder (null);
		btnFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//데이터베이스 조회
				//접속중이면 옆에 "*접속중*"이라고 뜨게.
				MemberDao memberDao = new MemberDaoImpl();				
				//Member[] member = memberDao.getMemberByYear(nickname);
				//System.out.println("해당 구에 거주하는 유저의 닉네임은 "+member.getNickName()); -->테스트 용도	
				/*for(int i=0;i<member.length;i++) {
					txtFriend.setText(member[i].getNickName()+'\n');
				}*/
				Member member = memberDao.getMemberByYear(nickname);
				txtFriend.setText(member.getNickName()+'\n');
			}
		});
		btnFriend.setBounds(180, 168, 143, 27);
		contentPane.add(btnFriend);
		
		JButton btnBDmember = new JButton("이번달 생일");
		btnBDmember.setFont(new Font("나눔스퀘어OTF", Font.BOLD, 15));
		btnBDmember.setBackground(new Color(245, 255, 250));
		btnBDmember.setBorder (null);
		btnBDmember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//오늘날짜 
				Calendar cal = Calendar.getInstance();
				String format = "yyyyMMdd";
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				String date = sdf.format(cal.getTime()); //20210706의 문자열 형태로 저장.
				
				MemberDao memberDao = new MemberDaoImpl();
				Member member =  memberDao.getMemberByMonth(date);
				
				Member bd = memberDao.getBirthdayByNickName(nickname); //생년월일 값
				//String bDay = bd.getBirthday();
				//String bdSet = bDay.substring(4,6)+"월"+ bDay.substring(6,8)+"일";
				//System.out.println("정돈된 월일: " +bdSet);
				
				/*Member[] member = memberDao.getMemberByMonth(date);
				//System.out.println("해당 구에 거주하는 유저의 닉네임은 "+member.getNickName()); -->테스트 용도
				for(int i=0;i<member.length;i++) {
					String bd = member[i].getBirthday(); //생년월일 값
					String bdSet = bd.substring(4,6)+"월"+ bd.substring(6,8)+"일";
					System.out.println(member[i].getNickName()+"-"+bdSet);					
					txtBDList.setText(member[i].getNickName()+"-"+bdSet+'\n');	
				}*/
				
				txtBDList.setText(member.getNickName());
				
				/*String bd ="19950927";
				String bdSet = bd.substring(4,6)+"월"+ bd.substring(6,8)+"일";
				System.out.println(bdSet);*/			
							
			}
		});
		btnBDmember.setBounds(180, 287, 143, 27);
		contentPane.add(btnBDmember);		
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("나눔스퀘어OTF", Font.PLAIN, 20));
		textArea.setBackground(new Color(230, 230, 250));
		textArea.setText("나와 공통점이 있는 채팅 멤버를 찾아서\r\n귓속말 채팅을 해보세요!");
		textArea.setBounds(151, 436, 326, 53);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SearchMember.class.getResource("/image/whisper.png")));
		lblNewLabel.setBounds(0, 397, 137, 134);
		contentPane.add(lblNewLabel);
		
	}
}
