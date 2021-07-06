package chatting;

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
					e.printStackTrace();
				}
			}
		});
	}

	public SearchMember(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtRegion = new JTextArea(); //같은 지역
		txtRegion.setBounds(28, 90, 326, 47);
		contentPane.add(txtRegion);
		
		txtFriend = new JTextArea(); //동갑내기
		txtFriend.setBounds(28, 215, 326, 47);
		contentPane.add(txtFriend);
		
		txtBDList = new JTextArea(); //생일자
		txtBDList.setBounds(28, 338, 326, 47);
		contentPane.add(txtBDList);
			
		
		JButton btnRegion = new JButton("동네 주민 찾기");
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
		btnRegion.setBounds(28, 39, 143, 27);
		contentPane.add(btnRegion);
		
		JButton btnFriend = new JButton("친구 찾기");
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
		btnFriend.setBounds(28, 176, 143, 27);
		contentPane.add(btnFriend);
		
		JButton btnBDmember = new JButton("이번달 생일");
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
		btnBDmember.setBounds(28, 282, 143, 27);
		contentPane.add(btnBDmember);		
		
	}
}
