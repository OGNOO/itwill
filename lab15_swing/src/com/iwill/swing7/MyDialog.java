package com.iwill.swing7;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonPanel;

	/**
	 * Launch the application.
	 * 
	 * @param frame
	 */
	public static void showMyDialog(JFrame frame) {
		try {
			MyDialog dialog = new MyDialog();

			// 다이얼로그의 닫기 버튼(X)의 기본 동작을 설정:
			// DISPOSE_ON_CLOSE: 현재 다이얼로그만 닫고, 메인 프로세스는 계속 실행
			// EXIT_ON_CLOSE: 현재 다이얼로그와 함께 메인 프로세스까지 종료
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MyDialog() {
		setResizable(false);
		setTitle("JAVA_SWING");
		initialize();
	};

	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 198);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(36, 34, 96, 51);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(168, 34, 96, 51);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(300, 34, 96, 51);
		contentPanel.add(lblNewLabel_2);

		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 198, 434, 42);
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);

		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
			}
		});
		okButton.setFont(new Font("D2Coding", Font.BOLD, 16));
		okButton.setBounds(100, 5, 90, 33);
		buttonPanel.add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("D2Coding", Font.BOLD, 16));
		cancelButton.setBounds(242, 5, 90, 33);
		cancelButton.setActionCommand("Cancel");
		buttonPanel.add(cancelButton);

	}
}
