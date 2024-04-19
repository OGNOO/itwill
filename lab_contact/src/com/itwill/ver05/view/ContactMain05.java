package com.itwill.ver05.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itwill.ver05.controller.ContactDao;
import com.itwill.ver05.controller.ContactDaoImpl;
import com.itwill.ver05.model.Contact;
import com.itwill.ver05.view.ContactCreateFrame.CreateNotify;
import com.itwill.ver05.view.ContactUpdateFrame.UpdateNotify;

import javax.swing.ListSelectionModel;
import java.awt.Cursor;
import java.awt.Dimension;

public class ContactMain05 implements CreateNotify, UpdateNotify {

	private static final String[] COLUMN_NAMES = { "이름", "전화번호" };

	private ContactDao dao = ContactDaoImpl.getInstance();

	private JFrame frame;
	private JPanel buttonPanel;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactMain05 window = new ContactMain05();
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
	public ContactMain05() {
		initialize(); // UI 컴포넌트들(버튼, 테이블, ...)을 생성, 초기화
		loadContactData(); // ContactDao를 사용해서 파일에 저장된 연락처 데이터를 테이블에 로딩
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("연락처 v0.5");

		buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

		btnCreate = new JButton("새 연락처");
		btnCreate.addActionListener((e) -> ContactCreateFrame.showContactCreateFrame(frame, ContactMain05.this));
		btnCreate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		buttonPanel.add(btnCreate);

		btnUpdate = new JButton("업데이트");
		btnUpdate.addActionListener((e) -> updateContact());
		btnUpdate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		buttonPanel.add(btnUpdate);

		btnDelete = new JButton("삭제");
		btnDelete.addActionListener((e) -> deleteContact());
		btnDelete.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		buttonPanel.add(btnDelete);

		btnSearch = new JButton("검색");
		btnSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnSearch.addActionListener((e) -> ContactSearchFrame.showContactSearchFrame(frame));
		buttonPanel.add(btnSearch);

		scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setFont(new Font("D2Coding", Font.PLAIN, 14));
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.getTableHeader().setPreferredSize(new Dimension(100, 40));
		table.getTableHeader().setFont(new Font("D2Coding", Font.BOLD, 20));
		table.setFont(new Font("D2Coding", Font.PLAIN, 20));
		table.setRowHeight(40);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		model = new DefaultTableModel(null, COLUMN_NAMES) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		table.setModel(model);
		scrollPane.setViewportView(table);
	}

	private void updateContact() {
		// 테이블에서 업데이트하기 위해서 선택한 행의 인덱스를 찾음
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(frame, "먼저 테이블에서 업데이트할 행을 선택하세요...", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 업데이트 프레임(JFrame)을 실행
		ContactUpdateFrame.showContactUpdateFrame(frame, index, ContactMain05.this);
	}

	private void deleteContact() {
		// 테이블에서 선택된 행(row)의 인덱스를 찾음.
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(frame, "먼저 테이블에서 삭제할 행을 선택하세요...", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(frame, "선택한 연락처를 삭제할까요?", "삭제 확인", JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			// DAO를 사용해서 선택된 연락처를 삭제하고, 파일에 저장.
			int result = dao.delete(index);
			if (result == 1) {
				resetTable(); // 테이블 새로 그리기
				JOptionPane.showMessageDialog(frame, "삭제 성공!");
			} else {
				JOptionPane.showMessageDialog(frame, "", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	}

	private void loadContactData() {
		// DAO를 사용해서 파일에 저장된 데이터를 읽어옴
		List<Contact> list = dao.read();

		// 리스트의 연락처들을 테이블에 행으로 추가
		for (Contact c : list) {
			Object[] row = { c.getName(), c.getPhone() };
			model.addRow(row);
		}

	}

	private void resetTable() {
		// 데이터를 모두 지운 새로운 테이블 모델 객체를 생성
		model = new DefaultTableModel(null, COLUMN_NAMES) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		// 파일에 저장된 연락처(새 연락처가 추가된 데이터)를 로딩
		loadContactData();
		// 새 테이블 모델을 테이블에 다시 세팅
		table.setModel(model);
	}

	@Override // ContactCreateFrame.CreateNotify 인터페이스의 메서드 재정의
	public void notifyContactCreated() {
		resetTable(); // 테이블을 처음부터 다시 새로 그림
		// 사용자에게 알림
		JOptionPane.showMessageDialog(frame, "새 연락처 저장 성공!");
	}

	@Override // ContactUpdatedFrame.UpdateNotify 인터페이스의 메서드 재정의
	public void notifyContactUpdated() {
		resetTable(); // 테이블을 처음부터 다시 새로 그림
		// 사용자에게 알림
		JOptionPane.showMessageDialog(frame, "연락처 업데이트 성공!");
	}
}