package db.jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class StudentView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNo;
	private JLabel lblNo;
	private JLabel lblName;
	private JLabel lblPhoto;
	private JLabel lblAge;
	private JLabel lblBirthday;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentView frame = new StudentView();
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
	public StudentView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 355);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(100, 10));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel = new JLabel("学号");
		panel.add(lblNewLabel);
		
		txtNo = new JTextField();
		txtNo.setText("201187654321");
		//		textField.setMinimumSize(new Dimension(100, 21));
		//		textField.setSize(new Dimension(500, 0));
				panel.add(txtNo);
				txtNo.setColumns(10);
				
				JButton btnNewButton = new JButton("查找");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						find();
					}
				});
				panel.add(btnNewButton);
				
				JPanel panel_1 = new JPanel();
				contentPane.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(null);
				
				lblNo = new JLabel("New label");
				lblNo.setBounds(84, 10, 54, 15);
				panel_1.add(lblNo);
				
				JLabel label = new JLabel("学号");
				label.setBounds(10, 10, 54, 15);
				panel_1.add(label);
				
				JLabel label_1 = new JLabel("姓名");
				label_1.setBounds(10, 35, 54, 15);
				panel_1.add(label_1);
				
				lblName = new JLabel("New label");
				lblName.setBounds(84, 35, 54, 15);
				panel_1.add(lblName);
				
				JLabel label_3 = new JLabel("年龄");
				label_3.setBounds(10, 65, 54, 15);
				panel_1.add(label_3);
				
				lblAge = new JLabel("New label");
				lblAge.setBounds(84, 65, 54, 15);
				panel_1.add(lblAge);
				
				JLabel label_5 = new JLabel("出生日期");
				label_5.setBounds(10, 93, 54, 15);
				panel_1.add(label_5);
				
				lblBirthday = new JLabel("New label");
				lblBirthday.setBounds(84, 93, 54, 15);
				panel_1.add(lblBirthday);
				
				lblPhoto = new JLabel("New label");
				lblPhoto.setBounds(191, 10, 359, 263);
				panel_1.add(lblPhoto);

	}
	
	void find() {
		String no = txtNo.getText().trim();
		StudentDAO sd  = new StudentDAO();
		Student s = sd.findByNo(no);
		System.out.println(s);
		lblNo.setText(s.getNo());
		lblName.setText(s.getName());
		lblAge.setText("" + s.getAge());
		lblBirthday.setText(s.getBirthday().toString());
		Blob photo = s.getPhoto();
		if (photo != null) {
			try {
				Image image = ImageIO.read(photo.getBinaryStream());
				lblPhoto.setIcon(new ImageIcon(image));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
