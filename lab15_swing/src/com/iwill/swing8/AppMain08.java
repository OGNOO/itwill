package com.iwill.swing8;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AppMain08 {
	private static final String[] COLUMN_NAMES = { "국어", "영어", "수학", "총점", "평균" };

	private JFrame frame;
	private JLabel lblKorean;
	private JTextField textKorean;
	private JTextField textEnglish;
	private JTextField textMath;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain08 window = new AppMain08();
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
	public AppMain08() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 478, 529);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblKorean = new JLabel("국어");
		lblKorean.setHorizontalAlignment(SwingConstants.CENTER);
		lblKorean.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblKorean.setBounds(55, 25, 80, 40);
		frame.getContentPane().add(lblKorean);

		JLabel lblEnglish = new JLabel("영어");
		lblEnglish.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnglish.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblEnglish.setBounds(190, 25, 80, 40);
		frame.getContentPane().add(lblEnglish);

		JLabel lblMath = new JLabel("수학");
		lblMath.setHorizontalAlignment(SwingConstants.CENTER);
		lblMath.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblMath.setBounds(325, 25, 80, 40);
		frame.getContentPane().add(lblMath);

		textKorean = new JTextField();
		textKorean.setHorizontalAlignment(SwingConstants.CENTER);
		textKorean.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textKorean.setBounds(55, 85, 80, 40);
		frame.getContentPane().add(textKorean);
		textKorean.setColumns(10);

		textEnglish = new JTextField();
		textEnglish.setHorizontalAlignment(SwingConstants.CENTER);
		textEnglish.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textEnglish.setColumns(10);
		textEnglish.setBounds(190, 85, 80, 40);
		frame.getContentPane().add(textEnglish);

		textMath = new JTextField();
		textMath.setHorizontalAlignment(SwingConstants.CENTER);
		textMath.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textMath.setColumns(10);
		textMath.setBounds(325, 85, 80, 40);
		frame.getContentPane().add(textMath);

		btnNewButton = new JButton("입력");
		btnNewButton.addActionListener(e -> inputScoreToTable());
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton.setBounds(62, 152, 137, 42);
		frame.getContentPane().add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 214, 438, 266);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		model = new DefaultTableModel(null, COLUMN_NAMES);
		table.setModel(model);
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		scrollPane.setViewportView(table);

		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(e -> deleteScoreFromTable());
		btnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnDelete.setBounds(261, 152, 137, 42);
		frame.getContentPane().add(btnDelete);
	}

	private void deleteScoreFromTable() {
		int index = table.getSelectedRow();
		if (index == -1) { // 테이블에서 선택된 행이 없을 때
			JOptionPane.showConfirmDialog(frame, " 삭제할 행을 선택해주세요.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 삭제 여부를 사용자에게 물어봄
		int confirm = JOptionPane.showConfirmDialog(frame, " 정말 삭제 하시겠습니까?.", "경고", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		if (confirm == 0) {
			model.removeRow(index);
		}
	}

	private void inputScoreToTable() {
		// 1. JTextField 에서 3과목의 점수를 읽음
		int korean = 0;
		int english = 0;
		int math = 0;

		try {
			korean = Integer.parseInt(textKorean.getText());
			english = Integer.parseInt(textEnglish.getText());
			math = Integer.parseInt(textMath.getText());
		} catch (NumberFormatException e) {
			// TODO 메세지 다이얼로그 띄위기
			JOptionPane.showConfirmDialog(frame, " 숫자만 입력 가능합니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
			clearAllTextFields();
			return;
		}

		if (korean < 0 || english < 0 || math < 0 || korean > 100 || english > 100 || math > 100) {
			JOptionPane.showConfirmDialog(frame, " 0 부터 100 이하의 수만 입력 가능합니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// 2. Score 타입 객체를 생성
		Score score = new Score(korean, english, math);

		// 3. JTable 에 행(row)을 추가
		Object[] row = { score.getKorean(), score.getEnglish(), score.getMath(), score.getTotal(), score.getMean() };
		model.addRow(row);

		// 4. JTextField 값 초기화
		clearAllTextFields();
	}

	private void clearAllTextFields() {
		textKorean.setText("");
		textEnglish.setText("");
		textMath.setText("");
	}

}
