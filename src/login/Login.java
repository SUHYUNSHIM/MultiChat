package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ChatFrame.chatMain;
import JDBCInfo.Member;
import JDBCInfo.MemberDao;
import JDBCInfo.MemberDaoImpl;
import chatSC.ServerClass;
import chatting.FState;
import chatting.SearchMember;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.Panel;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtNickName;
	private JTextField txtRegion;
	private JTextField txtBirthDay;
	private JLabel lblCheck;
	public String nickname;
	ArrayList<String> userlist = new ArrayList<String>();
	
	
	//닉네임 getter
	public String getNickName() {
		return nickname;
	}	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNickName = new JLabel("닉네임");
		lblNickName.setBounds(59, 147, 62, 18);
		contentPane.add(lblNickName);

		
		JLabel lblRegion = new JLabel("지역구");
		lblRegion.setBounds(59, 192, 62, 18);
		contentPane.add(lblRegion);
		
		JLabel lblAge = new JLabel("생년월일");
		lblAge.setBounds(59, 257, 62, 18);
		contentPane.add(lblAge);
		
		txtNickName = new JTextField();
		txtNickName.setBounds(151, 144, 116, 24);
		contentPane.add(txtNickName);
		txtNickName.setColumns(10);
		
		txtRegion = new JTextField();
		txtRegion.setBounds(151, 189, 116, 24);
		contentPane.add(txtRegion);
		txtRegion.setColumns(10);
		
		txtBirthDay = new JTextField();
		txtBirthDay.setBounds(151, 254, 116, 24);
		contentPane.add(txtBirthDay);
		txtBirthDay.setColumns(10);
		
		Panel panel_orange = new Panel(); //닉네임 중복을 확인했는지 색상을 확인 가능하다. 닉네임이 사용가능한 경우 초록색으로 변함.
		panel_orange.setBackground(Color.ORANGE);
		panel_orange.setBounds(380, 147, 18, 18);
		contentPane.add(panel_orange);
		
		
		JLabel lblLoginBanner = new JLabel("★아래의 정보를 입력 후 채팅 시작하자★");
		lblLoginBanner.setBounds(72, 46, 295, 18);
		contentPane.add(lblLoginBanner);
				
		//테이블 생성--> 애초부터 빈테이블을 만들고 시작한다. --> 빈테이블일 경우, 아이디 중복 검사를 진행했을 때 통과하기 위해서 이다. 
		MemberDao memberDao = new MemberDaoImpl();					
		memberDao.createMember();
		
		JButton btnSave = new JButton("가입"); //데이터베이스에 회원 정보를 넘겨주어야 한다. 
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//MemberDao memberDao = new MemberDaoImpl(); --> 애초에 만들고 중복검색을 하도록 했다.
				//테이블 생성
				//memberDao.createMember();
				if(txtNickName.getText() == null || txtRegion.getText() == null ||  txtBirthDay.getText() == null) {
					JOptionPane.showMessageDialog(null, "값을 모두 입력해주세요\n","공백 경고 메시지", JOptionPane.WARNING_MESSAGE);	
				}
				else if(panel_orange.getBackground()== Color.ORANGE) {
					JOptionPane.showMessageDialog(null, "닉네임 중복 검사를 해주세요\n","닉네임 중복 경고 메시지", JOptionPane.WARNING_MESSAGE);	
				}
				else {
					memberDao.insertMember(new Member(txtNickName.getText(), txtRegion.getText(), txtBirthDay.getText()));
					
					//Member member = memberDao.getMemberByRegion("동작구"); //나중에 스캐너든, inputdialog 든 값을 입력받아서 여기에 넣을 것이다. 
					//System.out.println("해당 구에 거주하는 유저의 닉네임은 "+member.getNickName()); -->테스트 용도	
					JOptionPane.showMessageDialog(null, "가입완료\n","Login", JOptionPane.INFORMATION_MESSAGE);
				}				
			}
		});
		btnSave.setBounds(72, 349, 105, 27);
		contentPane.add(btnSave);
		
		///------------------------------------------------------------//
		JButton btnChatStart = new JButton("채팅룸 검색하기");
		btnChatStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ip와 port를 입력하는 창으로 넘어간다. 
				//이 버튼을 누르면 서버가 동작하는 것은 어떨까
				nickname = txtNickName.getText();
				new Login2(nickname); 
				userlist.add(nickname);
				new FState(userlist);
				System.out.println(nickname);
				dispose();
				setVisible(false); 
				new Login2(nickname).setVisible(true);
				new SearchMember(nickname);
				System.out.println("채팅 포트, ip 주소 입력 화면으로 전환 성공.");			
			
			}
		});
		btnChatStart.setBounds(191, 349, 141, 27);
		contentPane.add(btnChatStart);
		
		
		JButton btnDuplCheck = new JButton("중복 검색");
		btnDuplCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MemberDao memberDao = new MemberDaoImpl();
				JLabel lblCheck = new JLabel("");
				if(arg0.getSource() == btnDuplCheck) {
					//member 테이블에서 조회하여 이미 같은 닉네임을 가진 유저가 있을 경우 경고 메시지가 나타나야 한다.
					boolean duplCheck = memberDao.getMemberByNickName(txtNickName.getText());
					if(duplCheck == true) {
						JOptionPane.showMessageDialog(null, "사용가능한 닉네임입니다\n","ID check", JOptionPane.INFORMATION_MESSAGE);
						 System.out.println("닉네임 사용가능");
						 panel_orange.setBackground(Color.GREEN);
					}
					else if (duplCheck == false) {
						JOptionPane.showMessageDialog(null, "이미 사용 중인 닉네임입니다\n","사용할 수 없는 닉네임 경고", JOptionPane.WARNING_MESSAGE);	
						panel_orange.setBackground(Color.ORANGE); 						
					}
				}			
				
			}
		});
		btnDuplCheck.setBounds(281, 143, 93, 27);
		contentPane.add(btnDuplCheck);	
		
	}
}
