package com.itwill.order.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.itwill.order.controller.ManagerDao;
import com.itwill.order.controller.ProductDao;
import com.itwill.order.model.Product;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class AppMain implements PropertyChangeListener {
	private static final String[] ORDER_LIST = { "상품분류", "상품명", "현재 재고", "발주 수량", "입고후 예상 수량" };
	private static final String[] BASED_ORDERS = { "상품분류", "상품명", "현재 재고", "최소 확보 재고", "최소확보재고 이하 일때 발주할 수량(묶음)",
			"묶음당 낱개" };

	private String access = null;
	private JFrame frame;
	private ManagerDao managerDao = ManagerDao.getInstance();
	private ProductDao productDao = ProductDao.getInstance();
	private JTable tableBasedOrders;
	private JPanel tabBasedOrders;
	private JTable tableOrderList;

	private DefaultTableModel tableBasedOrdersModel;
	private DefaultTableModel tableOrderListModel;
	private JLabel lblNewLabel;

	private String selectedOption;
	private JPanel panel;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton saveBtn;
	private int addBtnClickable = 1;
	private JButton cancelBtn;
	private String originalCellValue;
	private String updatedValue;
	private int editingRow;
	private int editingColumn;
	private TreeSet<Integer> updatedRowsSet = new TreeSet<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain window = new AppMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AppMain() {
		initialize();
		initializeTable();
	}

	private void initialize() {
		String[] selectAdmin = managerDao.readAdminName();
		JComboBox<String> comboBox = new JComboBox<>(selectAdmin);

		String input = JOptionPane.showInputDialog(null, comboBox, "관리자 선택", JOptionPane.QUESTION_MESSAGE);
		if (input != null) {
			selectedOption = (String) comboBox.getSelectedItem();

			System.out.println("선택한 옵션: " + selectedOption);
			System.out.println("입력된 텍스트: " + input);
			if (managerDao.login(selectedOption, input) == 1) {
				String checkCrud = managerDao.checkCrud(selectedOption);
				if (checkCrud == null) {
					JOptionPane.showMessageDialog(null, "접근 권한이 없습니다.", "경고", JOptionPane.ERROR_MESSAGE);
					initialize();
				} else {
					access = checkCrud;
					System.out.println(access);
				}
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				initialize();
			}
		} else {
			System.exit(0);
		}
		frame = new JFrame();
		frame.setTitle("발주 Helper");
		frame.setBounds(100, 100, 800, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// 탭 생성
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("굴림", Font.PLAIN, 16));

		// 첫번째 탭 ----->
		JPanel tabOrderList = new JPanel();
		tabbedPane.addTab("발주 목록", tabOrderList);
		tabOrderList.setLayout(new BorderLayout(0, 0));

		if (selectedOption.contains("님"))
			selectedOption = selectedOption.replace("님", "");
		LocalDateTime now = LocalDateTime.now();
		String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));
		String formattedTime = now.format(DateTimeFormatter.ofPattern(" HH시mm분"));
		System.out.println(formattedTime);
		String labelStr = String.format("안녕하세요 %s님 %s 기준. 오늘 발주하실 내역입니다.", selectedOption,
				formattedDate + formattedTime);
		System.out.println(selectedOption);
		lblNewLabel = new JLabel(labelStr);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		tabOrderList.add(lblNewLabel, BorderLayout.NORTH);

		JScrollPane scrollPaneOrderList = new JScrollPane();
		tabOrderList.add(scrollPaneOrderList, BorderLayout.CENTER);

		tableOrderList = new JTable();
		tableOrderListModel = new DefaultTableModel(null, ORDER_LIST);
		tableOrderList.getTableHeader().setPreferredSize(new Dimension(0, 30));
		tableOrderList.getTableHeader().setFont(new Font("굴림", Font.BOLD, 18));
		tableOrderList.setFont(new Font("굴림", Font.PLAIN, 16));
		tableOrderList.setRowHeight(26);
		// <----- 첫번째 탭
		// 두번째 탭 ----->
		tabBasedOrders = new JPanel();
		tabBasedOrders.setLayout(new BorderLayout(0, 0));

		// 버튼 집합 ----->
		panel = new JPanel();
		tabBasedOrders.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		addBtn = new JButton("+");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableBasedOrders.removePropertyChangeListener(AppMain.this);
				addBtnClickable = 0;
