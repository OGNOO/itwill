package com.iwill.swing05;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

public class AppMain05 {

	private JFrame frame;
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private JButton inputBtn;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain05 window = new AppMain05();
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
	public AppMain05() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 616, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblName.setToolTipText("");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(12, 10, 137, 62);
		frame.getContentPane().add(lblName);

		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblPhone.setToolTipText("");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setBounds(12, 82, 137, 62);
		frame.getContentPane().add(lblPhone);

		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblEmail.setToolTipText("");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(12, 154, 137, 62);
		frame.getContentPane().add(lblEmail);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("D2Coding", Font.PLAIN, 16));
		nameTextField.setBounds(161, 15, 410, 52);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		phoneTextField = new JTextField();
		phoneTextField.setFont(new Font("D2Coding", Font.PLAIN, 16));
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(161, 87, 410, 52);
		frame.getContentPane().add(phoneTextField);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font("D2Coding", Font.PLAIN, 16));
		emailTextField.setColumns(10);
		emailTextField.setBounds(161, 159, 410, 52);
		frame.getContentPane().add(emailTextField);

		inputBtn = new JButton("입력");
		inputBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		inputBtn.addActionListener(e -> onClickBtn());
		inputBtn.setBounds(251, 238, 97, 52);
		frame.getContentPane().add(inputBtn);

		textArea = new JTextArea();
		textArea.setSize(540, 52);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("D2Coding", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(29, 315, 542, 206);
		frame.getContentPane().add(scrollPane);
	}

	private void onClickBtn() {
		textArea.append("이름: " + nameTextField.getText() + "\n");
		textArea.append("전화번호: " + phoneTextField.getText() + "\n");
		textArea.append("이메일: " + emailTextField.getText() + "\n");
		textArea.append("========================================" + "\n");

		nameTextField.setText("");
		phoneTextField.setText("");
		emailTextField.setText("");
	}
}
