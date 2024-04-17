package com.iwill.swing04;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;

public class AppMain04 {

	private JFrame frame;
	private JTextField num1TextField;
	private JTextField num2TextField;
	private JLabel num1;
	private JLabel num2;
	private JButton plusBtn;
	private JButton minusBtn;
	private JButton mltplBtn;
	private JButton divisionBtn;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain04 window = new AppMain04();
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
	public AppMain04() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 506, 549);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		num1 = new JLabel("Number1");
		num1.setBackground(new Color(255, 228, 225));
		num1.setHorizontalAlignment(SwingConstants.CENTER);
		num1.setBounds(31, 39, 127, 61);
		frame.getContentPane().add(num1);

		num2 = new JLabel("Number2");
		num2.setHorizontalAlignment(SwingConstants.CENTER);
		num2.setBackground(new Color(255, 228, 225));
		num2.setBounds(31, 129, 127, 61);
		frame.getContentPane().add(num2);

		num1TextField = new JTextField();
		num1TextField.setBounds(189, 39, 268, 61);
		frame.getContentPane().add(num1TextField);
		num1TextField.setColumns(10);

		num2TextField = new JTextField();
		num2TextField.setColumns(10);
		num2TextField.setBounds(189, 129, 268, 61);
		frame.getContentPane().add(num2TextField);

		plusBtn = new JButton("+");
		plusBtn.addActionListener(e -> handleButtonClick(e));
		plusBtn.setFont(new Font("D2Coding", Font.BOLD, 24));
		plusBtn.setBounds(40, 227, 72, 49);
		frame.getContentPane().add(plusBtn);

		minusBtn = new JButton("-");
		minusBtn.addActionListener(e -> handleButtonClick(e));
		minusBtn.setFont(new Font("D2Coding", Font.BOLD, 24));
		minusBtn.setBounds(152, 227, 72, 49);
		frame.getContentPane().add(minusBtn);

		mltplBtn = new JButton("x");
		mltplBtn.addActionListener(e -> handleButtonClick(e));
		mltplBtn.setFont(new Font("D2Coding", Font.BOLD, 24));
		mltplBtn.setBounds(264, 227, 72, 49);
		frame.getContentPane().add(mltplBtn);

		divisionBtn = new JButton("/");
		divisionBtn.addActionListener(e -> onClickDivisionBtn());
		divisionBtn.setFont(new Font("D2Coding", Font.BOLD, 24));
		divisionBtn.setBounds(376, 227, 72, 49);
		frame.getContentPane().add(divisionBtn);

		textArea = new JTextArea();
		textArea.setBounds(31, 306, 426, 178);
		frame.getContentPane().add(textArea);

	}

	private void onClickDivisionBtn() {
		try {
			if (!num1TextField.getText().equals("0") && !num2TextField.getText().equals("0")) {
				double num1 = Double.parseDouble(num1TextField.getText());
				double num2 = Double.parseDouble(num2TextField.getText());

				textArea.setText(textArea.getText().replace("숫자로 입력해주세요!", ""));
				textArea.append(num1 + " / " + num2 + " = " + (num1 / num2) + "\n");
			} else {
				textArea.setText(textArea.getText().replace("숫자로 입력해주세요!", ""));
				textArea.append("0 으로 나눌수 없습니다.");
			}
			num1TextField.setText("");
			num2TextField.setText("");
		} catch (NumberFormatException e) {
			textArea.append("숫자로 입력해주세요!");
			num1TextField.setText("");
			num2TextField.setText("");
			return;
		}
	}

	private void handleButtonClick(ActionEvent e) {
		// JTextField 에 입력된 문자열을 숫자(double)로 변환
		try {
			if (num1TextField.getText().contains(".") || num2TextField.getText().contains(".")) {
				double x = Double.parseDouble(num1TextField.getText());
				double y = Double.parseDouble(num2TextField.getText());

				textArea.setText(textArea.getText().replace("숫자로 입력해주세요!", ""));

				double result = 0;
				String operator = "";
				Object source = e.getSource();

				if (source == plusBtn) {
					result = x + y;
					operator = "+";
				} else if (source == minusBtn) {
					result = x - y;
					operator = "-";
				} else if (source == mltplBtn) {
					result = x * y;
					operator = "x";
				}
				textArea.append(x + operator + y + " = " + result + "\n");
			} else {
				int x = Integer.parseInt(num1TextField.getText());
				int y = Integer.parseInt(num2TextField.getText());

				textArea.setText(textArea.getText().replace("숫자로 입력해주세요!", ""));

				int result = 0;
				String operator = "";
				Object source = e.getSource();

				if (source == plusBtn) {
					result = x + y;
					operator = "+";
				} else if (source == minusBtn) {
					result = x - y;
					operator = "-";
				} else if (source == mltplBtn) {
					result = x * y;
					operator = "x";
				}
				textArea.append(x + operator + y + " = " + result + "\n");
			}
			num1TextField.setText("");
			num2TextField.setText("");
		} catch (NumberFormatException ex) {
			textArea.append("숫자로 입력해주세요!");
			num1TextField.setText("");
			num2TextField.setText("");

			return;
		}
	}
}
