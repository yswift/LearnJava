package thread.getimage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import io.HttpTools;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class ImageAPI extends JFrame {

	private JPanel contentPane;
	private JTextField txtImageCount;
	private JTextField txtThreadCount;
	private JScrollPane scrollPane;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageAPI frame = new ImageAPI();
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
	public ImageAPI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnlButton = new JPanel();
		contentPane.add(pnlButton, BorderLayout.NORTH);

		JButton btnClean = new JButton("清除图片");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.updateUI();
			}
		});
		pnlButton.add(btnClean);

		JLabel lblNewLabel = new JLabel("获取图片数");
		pnlButton.add(lblNewLabel);

		txtImageCount = new JTextField();
		txtImageCount.setText("5");
		pnlButton.add(txtImageCount);
		txtImageCount.setColumns(10);

		JButton btnFetch = new JButton("获取图片");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetch();
			}
		});
		pnlButton.add(btnFetch);

		JButton btnFetchSingleThread = new JButton("获取（单线程）");
		btnFetchSingleThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchSingleThread();
			}
		});
		pnlButton.add(btnFetchSingleThread);

		JButton btnFetchMultiThread = new JButton("获取（多线程）");
		btnFetchMultiThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchMultiThread();
			}
		});
		pnlButton.add(btnFetchMultiThread);

		JLabel lblThreadCount = new JLabel("线程数");
		pnlButton.add(lblThreadCount);

		txtThreadCount = new JTextField();
		txtThreadCount.setText("3");
		txtThreadCount.setColumns(10);
		pnlButton.add(txtThreadCount);

		JButton btnThreadPool = new JButton("获取（线程池）");
		btnThreadPool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchThreadPool();
			}
		});
		pnlButton.add(btnThreadPool);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
	}

	// 访问API，获取图片信息
	ImagePojo getImagePojo(int count) throws IOException {
		String url = "https://api.apiopen.top/getImages?count=" + count;
		String json = HttpTools.doGetString(url);
		System.out.println("json\n" + json);

		Gson gson = new Gson();
		return gson.fromJson(json, ImagePojo.class);
	}

	// 获取图片，如果有错误就返回null
	Image getImage(String url) {
		try {
			// 为了模拟网络慢，停2秒
			Thread.sleep(2000);
			Image image = ImageIO.read(new URL(url));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	void showImage(Image image) {
		if (image == null) {
			return;
		}
		JLabel lbl = new JLabel(new ImageIcon(image));
		SwingUtilities.invokeLater(() -> {
			panel.add(lbl);
			panel.updateUI();
		});
	}

	// 获取图片，不使用线程
	void fetch() {
		try {
			int count = Integer.parseInt(txtImageCount.getText());
			ImagePojo pojo = getImagePojo(count);
			for (ImageInfo imageInfo : pojo.getResult()) {
				System.out.println("fetch:" + imageInfo.getImg());
				Image image = getImage(imageInfo.getImg());
				System.out.println("image:" + image.toString());
				showImage(image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取图片，单线程
	void fetchSingleThread() {
		// Lambda 表达式
//		new Thread(() -> fetch()).start();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				fetch();
			}
		};
		Thread t = new Thread(r);
		t.start();
	}

	// 获取图片，多线程
	void fetchMultiThread() {
		try {
			int count = Integer.parseInt(txtImageCount.getText());
			ImagePojo pojo = getImagePojo(count);
			for (ImageInfo imageInfo : pojo.getResult()) {
				new Thread(() -> {
					System.out.println("fetch:" + imageInfo.getImg());
					Image image = getImage(imageInfo.getImg());
					System.out.println("image:" + image.toString());
					showImage(image);
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取图片，线程池
	void fetchThreadPool() {
		try {
			int count = Integer.parseInt(txtImageCount.getText());
			ImagePojo pojo = getImagePojo(count);
			// 创建线程池
			int tc = Integer.parseInt(txtThreadCount.getText());
			Executor exec = Executors.newFixedThreadPool(tc);
			for (ImageInfo imageInfo : pojo.getResult()) {
				exec.execute(() -> {
					System.out.println("fetch:" + imageInfo.getImg());
					Image image = getImage(imageInfo.getImg());
					System.out.println("image:" + image.toString());
					showImage(image);
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
