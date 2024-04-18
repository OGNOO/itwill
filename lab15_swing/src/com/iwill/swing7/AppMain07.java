package com.iwill.swing7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppMain07 {

	public JFrame frame;
	private JButton btnMsgDlg;
	private JButton btnInputDialog;
	private JButton btnCustomDialog;
	private JButton btnMyFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain07 window = new AppMain07();
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
	public AppMain07() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("D2Coding", Font.PLAIN, 12));
		frame.setBounds(100, 100, 666, 604);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnMsgDlg = new JButton("Message Dialog");
		btnMsgDlg.addActionListener(e -> onClickBtnMsgDlg(e));
		btnMsgDlg.setFont(new Font("D2Coding", Font.BOLD, 20));
		btnMsgDlg.setBounds(220, 26, 197, 81);
		frame.getContentPane().add(btnMsgDlg);

		JButton btnConfirmDialog = new JButton("Confirm Dialog");
		btnConfirmDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 컨펌 다이얼로그 보여주기
//				int result = JOptionPane.showConfirmDialog(frame, "안녕하세여");
//				btnConfirmDialog.setText("confirm = " + result);

				JOptionPane.showConfirmDialog(frame, "정말 삭제하시겠습니까?", "제목", JOptionPane.OK_CANCEL_OPTION, 0);
//				JOptionPane.showConfirmDialog(창 어디에 띄울지, 바디 텍스트, 헤더 텍스트, 세부 기능 변경, 메세지 아이콘);

			}
		});
		btnConfirmDialog.setFont(new Font("D2Coding", Font.BOLD, 20));
		btnConfirmDialog.setBounds(220, 133, 197, 81);
		frame.getContentPane().add(btnConfirmDialog);

		btnInputDialog = new JButton("Input Dialog");
		btnInputDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showInputDialog("메세지");
//				JOptionPane.showInputDialog(frame, "바디 메세지");
//				JOptionPane.showInputDialog(frame, "바디 메세지", "input 디폴트 값");
//				JOptionPane.showInputDialog(frame, "바디 메세지", "헤더 메세지", 0); // 마지막 == 아이콘모양
				String[] test = { "가", "나", "다" };
				JOptionPane.showInputDialog(frame, "바디 메세지", "헤더 메세지", 1, null, test, test[1]);
			}
		});
		btnInputDialog.setFont(new Font("D2Coding", Font.BOLD, 20));
		btnInputDialog.setBounds(220, 240, 197, 81);
		frame.getContentPane().add(btnInputDialog);

		btnCustomDialog = new JButton("Custom Dialog");
		btnCustomDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 내가 만든 다이얼로그 보여주기
				MyDialog.showMyDialog(frame);
			}
		});
		btnCustomDialog.setFont(new Font("D2Coding", Font.BOLD, 20));
		btnCustomDialog.setBounds(220, 347, 197, 81);
		frame.getContentPane().add(btnCustomDialog);
		
		btnMyFrame = new JButton("Custom Frame");
		btnMyFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyFrame.showMyFrame();
			}
		});
		btnMyFrame.setFont(new Font("D2Coding", Font.BOLD, 20));
		btnMyFrame.setBounds(220, 454, 197, 81);
		frame.getContentPane().add(btnMyFrame);
	}

	private void onClickBtnMsgDlg(ActionEvent e) {
		// 메세지 다이얼로그 보여주기
//		JOptionPane.showMessageDialog(frame, "안녕하세요");
		JOptionPane.showMessageDialog(frame, "안녕하세요, swing", "메세지", JOptionPane.ERROR_MESSAGE);

	}
}
