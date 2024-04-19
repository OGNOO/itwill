package com.itwill.ver05.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itwill.ver05.controller.ContactDao;
import com.itwill.ver05.controller.ContactDaoImpl;
import com.itwill.ver05.model.Contact;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ContactSearchFrame extends JFrame {

	private static final String[] COLUMN_NAMES = { "이름", "전화번호", "이메일" };
	private static final long serialVersionUID = 1L;

	private Component parentComponent; // CreateFrame의 부모 컴포넌트

	private JPanel contentPane;
	private JPanel headerPanel;
	private JTextField textField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;

	private ContactDao dao = ContactDaoImpl.getInstance();

	/**
	 * Launch the application.
	 */
	public static void showContactSearchFrame(Component parentComponent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactSearchFrame frame = new ContactSearchFrame(parentComponent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContactSearchFrame(Component parentComponent) {
		this.parentComponent = parentComponent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("연락처 검색");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		int x = 0;
		int y = 0;
		if (parentComponent != null) {
			// 부모 컴포넌트가 있으면 부모 컴포넌트와 같은 좌표(x,y)에 위치시킴
			x = parentComponent.getX();
			y = parentComponent.getY();
		}
		setBounds(x, y, 560, 367);

		if (parentComponent == null) {
			// 부모 컴포넌트가 없으면 화면 중앙에 위치시킴
			setLocationRelativeTo(null);
		}

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		headerPanel = new JPanel();
		contentPane.add(headerPanel, BorderLayout.NORTH);

		textField = new JTextField();
		textField.setFont(new Font("D2Coding", Font.PLAIN, 20));
		headerPanel.add(textField);
		textField.setColumns(10);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener((e) -> searchByKeyword());
		btnSearch.setFont(new Font("D2Coding", Font.PLAIN, 20));
		headerPanel.add(btnSearch);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.getTableHeader().setPreferredSize(new Dimension(100, 40));
		table.setEnabled(false);
		table.getTableHeader().setFont(new Font("D2Coding", Font.BOLD, 20));
		table.setFont(new Font("D2Coding", Font.PLAIN, 20));
		table.setRowHeight(40);
		model = new DefaultTableModel(null, COLUMN_NAMES);
		table.setModel(model);
		scrollPane.setViewportView(table);

	}

	private void searchByKeyword() {
		// 검색어 읽음
		String keyword = textField.getText();
		model.setRowCount(0);
		if (keyword.isBlank()) {
			return;
		}

		// DAO를 사용해서 검색어로 검색 결과를 가져옴
		List<Contact> list = dao.search(keyword);

		// 검색 결과를 테이블에 씀
		for (Contact c : list) {
			Object[] row = { c.getName(), c.getPhone(), c.getEmail() };
			model.addRow(row);
		}
	}
}