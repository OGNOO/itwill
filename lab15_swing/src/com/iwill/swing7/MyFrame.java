package com.iwill.swing7;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {
	public interface Notifiable {
		public void notifyMessage(String msg);
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Component parentComponent;
	private JTextField textField;
	private JButton okButton;
	private Notifiable app;

	/**
	 * Launch the application.
	 * 
	 * @param appMain07
	 */
	public static void showMyFrame(Component parentComponent, Notifiable app) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MyFrame frame = new MyFrame(parentComponent, app);
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
	public MyFrame(Component parentComponent, Notifiable app) {
		this.parentComponent = parentComponent;
		this.app = app;
		initialize();

	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		if (parentComponent != null) {
			setBounds(parentComponent.getX(), parentComponent.getY(), 450, 220);
		} else {
			setBounds(0, 0, 450, 220);
			setLocationRelativeTo(null);
		}

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		textField.setBounds(50, 50, 333, 51);
		contentPane.add(textField);
		textField.setColumns(10);

		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.notifyMessage(textField.getText());
				dispose();
			}
		});
		okButton.setFont(new Font("D2Coding", Font.BOLD, 16));
		okButton.setBounds(172, 134, 90, 33);
		contentPane.add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
	}
}
