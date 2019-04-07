package thread.bank;

import java.util.concurrent.BlockingQueue;

// 模拟柜员类
public class Teller {
	private static int gId = 0;
	private final int id = gId++; // 柜员Id
	// 阻塞队列，柜员从队列中获取等待的顾客
	private BlockingQueue<Customer> queue;
	// 服务线程
	private Thread thread;
	private boolean isRunning = false;
	
	// 服务监听器，用于传递消息
	private TellerListener listener;
	
	

	public Teller(BlockingQueue<Customer> queue) {
		this.queue = queue;
	}

	public int getId() {
		return id;
	}

	public TellerListener getListener() {
		return listener;
	}

	public void setListener(TellerListener listener) {
		this.listener = listener;
	}

	// 开始服务，从等待队列中取出顾客服务，
	public void startServe() {
		System.out.println(id + "号柜员开始服务");
		isRunning = true;
		thread = new Thread(() -> {
			while (isRunning) {
				try {
					if (listener != null) {
						listener.onServe(null);
					}
					Customer customer = queue.take();
					if (listener != null) {
						listener.onServe(customer);
					}
					// 随机暂停5-10秒，表示服务时间
					int t = (int)(Math.random()*5000 + 5000);
					System.out.printf("%d号柜员为%d号顾客服务，时间%d秒\n", id, customer.getId(), t);
					Thread.sleep(t);
				} catch (InterruptedException e) {
					// e.printStackTrace();
					System.out.println(e);
				}
			}
		});
		thread.start();
	}

	public void stopServe() {
		if (thread == null) {
			return;
		}
		isRunning = false;
		thread.interrupt();
		thread = null;
		System.out.println(id + "号柜员停止服务");
	}
}
