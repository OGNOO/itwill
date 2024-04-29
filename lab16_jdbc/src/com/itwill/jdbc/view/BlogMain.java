package com.itwill.jdbc.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.itwill.jdbc.controller.BlogDao;
import com.itwill.jdbc.model.Blog;
import com.itwill.jdbc.view.BlogCreateFrame.CreateNotify;
import com.itwill.jdbc.view.BlogDetailsFrame.UpdateNotify;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.FlowLayout;

public class BlogMain implements CreateNotify, UpdateNotify {
	private static final String[] SEARCH_TYPES = { "제목", "내용", "제목 + 내용", "작성자" };
	private static final String[] COLUMN_NAMES = { "번호", "제목", "작성자", "수정시간" };

	private JFrame frame;
	private JPanel searchPanel;
	private JComboBox<String> comboBox;
	private JTextField textSearchKeyword;
	private JButton btnSearch;
	private JPanel buttonPanel;
	private JButton btnReadAll;
	private JButton btnCreate;
	private JButton btnDetails;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private BlogDao dao = BlogDao.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlogMain window = new BlogMain();
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
	public BlogMain() {
		initialize();
		initializeTable();
	}

	private void initializeTable() {
		// DAO를 사용해서 DB 테이블에서 검색.
		List<Blog> blogs = dao.read();

		resetTable(blogs);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 650);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("블로그 메인");

		searchPanel = new JPanel();
		frame.getContentPane().add(searchPanel, BorderLayout.NORTH);

		comboBox = new JComboBox<String>();
		final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(SEARCH_TYPES);
		searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		comboBox.setModel(comboBoxModel);
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 18));
		searchPanel.add(comboBox);

		textSearchKeyword = new JTextField();
		textSearchKeyword.setMargin(new Insets(4, 8, 4, 2));
		textSearchKeyword.setFont(new Font("D2Coding", Font.PLAIN, 18));
		searchPanel.add(textSearchKeyword);
		textSearchKeyword.setColumns(10);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(e -> search());
		btnSearch.setFont(new Font("D2Coding", Font.PLAIN, 18));
		searchPanel.add(btnSearch);

		buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		btnReadAll = new JButton("목록보기");
		btnReadAll.addActionListener(e -> {
			initializeTable();
			textSearchKeyword.setText("");
		});
		btnReadAll.setFont(new Font("D2Coding", Font.PLAIN, 16));
		buttonPanel.add(btnReadAll);

		btnCreate = new JButton("새 블로그");
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: 새 블로그 작성 창 띄우기
				BlogCreateFrame.showBolgCreateFrame(frame, BlogMain.this);
			}
		});
		btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 16));
		buttonPanel.add(btnCreate);

		btnDetails = new JButton("상세보기");
		btnDetails.addActionListener((e) -> showDetailsFrame());
		btnDetails.setFont(new Font("D2Coding", Font.PLAIN, 16));
		buttonPanel.add(btnDetails);

		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(e -> deleteBlog());
		btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 16));
		buttonPanel.add(btnDelete);

		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		tableModel = new DefaultTableModel(null, COLUMN_NAMES);
		table.getTableHeader().setPreferredSize(new Dimension(0, 40));
		table.getTableHeader().setFont(new Font("D2Coding", Font.BOLD, 20));
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
	}

	private void showDetailsFrame() {
		int index = table.getSelectedRow(); // 테이블에서 선택된 행의 인덱스
		if (index == -1) { // JTable에서 선택된 행이 없을 때
			JOptionPane.showMessageDialog(frame, "상세보기할 행을 먼저 선택하세요.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		Integer id = (Integer) tableModel.getValueAt(index, 0);

		BlogDetailsFrame.showBlogDetailsFrame(frame, id, BlogMain.this);

	}

	private void search() {
		int type = comboBox.getSelectedIndex(); // 콤보박스에서 선택된 아이템의 인덱스
		String keyword = textSearchKeyword.getText(); // 검색어
		if (keyword.isBlank()) {
			JOptionPane.showMessageDialog(frame, "검색어를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
			textSearchKeyword.requestFocus();
			// -> 검색어 입력 JTextField 에 포커스를 줌(커서 깜빡깜빡).
			return;
		}
		// DAO 메서드를 호출해서 검색 결과를 가져옴.
		List<Blog> blogs = dao.search(type, keyword);
		resetTable(blogs); // 테이블 리셋.
	}

	private void resetTable(List<Blog> blogs) {
		// 검색한 내용을 JTable 에 보여줌 - JTable 의 테이블 모델을 재설정.
		tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 테이블 모델 리셋.
		for (Blog b : blogs) {
			// DB 테이블에서 검색한 레코드를 JTable 에서 사용할 행 데이터로 변환.
			Object[] row = { b.getId(), b.getTitle(), b.getWriter(), b.getModifiedTime() };
			tableModel.addRow(row); // 테이블 모델에 행 데이터를 추가.
		}
		table.setModel(tableModel); // JTable 의 모델을 다시 세팅.
	}

	private void deleteBlog() {
		int index = table.getSelectedRow();// 테이블에서 선택된 행의 인덱스
		if (index == -1) { // JTable 에 선택된 행이 없을 때
			JOptionPane.showMessageDialog(frame, "삭제할 행을 먼저 선택하세요!", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int confirm = JOptionPane.showConfirmDialog(frame, "정말 삭제하기겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			// 선택된 행에서 블로그 번호(id)를 읽음.
			int id = (Integer) tableModel.getValueAt(index, 0);
			// DAO 의 delete 메서드 호출
			int result = dao.delete(id);
			if (result == 1) {
				initializeTable(); // 테이블을 새로고침.
				JOptionPane.showMessageDialog(frame, "삭제 성공!");
			} else {
				JOptionPane.showMessageDialog(frame, "삭제 실패!");
			}
		}
	}

	@Override
	public void notifyCreateSuccess() {
		// BlogCreateFrame 에서 테이블에 insert 성공했을 때 BlogCreateFrame 이 호출하는 메서드.
		initializeTable();
		JOptionPane.showMessageDialog(frame, "새 블로그 등록 성공!");

	}

	@Override
	public void notifyUpdateSuccess() {
		initializeTable();
		JOptionPane.showMessageDialog(frame, "블로그 업데이트 성공!");
	}

}
