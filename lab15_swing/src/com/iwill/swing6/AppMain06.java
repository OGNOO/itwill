package com.iwill.swing6;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AppMain06 {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<String> comboBox;
	private JCheckBox cbStatic;
	private JCheckBox cbFinal;
	private JCheckBox cbAbstract;
	private JRadioButton rbPublic;
	private JRadioButton rbProtected;
	private JRadioButton rbPackage;
	private JRadioButton rbPrivate;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain06 window = new AppMain06();
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
	public AppMain06() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 616, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		rbPrivate = new JRadioButton("private");
		rbPrivate.setSelected(true);
		buttonGroup.add(rbPrivate);
		rbPrivate.setFont(new Font("D2Coding", Font.PLAIN, 20));
		rbPrivate.setHorizontalAlignment(SwingConstants.CENTER);
		rbPrivate.setBounds(23, 30, 121, 23);
		frame.getContentPane().add(rbPrivate);

		rbPackage = new JRadioButton("package");
		buttonGroup.add(rbPackage);
		rbPackage.setHorizontalAlignment(SwingConstants.CENTER);
		rbPackage.setFont(new Font("D2Coding", Font.PLAIN, 20));
		rbPackage.setBounds(167, 30, 121, 23);
		frame.getContentPane().add(rbPackage);

		rbProtected = new JRadioButton("protected");
		buttonGroup.add(rbProtected);
		rbProtected.setHorizontalAlignment(SwingConstants.CENTER);
		rbProtected.setFont(new Font("D2Coding", Font.PLAIN, 20));
		rbProtected.setBounds(311, 30, 121, 23);
		frame.getContentPane().add(rbProtected);

		rbPublic = new JRadioButton("public");
		buttonGroup.add(rbPublic);
		rbPublic.setHorizontalAlignment(SwingConstants.CENTER);
		rbPublic.setFont(new Font("D2Coding", Font.PLAIN, 20));
		rbPublic.setBounds(455, 30, 121, 23);
		frame.getContentPane().add(rbPublic);

		cbAbstract = new JCheckBox("abstract");
		cbAbstract.setFont(new Font("D2Coding", Font.PLAIN, 18));
		cbAbstract.setHorizontalAlignment(SwingConstants.CENTER);
		cbAbstract.setBounds(42, 88, 143, 39);
		frame.getContentPane().add(cbAbstract);

		cbFinal = new JCheckBox("final");
		cbFinal.setHorizontalAlignment(SwingConstants.CENTER);
		cbFinal.setFont(new Font("D2Coding", Font.PLAIN, 18));
		cbFinal.setBounds(227, 88, 143, 39);
		frame.getContentPane().add(cbFinal);

		cbStatic = new JCheckBox("static");
		cbStatic.setHorizontalAlignment(SwingConstants.CENTER);
		cbStatic.setFont(new Font("D2Coding", Font.PLAIN, 18));
		cbStatic.setBounds(412, 88, 143, 39);
		frame.getContentPane().add(cbStatic);

		comboBox = new JComboBox<>(); // new JComboBox<String>();
		final String[] emails = { "naver.com", "gmail.com", "kakao.com", "yahoo.com" };
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(emails);
		comboBox.setModel(model);
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 18));
		comboBox.setBounds(91, 137, 270, 59);
		frame.getContentPane().add(comboBox);
		
		JButton btnInfo = new JButton("확인");
		btnInfo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnInfo.setBounds(384, 133, 97, 59);
		frame.getContentPane().add(btnInfo);
		
		textArea = new JTextArea();
		textArea.setSize(540, 52);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("D2Coding", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(29, 235, 542, 206);
		frame.getContentPane().add(scrollPane);
	}
}
