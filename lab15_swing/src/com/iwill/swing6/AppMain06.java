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
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
	private JButton btnInfo;

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
		rbPrivate.addActionListener(this::handleRadioBtnClick); // 메서드 참조
		rbPrivate.setSelected(true);
		buttonGroup.add(rbPrivate);
		rbPrivate.setFont(new Font("D2Coding", Font.PLAIN, 20));
		rbPrivate.setHorizontalAlignment(SwingConstants.CENTER);
		rbPrivate.setBounds(23, 30, 121, 23);
		frame.getContentPane().add(rbPrivate);

		rbPackage = new JRadioButton("package");
		rbPackage.addActionListener(e -> handleRadioBtnClick(e));
		buttonGroup.add(rbPackage);
		rbPackage.setHorizontalAlignment(SwingConstants.CENTER);
		rbPackage.setFont(new Font("D2Coding", Font.PLAIN, 20));
		rbPackage.setBounds(167, 30, 121, 23);
		frame.getContentPane().add(rbPackage);

		rbProtected = new JRadioButton("protected");
		rbProtected.addActionListener(e -> handleRadioBtnClick(e));
		buttonGroup.add(rbProtected);
		rbProtected.setHorizontalAlignment(SwingConstants.CENTER);
		rbProtected.setFont(new Font("D2Coding", Font.PLAIN, 20));
		rbProtected.setBounds(311, 30, 121, 23);
		frame.getContentPane().add(rbProtected);

		rbPublic = new JRadioButton("public");
		rbPublic.addActionListener(e -> handleRadioBtnClick(e));
		buttonGroup.add(rbPublic);
		rbPublic.setHorizontalAlignment(SwingConstants.CENTER);
		rbPublic.setFont(new Font("D2Coding", Font.PLAIN, 20));
		rbPublic.setBounds(455, 30, 121, 23);
		frame.getContentPane().add(rbPublic);

		cbAbstract = new JCheckBox("abstract");
		cbAbstract.addActionListener(e -> handleCheckBoxBtnClick(e));
		cbAbstract.setFont(new Font("D2Coding", Font.PLAIN, 18));
		cbAbstract.setHorizontalAlignment(SwingConstants.CENTER);
		cbAbstract.setBounds(42, 88, 143, 39);
		frame.getContentPane().add(cbAbstract);

		cbFinal = new JCheckBox("final");
		cbFinal.addActionListener(e -> handleCheckBoxBtnClick(e));
		cbFinal.setHorizontalAlignment(SwingConstants.CENTER);
		cbFinal.setFont(new Font("D2Coding", Font.PLAIN, 18));
		cbFinal.setBounds(227, 88, 143, 39);
		frame.getContentPane().add(cbFinal);

		cbStatic = new JCheckBox("static");
		cbStatic.addActionListener(e -> handleCheckBoxBtnClick(e));
		cbStatic.setHorizontalAlignment(SwingConstants.CENTER);
		cbStatic.setFont(new Font("D2Coding", Font.PLAIN, 18));
		cbStatic.setBounds(412, 88, 143, 39);
		frame.getContentPane().add(cbStatic);

		comboBox = new JComboBox<>();
		final String[] emails = { "naver.com", "gmail.com", "kakao.com", "yahoo.com" };
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(emails);
		comboBox.addActionListener(e -> handleEmailBoxClick(e));
		comboBox.setModel(model);
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 18));
		comboBox.setBounds(91, 137, 270, 59);
		frame.getContentPane().add(comboBox);

		btnInfo = new JButton("확인");
		btnInfo.addActionListener(e -> handleBtnInfoClick(e));
		btnInfo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnInfo.setBounds(384, 133, 97, 59);
		frame.getContentPane().add(btnInfo);

		textArea = new JTextArea();
		textArea.setSize(540, 52);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("D2Coding", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(29, 230, 542, 210);
		frame.getContentPane().add(scrollPane);
	}

	private void handleBtnInfoClick(ActionEvent e) {
		// JTextArea 에 출력할 문자열들을 붙여 나갈(append) 문자열 버퍼
		StringBuffer buffer = new StringBuffer();

		if (!cbAbstract.isSelected() && !cbFinal.isSelected() && !cbStatic.isSelected()) {
			textArea.setText("체크박스를 선택해주세요\n");
			return;
		}

		// 어떤 라디오버튼이 선택됐는지?
		if (rbPrivate.isSelected()) {
			buffer.append(rbPrivate.getText());
		} else if (rbPackage.isSelected()) {
			buffer.append(rbPackage.getText());
		} else if (rbProtected.isSelected()) {
			buffer.append(rbProtected.getText());
		} else if (rbPublic.isSelected()) {
			buffer.append(rbPublic.getText());
		}
		buffer.append(" 라디오 버튼 선택됨\n");

		// 어떤 체크박스버튼(들)이 선택됐는지?
		if (cbAbstract.isSelected()) {
			buffer.append(cbAbstract.getText() + " ");
		}
		if (cbFinal.isSelected()) {
			buffer.append(cbFinal.getText() + " ");
		}
		if (cbStatic.isSelected()) {
			buffer.append(cbStatic.getText() + " ");
		}
		buffer.append("체크 박스 버튼 선택됨\n");

		// 콤보박스에서 선택된 아이템
		buffer.append(comboBox.getSelectedItem() + "\n");
		textArea.setText(buffer.toString());
	}

	private void handleEmailBoxClick(ActionEvent e) {
		textArea.append((String) comboBox.getSelectedItem() + "\n");
	}

	private ArrayList<JCheckBox> selectedCheckBoxes = new ArrayList<JCheckBox>();

	private void handleCheckBoxBtnClick(ActionEvent e) {
		JCheckBox cb = (JCheckBox) e.getSource();
		String text = cb.getText();
		boolean selected = cb.isSelected();

		if (selected) {
			selectedCheckBoxes.add(cb);
			textArea.append(text + ":" + selected + "\n");
		} else {
			selectedCheckBoxes.remove(cb);
		}
	}

	private JRadioButton $rb;
	private String textFormat = "default string";

	private void handleRadioBtnClick(ActionEvent e) {
		// 4개 라디오버튼들 중에서 누가 클릭됐는 지 찾기:
		JRadioButton rb = (JRadioButton) e.getSource();
		String text = rb.getText(); // 이벤트가 발생한 라디오버튼의 텍스트
		boolean selected = rb.isSelected(); // 이벤트가 발생한 라디오 버튼의 선택 여부

		if ($rb != rb && $rb != null) {
			textArea.setText(textArea.getText().replace(textFormat, ""));
		}
		if (selected && !textArea.getText().contains(textFormat)) {
			textFormat = text + ":" + selected + "\n";
			textArea.append(textFormat);
		}
		$rb = rb;
	}
}
