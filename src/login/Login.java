package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JDBCInfo.Member;
import JDBCInfo.MemberDao;
import JDBCInfo.MemberDaoImpl;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtNickName;
	private JTextField txtRegion;
	private JTextField txtBirthDay;

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
		setBounds(100, 100, 448, 529);
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
		
		JLabel lblLoginBanner = new JLabel("아래의 정보를 입력 후 채팅 시작하자");
		lblLoginBanner.setBounds(72, 46, 260, 18);
		contentPane.add(lblLoginBanner);
		
		JButton btnSave = new JButton("가입완료"); //데이터베이스에 회원 정보를 넘겨주어야 한다. 
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MemberDao memberDao = new MemberDaoImpl();
				//테이블 생성
				memberDao.createMember();
				memberDao.insertMember(new Member(txtNickName.getText(), txtRegion.getText(), txtBirthDay.getText()));
				 //Member member =  memberDao.getMemberByRegion("동작구");
				//System.out.println("해당 구에 거주하는 유저의 닉네임은 : " +member.getNickName());
			}
		});
		btnSave.setBounds(72, 349, 105, 27);
		contentPane.add(btnSave);
		
		JButton btnChatStart = new JButton("채팅 시작하기");
		btnChatStart.setBounds(191, 349, 141, 27);
		contentPane.add(btnChatStart);
	}
}
