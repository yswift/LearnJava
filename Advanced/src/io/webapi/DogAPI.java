package io.webapi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import io.HttpTools;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class DogAPI extends JFrame {

	private JPanel contentPane;
	private JLabel lblJson;
	private JLabel lblImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DogAPI frame = new DogAPI();
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
	public DogAPI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLoad = new JButton("Fetch");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(10, 10, 93, 23);
		contentPane.add(btnLoad);

		lblJson = new JLabel("New label");
		lblJson.setBounds(10, 40, 622, 39);
		contentPane.add(lblJson);

		lblImage = new JLabel("");
		lblImage.setBounds(10, 94, 603, 377);
		contentPane.add(lblImage);
	}

	public void load() throws IOException {
		// 获取，并显示json
		String url = "https://dog.ceo/api/breeds/image/random";
		String json = HttpTools.doGetString(url);

		// 解析出 image url
		Gson gson = new Gson();
		DogJson dog = gson.fromJson(json, DogJson.class);
		String imageUrl = dog.getMessage();
		lblJson.setText(imageUrl);

		// 获取图像
		BufferedImage image = ImageIO.read(new URL(imageUrl));

//		JLabel lbl = new JLabel(new ImageIcon(image));
//		contentPane.add(lbl);
		// 显示
		lblImage.setIcon(new ImageIcon(image));
	}
}
