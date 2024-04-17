package com.iwill.swing03;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppMain03 {

	private static final String[] IMAGES = { "images/img1.jpg", "images/img2.jpg", "images/img3.jpg", "images/img4.jpg",
			"images/img5.jpg", };

	private int curIndex;
	private JFrame frmJava;
	private JLabel lblimage;
	private JButton btnPrev;
	private JButton btnNext;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain03 window = new AppMain03();
					window.frmJava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppMain03() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJava = new JFrame();
		frmJava.setTitle("Java");
		frmJava.setBounds(100, 100, 600, 600);
		frmJava.setLocationRelativeTo(null);
		frmJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJava.getContentPane().setLayout(null);

		lblimage = new JLabel();
		lblimage.setIcon(new ImageIcon(IMAGES[0]));
		lblimage.setHorizontalAlignment(SwingConstants.CENTER);
		lblimage.setBounds(92, 48, 400, 400);
		frmJava.getContentPane().add(lblimage);

		btnPrev = new JButton("<");
		btnPrev.addActionListener(e -> onClickPrevBtn()); // 람다 표현식
		btnPrev.setFont(new Font("D2Coding", Font.BOLD, 24));
		btnPrev.setBounds(128, 481, 100, 45);
		frmJava.getContentPane().add(btnPrev);

		btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 이전 이미지 보여주기
				onClickNextBtn();
			}
		});
		btnNext.setFont(new Font("D2Coding", Font.BOLD, 24));
		btnNext.setBounds(356, 481, 100, 45);
		frmJava.getContentPane().add(btnNext);

		lblNewLabel = new JLabel(IMAGES[curIndex]);
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(214, 484, 156, 42);
		frmJava.getContentPane().add(lblNewLabel);
	}

	protected void onClickNextBtn() {
		if (curIndex < IMAGES.length - 1) {
			curIndex++;
		} else {
			curIndex = 0;
		}
		lblimage.setIcon(new ImageIcon(IMAGES[curIndex]));
		lblNewLabel.setText(IMAGES[curIndex]);
	}

	protected void onClickPrevBtn() {
		if (curIndex > 0) {
			curIndex--;
		} else {
			curIndex = IMAGES.length - 1;
		}
		lblimage.setIcon(new ImageIcon(IMAGES[curIndex]));
		lblNewLabel.setText(IMAGES[curIndex]);
	}
}
