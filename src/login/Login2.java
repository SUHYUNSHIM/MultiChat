package login;
//ip, port번호를 받는다.
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	static String nickname;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login2 frame = new Login2(nickname);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login2(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(160, 86, 175, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 134, 175, 27);
		contentPane.add(textField_1);
		
		JLabel lblPortNo = new JLabel("Port no");
		lblPortNo.setFont(new Font("나눔스퀘어OTF Bold", Font.PLAIN, 15));
		lblPortNo.setBounds(91, 86, 57, 27);
		contentPane.add(lblPortNo);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font("나눔스퀘어OTF Bold", Font.PLAIN, 15));
		lblIp.setBounds(91, 134, 57, 27);
		contentPane.add(lblIp);
		
		JButton btnNewButton = new JButton("\uC785\uC7A5\uD558\uAE30");
		btnNewButton.setBackground(new Color(245, 255, 250));
		btnNewButton.setFont(new Font("나눔스퀘어OTF", Font.PLAIN, 15));
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p = textField.getText();
				String i = textField_1.getText();
				System.out.println(p +"\t"+ i+"\t"+nickname);
				
				//포트, ip 주소 입력 창은 닫혀야 한다.
				dispose();
				setVisible(false); 
				new client1(p, i, nickname);
			}
		});

		btnNewButton.setBounds(177, 204, 97, 23);
		contentPane.add(btnNewButton);
	}
}
