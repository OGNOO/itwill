package com.iwill.swing02;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppMain02 {

	private JFrame frame;
	private JTextField textInput;
	private JLabel lblMessage;
	private JButton btnInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain02 window = new AppMain02();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppMain02() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 635, 489);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); // Absolute Layout

		lblMessage = new JLabel("메세지를 입력하세요...");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblMessage.setBounds(12, 30, 595, 88);
		frame.getContentPane().add(lblMessage);

		textInput = new JTextField();
		textInput.setFont(new Font("D2Coding", Font.PLAIN, 20));
		textInput.setBounds(12, 148, 595, 186);
		frame.getContentPane().add(textInput);
		textInput.setColumns(10);

		btnInput = new JButton("입력"); // JButton 객체 생성

		// 버튼에 ActionListener 이벤트 핸들러를 설정(등록)
		btnInput.addActionListener(new ActionListener() {
			@Override // 버튼이 클릭됐을 때 할 일을 작성
			public void actionPerformed(ActionEvent e) {
				// (지역) 내부 클래스에서는 외부 클래스의 메서드를 사용할 수 있음
				handelInputButtonClick();
			}
		});
		btnInput.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnInput.setBounds(245, 364, 128, 53);
		frame.getContentPane().add(btnInput);
	}

	private void handelInputButtonClick() {
		// 1. JTextField 에 입력된 문자열을 읽음
		// 2. (1)에서 읽은 내용을 JLabel에 씀
		// 3. JTextField 를 비움(JTextField 에 입력된 내용을 지움).
		if (!textInput.getText().isBlank()) {
			String msg = textInput.getText();
			lblMessage.setText(msg);
			textInput.setText("");
		}
	}
}
