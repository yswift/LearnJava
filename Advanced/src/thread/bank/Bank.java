package thread.bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.awt.event.ActionEvent;

public class Bank extends JFrame {

	private BlockingQueue<Customer> queue = new LinkedBlockingQueue<>();
	private Teller[] tellers = new Teller[3];
	private JToggleButton[] btnWindows = new JToggleButton[3];
	private JLabel lblMsg;
	private JLabel lblCustomerCount;
	private JLabel lblLastCustomer;

	private Thread autoAddCustomerThread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bank frame = new Bank();
					frame.init();
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
	public Bank() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 409);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JToggleButton btnWindow1 = new JToggleButton("窗口0");
		btnWindow1.setName("0");
		btnWindow1.setPreferredSize(new Dimension(120, 80));
		btnWindows[0] = btnWindow1;
		panel.add(btnWindow1);

		JToggleButton btnWindow2 = new JToggleButton("窗口1");
		btnWindow2.setName("1");
		btnWindow2.setPreferredSize(new Dimension(120, 80));
		btnWindows[1] = btnWindow2;
		panel.add(btnWindow2);

		JToggleButton btnWindow3 = new JToggleButton("窗口3");
		btnWindow3.setName("2");
		btnWindow3.setPreferredSize(new Dimension(120, 80));
		btnWindows[2] = btnWindow3;
		panel.add(btnWindow3);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		final JToggleButton btnAutoAddCustomer = new JToggleButton("自动产生顾客");
		btnAutoAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autoAddCustomer(btnAutoAddCustomer);
			}
		});
		btnAutoAddCustomer.setPreferredSize(new Dimension(140, 40));
		panel_1.add(btnAutoAddCustomer);

		JButton btnAddCustomer = new JButton("添加顾客");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer();
			}
		});
		btnAddCustomer.setPreferredSize(new Dimension(110, 40));
		panel_1.add(btnAddCustomer);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		lblMsg = new JLabel("叫号");
		lblMsg.setFont(new Font("宋体", Font.PLAIN, 22));
		lblMsg.setBounds(37, 13, 415, 51);
		panel_2.add(lblMsg);

		lblLastCustomer = new JLabel("最后一位顾客编号");
		lblLastCustomer.setFont(new Font("宋体", Font.PLAIN, 22));
		lblLastCustomer.setBounds(37, 122, 415, 51);
		panel_2.add(lblLastCustomer);

		lblCustomerCount = new JLabel("现排队人数");
		lblCustomerCount.setFont(new Font("宋体", Font.PLAIN, 22));
		lblCustomerCount.setBounds(37, 66, 415, 51);
		panel_2.add(lblCustomerCount);
	}

	void init() {
		// 监听按钮是否按下，按下启动柜员服务，弹起停止柜员服务
		ActionListener btnWindowActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取事件源（即开关按钮本身）
				JToggleButton btn = (JToggleButton) e.getSource();
				int id = Integer.parseInt(btn.getName());
				Teller teller = tellers[id];
				if (btn.isSelected()) {
					teller.startServe();
				} else {
					teller.stopServe();
					btn.setText("窗口" + btn.getName() + "停止服务");
				}
			}
		};

		for (int i = 0; i < tellers.length; i++) {
			final Teller teller = new Teller(queue);
			final JToggleButton btn = btnWindows[i];
			teller.setListener(new TellerListener() {
				@Override
				public void onServe(Customer customer) {
					if (customer == null) {
						btn.setText("窗口" + btn.getName() + "空闲");
						return;
					}
					SwingUtilities.invokeLater(() -> {
						lblMsg.setText("请第" + customer.getId() + "号顾客到" + teller.getId() + "窗口");
						btn.setText("服务顾客：" + customer.getId());
						lblCustomerCount.setText("现排队人数：" + queue.size());
					});
				}
			});
			tellers[i] = teller;

			// 监听按钮是否按下，按下表明柜员服务
			btnWindows[i].addActionListener(btnWindowActionListener);
		}
	}

	void addCustomer() {
		Customer customer = new Customer();
		queue.add(customer);
		SwingUtilities.invokeLater(() -> {
			lblCustomerCount.setText("现排队人数：" + queue.size());
			lblLastCustomer.setText("最后一位顾客编号:" + customer.getId());
		});
	}

	void autoAddCustomer(JToggleButton btn) {
		if (btn.isSelected()) {
			autoAddCustomerThread = new Thread(() -> {
				while (true) {
					addCustomer();
					// 随机暂停3-8秒，模拟客户到达的间隔时间
					int t = (int) (Math.random() * 5000 + 3000);
					System.out.printf("生成顾客，时间%d秒\n", t);
					try {
						Thread.sleep(t);
					} catch (InterruptedException e) {
						break;
					}
				}
			});
			autoAddCustomerThread.start();
		} else if (autoAddCustomerThread != null) {
			autoAddCustomerThread.interrupt();
			autoAddCustomerThread = null;
		}
	}

}