//		        버튼 클릭 막기
				deleteBtn.setEnabled(false);
				addBtn.setEnabled(false);
//				행 추가하기
				Object[] newRowData = { "", "", "", "", "", "" };
				DefaultTableModel model = (DefaultTableModel) tableBasedOrders.getModel();
				model.insertRow(0, newRowData);

				saveBtn.setEnabled(true);
				cancelBtn.setEnabled(true);
			}
		});
		addBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		panel.add(addBtn);

		deleteBtn = new JButton("-");
		deleteBtn.addActionListener(e -> deleteSelectedRow());
		deleteBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		panel.add(deleteBtn);

		saveBtn = new JButton("저장");
		saveBtn.setEnabled(false);
		saveBtn.addActionListener(e -> checkSaveFormatAndInsert());
		saveBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		panel.add(saveBtn);

		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableBasedOrders.editingCanceled(null);
				cancelBtn.setEnabled(false);
				deleteBtn.setEnabled(true);
				addBtn.setEnabled(true);
				saveBtn.setEnabled(false);
				PropertyChangeListener[] listeners = tableBasedOrders.getPropertyChangeListeners();
				updatedRowsSet.clear();
//				System.out.println(listeners.length);
				if (listeners.length == 1) {
					tableBasedOrders.addPropertyChangeListener(AppMain.this);
				}
				if (saveBtn.getText().equals("수정하기")) {
					saveBtn.setText("저장");
					cancelBtn.setText("취소");
				}
				initializeTable();
			}
		});
		cancelBtn.setEnabled(false);
		cancelBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		panel.add(cancelBtn);
		// <----- 버튼 집합

		JScrollPane scrollPaneBasedOrders = new JScrollPane();
		tabBasedOrders.add(scrollPaneBasedOrders);

		tableBasedOrders = new JTable();
		tableBasedOrders.addPropertyChangeListener(AppMain.this);
		tableBasedOrdersModel = new DefaultTableModel(null, BASED_ORDERS);
		tableBasedOrders.getTableHeader().setPreferredSize(new Dimension(0, 30));
		tableBasedOrders.getTableHeader().setFont(new Font("굴림", Font.BOLD, 18));
		tableBasedOrders.setFont(new Font("굴림", Font.PLAIN, 16));
		tableBasedOrders.setRowHeight(26);

		scrollPaneBasedOrders.setViewportView(tableBasedOrders);
		scrollPaneOrderList.setViewportView(tableOrderList);
		tabbedPane.addTab("발주 기준", tabBasedOrders);

		// <----- 두번째 탭
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	}

	// 모든 테이블 초기화
	private void initializeTable() {
		// 첫번째 탭 테이블
		List<Product> listProducts = productDao.readBasedOrder();
		resetOrderListTable(listProducts);

		// 두번째 탭 테이블
		List<Product> basedProducts = productDao.readBasedOrder();
		resetBasedOrderTable(basedProducts);
	}

	// 첫번째 탭 테이블 그리기
	private void resetOrderListTable(List<Product> listProducts) {
		// 테이블 편집 모드 막기
		tableOrderListModel = new DefaultTableModel(null, ORDER_LIST) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		for (Product p : listProducts) {
			if (p.getCurrentInven() <= p.getMinStk()) {
				Object[] row = { p.getProductCategory(), p.getProductName(), p.getCurrentInven(), p.getImsqob(),
						p.getCurrentInven() + (p.getImsqob() * p.getQnttyBndl()) };
				tableOrderListModel.addRow(row);
			}
		}
		tableOrderList.setModel(tableOrderListModel);
	}

	// 두번째 탭 테이블 그리기
	private void resetBasedOrderTable(List<Product> basedProducts) {
		tableBasedOrdersModel = new DefaultTableModel(null, BASED_ORDERS) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// TODO: 테이블 다시 그릴때 언제 어느 위치를 막을건지 결정
				if (column == 1 && addBtn.isEnabled()) {
					// Allow editing for the second column
					return false;
				} else {
					// Disable editing for all other columns
					return true;
				}
			};
		};
		for (Product p : basedProducts) {
			Object[] row = { p.getProductCategory(), p.getProductName(), p.getCurrentInven(), p.getMinStk(),
					p.getImsqob(), p.getQnttyBndl() };
			tableBasedOrdersModel.addRow(row);
		}
		tableBasedOrders.setModel(tableBasedOrdersModel);

	}

	// 저장하기 버튼 클릭 이벤트
	private void checkSaveFormatAndInsert() {
		DefaultTableModel tableModel = (DefaultTableModel) tableBasedOrders.getModel();
		if (addBtnClickable == 0 && saveBtn.getText().equals("저장")) {
			// 테이블의 첫번째 컬럼의 정보를 가져옴
			String[] column1InItems = new String[tableModel.getRowCount()];
			System.out.println("첫번째 컬럼 수" + column1InItems.length);
			// 테이블의 0번째 행의 정보를 가져옴
			String[] row0InItems = new String[BASED_ORDERS.length];
			int col2;
			int col3;
			int col4;
			int col5;

			String insertProductCategory = null;

			if ((String) tableModel.getValueAt(0, 0) != null) {
				insertProductCategory = (String) tableModel.getValueAt(0, 0);
				if (insertProductCategory.isBlank()) {
					JOptionPane.showMessageDialog(null, "상품 분류를 입력해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			String insertProductName = null;
			if ((String) tableModel.getValueAt(0, 1) != null) {
				insertProductName = (String) tableModel.getValueAt(0, 1);
				if (insertProductName.isBlank()) {
					JOptionPane.showMessageDialog(null, "상품명을 입력해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					for (int i = 1; i < column1InItems.length; i++) {
						String value = (String) tableModel.getValueAt(i, 1);
						if (insertProductName.equals(value)) {
							JOptionPane.showMessageDialog(null, "중복된 상품이 존재합니다.", "경고", JOptionPane.ERROR_MESSAGE);
							tableModel.setValueAt("", 0, 1);
							return;
						}
					}
				}
			}

			for (int i = 0; i < row0InItems.length; i++) {
				String value = (String) tableModel.getValueAt(0, i);
				row0InItems[i] = value;
			}
			try {
				col2 = Integer.parseInt(row0InItems[2]);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "현재 재고의 형식이 올바르지 않습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				tableModel.setValueAt("", 0, 2);
				return;
			}
			try {
				col3 = Integer.parseInt(row0InItems[3]);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "최소 확보 재고의 형식이 올바르지 않습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				tableModel.setValueAt("", 0, 3);
				return;
			}
			try {
				col4 = Integer.parseInt(row0InItems[4]);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "최소확보재고 이하 일때 발주할 수량(묶음)의 형식이 올바르지 않습니다.", "경고",
						JOptionPane.ERROR_MESSAGE);
				tableModel.setValueAt("", 0, 4);
				return;
			}
			try {
				col5 = Integer.parseInt(row0InItems[5]);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "묶음당 낱개의 형식이 올바르지 않습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				tableModel.setValueAt("", 0, 5);
				return;
			}

			Product product = new Product(row0InItems[0], row0InItems[1], col2, col3, col4, col5);
			productDao.insertNewProductInfo(product);
			JOptionPane.showMessageDialog(null, "저장되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
			initializeTable();
			saveBtn.setEnabled(false);
			cancelBtn.setEnabled(false);
			addBtn.setEnabled(true);
			deleteBtn.setEnabled(true);

			PropertyChangeListener[] listeners = tableBasedOrders.getPropertyChangeListeners();
			System.out.println(listeners.length);
			if (listeners.length == 1) {
				tableBasedOrders.addPropertyChangeListener(AppMain.this);
			}
		} else if (saveBtn.getText().equals("수정하기")) {
			System.out.println("updatedRowsSet :      " + updatedRowsSet);
			int changeCol2;
			int changeCol3;
			int changeCol4;
			int changeCol5;
			// 0 업데이트 됐다고 알려주기
			int confirm = JOptionPane.showConfirmDialog(null, "정말 수정하시겠습니까?", "수정 확인", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.OK_OPTION) {
				System.out.println("ㅇㅇ 맞음");
				for (int i : updatedRowsSet) {
					// 1 내가 변경한 테이블의 모든 내용 읽어오기
					System.out.println("내가 수정한 인덱스 번호" + i);
//				 (String) tableModel.getValueAt(0, 1)
					String changeCol0 = (String) tableModel.getValueAt(i, 0);
					String changeCol1 = (String) tableModel.getValueAt(i, 1);
//					System.out.println(changeCol2);
					System.out.println(tableModel.getValueAt(i, 2).getClass());

					if (tableModel.getValueAt(i, 2) instanceof Integer) {
						changeCol2 = (int) tableModel.getValueAt(i, 2);
					} else {
						changeCol2 = Integer.parseInt((String) tableModel.getValueAt(i, 2));
					}

					if (tableModel.getValueAt(i, 3) instanceof Integer) {
						changeCol3 = (int) tableModel.getValueAt(i, 3);
					} else {
						changeCol3 = Integer.parseInt((String) tableModel.getValueAt(i, 3));
					}

					if (tableModel.getValueAt(i, 4) instanceof Integer) {
						changeCol4 = (int) tableModel.getValueAt(i, 4);
					} else {
						changeCol4 = Integer.parseInt((String) tableModel.getValueAt(i, 4));
					}

					if (tableModel.getValueAt(i, 5) instanceof Integer) {
						changeCol5 = (int) tableModel.getValueAt(i, 5);
					} else {
						changeCol5 = Integer.parseInt((String) tableModel.getValueAt(i, 5));
					}

					Product product = new Product(changeCol0, changeCol1, changeCol2, changeCol3, changeCol4,
							changeCol5);
					// 2 update 쿼리 날리기
					productDao.update(product);
					System.out.println(product);
				}
				JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
			}
			updatedRowsSet.clear();
			// 3 테이블 다시 그리기
			initializeTable();
		}

	}

	// 삭제하기 버튼 클릭 이벤트
	private void deleteSelectedRow() {
		int choiceRows[] = tableBasedOrders.getSelectedRows();
		String[] selectedProductNames = new String[choiceRows.length];

		if (choiceRows.length == 0) {
			JOptionPane.showMessageDialog(null, "삭제할 행이 없습니다.", "경고", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(null, "정말 삭제하기겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {

			for (int i = 0; i < selectedProductNames.length; i++) {
				selectedProductNames[i] = (String) tableBasedOrdersModel.getValueAt(choiceRows[i], 1);
			}
			productDao.delete(selectedProductNames);
		}
		initializeTable();
	}

	// 셀 값 변경 탐지 이벤트
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if ("tableCellEditor".equals(e.getPropertyName())) {
			if (tableBasedOrders.isEditing()) {
				System.out.println("셀 편집 모드 진입");
				saveBtn.setText("수정하기");
				cancelBtn.setText("수정취소");

				// 현재 편집 중인 셀 값 가져오기
				editingRow = tableBasedOrders.getSelectedRow();
				editingColumn = tableBasedOrders.getSelectedColumn();

				if (String.valueOf(tableBasedOrders.getValueAt(editingRow, editingColumn)) != null) {
					originalCellValue = String.valueOf(tableBasedOrders.getValueAt(editingRow, editingColumn));
				} else {
					originalCellValue = "";
				}
				System.out.println("셀 출근 후 값" + originalCellValue);
				addBtn.setEnabled(false);
				deleteBtn.setEnabled(false);
				saveBtn.setEnabled(false);
				cancelBtn.setEnabled(true);
			} else {
				System.out.println("셀 편집 모드 종료");
				if ((String) tableBasedOrders.getValueAt(editingRow, editingColumn) != null) {
					updatedValue = (String) tableBasedOrders.getValueAt(editingRow, editingColumn);
				} else {
					updatedValue = "";
				}
				updatedValue = (String) tableBasedOrders.getValueAt(editingRow, editingColumn);

				System.out.println("셀 퇴근 후 값" + updatedValue);
				if (!originalCellValue.equals(updatedValue)) {
					saveBtn.setEnabled(true);
					System.out.println("셀값 변경 감지됨");
					System.out.println(editingRow);
//					System.out.println(editingColumn);

					// 저장 전에 컬럼 유효성 검사
					// 0번째 컬럼 유효성 검사
					if (editingColumn == 0) {
						if (updatedValue.isBlank()) {
							JOptionPane.showMessageDialog(null, "상품 분류를 입력해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
							tableBasedOrders.setValueAt(originalCellValue, editingRow, 0);
						}

					}
					if (editingColumn == 1) {
						// 중복된 상품이 존재하면 true
						Boolean checkPk = false;
						System.out.println("tableBasedOrdersModel.getRowCount()" + tableBasedOrdersModel.getRowCount());
						for (int i = 0; i < tableBasedOrdersModel.getRowCount(); i++) {
							String value = (String) tableBasedOrdersModel.getValueAt(i, 1);
							if (editingRow != i && updatedValue.equals(value)) {
								checkPk = true;
								break;
							}
						}

						if (updatedValue.isBlank()) {
							JOptionPane.showMessageDialog(null, "상품명을 입력해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
							tableBasedOrders.setValueAt(originalCellValue, editingRow, 1);
						} else if (checkPk) {
							JOptionPane.showMessageDialog(null, "같은 상품명이 존재합니다.", "경고", JOptionPane.ERROR_MESSAGE);
							tableBasedOrders.setValueAt(originalCellValue, editingRow, 1);
						}

					}
					if (editingColumn == 2) {
						if (!isValidNumber(updatedValue)) {
							JOptionPane.showMessageDialog(null, "현재 재고의 형식이 올바르지 않습니다.\n유효값 : (0 ~ 99999)", "경고",
									JOptionPane.ERROR_MESSAGE);
							tableBasedOrders.setValueAt(originalCellValue, editingRow, 2);
						}

					}
					if (editingColumn == 3) {
						if (!isValidNumber(updatedValue)) {
							JOptionPane.showMessageDialog(null, "최소 확보 재고의 형식이 올바르지 않습니다.\n유효값 : (0 ~ 99999)", "경고",
									JOptionPane.ERROR_MESSAGE);
							tableBasedOrders.setValueAt(originalCellValue, editingRow, 3);
						}
					}
					if (editingColumn == 4) {
						if (!isValidNumber(updatedValue)) {
							JOptionPane.showMessageDialog(null,
									"최소확보재고 이하 일때 발주할 수량(묶음)의 형식이 올바르지 않습니다.\n유효값 : (0 ~ 99999)", "경고",
									JOptionPane.ERROR_MESSAGE);
							tableBasedOrders.setValueAt(originalCellValue, editingRow, 4);
						}
					}
					if (editingColumn == 5) {
						if (!isValidNumber(updatedValue) || updatedValue.equals("0")) {
							JOptionPane.showMessageDialog(null, "묶음당 낱개의 형식이 올바르지 않습니다.\n유효값 : (1 ~ 99999)", "경고",
									JOptionPane.ERROR_MESSAGE);
							tableBasedOrders.setValueAt(originalCellValue, editingRow, 5);
						}
					}

					// 정보가 바뀐 행의 정보를 TreeSet 에 저장
					updatedRowsSet.add(editingRow);

//					System.out.println(updatedRows);
				} else {
					// 유효성 검사 해야 되서 False 로 바꿔놔야댐
					saveBtn.setEnabled(false);
				}
			}
		}

	}

	public static boolean isValidNumber(String input) {
		Pattern pattern = Pattern.compile("^[0-9]{1,5}$");
		return pattern.matcher(input).matches();
	}
}
