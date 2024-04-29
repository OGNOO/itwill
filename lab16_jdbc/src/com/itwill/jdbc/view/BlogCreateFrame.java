package com.itwill.jdbc.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.jdbc.controller.BlogDao;
import com.itwill.jdbc.model.Blog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class BlogCreateFrame extends JFrame {

	public interface CreateNotify {
		void notifyCreateSuccess();
	}

	private static final long serialVersionUID = 1L;

	private BlogDao dao = BlogDao.getInstance();
	private CreateNotify app;

	private Component parent;
	private JTextField textTitle;
	private JPanel contentPane;
	private JTextArea textContent;
	private JTextField textWriter;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void showBolgCreateFrame(Component parent, CreateNotify app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlogCreateFrame frame = new BlogCreateFrame(parent, app);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private BlogCreateFrame(Component parent, CreateNotify app) {
		this.parent = parent;
		this.app = app;
		initialize();
	}

	public void initialize() {
		setTitle("새 블로그 작성");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int x = 0;
		int y = 0;
		if (parent != null) {
			x = parent.getX(); // 부모 컴포넌트의 X 좌표
			y = parent.getY(); // 부모 컴포넌트의 Y 좌표
		}
		setBounds(x, y, 640, 650);
		if (parent == null) {
			setLocationRelativeTo(null); // 화면 중앙에서 JFrame 을 띄움.
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("제목");
		lblTitle.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblTitle.setBounds(12, 22, 600, 41);
		contentPane.add(lblTitle);

		textTitle = new JTextField();
		textTitle.setFont(new Font("D2Coding", Font.PLAIN, 18));
		textTitle.setBounds(12, 63, 600, 41);
		contentPane.add(textTitle);
		textTitle.setColumns(10);

		JLabel lblContent = new JLabel("내용");
		lblContent.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblContent.setBounds(12, 124, 600, 41);
		contentPane.add(lblContent);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 175, 600, 252);
		contentPane.add(scrollPane);

		textContent = new JTextArea();
		textContent.setFont(new Font("D2Coding", Font.PLAIN, 16));
		scrollPane.setViewportView(textContent);

		textWriter = new JTextField();
		textWriter.setFont(new Font("D2Coding", Font.PLAIN, 18));
		textWriter.setColumns(10);
		textWriter.setBounds(12, 488, 600, 41);
		contentPane.add(textWriter);

		JLabel lblWriter = new JLabel("작성자");
		lblWriter.setFont(new Font("D2Coding", Font.BOLD, 20));
		lblWriter.setBounds(12, 437, 600, 41);
		contentPane.add(lblWriter);

		btnSave = new JButton("작성완료");
		btnSave.addActionListener(e -> createNewBlog());
		btnSave.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnSave.setBounds(173, 564, 127, 41);
		contentPane.add(btnSave);

		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(e -> dispose());
		btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnCancel.setBounds(323, 564, 127, 41);
		contentPane.add(btnCancel);
	}

	private void createNewBlog() {
		// 제목, 내용, 작성자에 입력된 내용을 Blog 객체로 만들어서
		// Dao 메서드를 사용해서 DB 테이블에 insert.
		String title = textTitle.getText();
		String content = textContent.getText();
		String writer = textWriter.getText();
		if (title.equals("") || content.equals("") || writer.equals("")) {
			if (title.isBlank()) {
				JOptionPane.showMessageDialog(BlogCreateFrame.this, "제목은 반드시 입력해야 합니다!", "경고",
						JOptionPane.WARNING_MESSAGE);
			} else if (content.isBlank()) {
				JOptionPane.showMessageDialog(BlogCreateFrame.this, "내용은 반드시 입력해야 합니다!", "경고",
						JOptionPane.WARNING_MESSAGE);
			} else if (writer.isBlank()) {
				JOptionPane.showMessageDialog(BlogCreateFrame.this, "작성자는 반드시 입력해야 합니다!", "경고",
						JOptionPane.WARNING_MESSAGE);
			}
			return;
		}

		Blog blog = new Blog(0, title, content, writer, null, null);
		int result = dao.create(blog);
		if (result == 1) {
			// TODO: BlogMain 프레임에게 테이블 삽입 성공을 알려줌.
			app.notifyCreateSuccess();
			dispose(); // 현재 창 닫기
		} else {
			JOptionPane.showMessageDialog(BlogCreateFrame.this, "INSERT 실패");
		}
	}
}
