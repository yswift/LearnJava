package thread.base;

// 启动线程的几种方式
public class ThreadTester {
	static void start1() {
		Runnable job = new MyRunnable();
		Thread t = new Thread(job);
		t.start();
	}

	static void start2() {
		new Thread(new MyRunnable()).start();
	}

	static void start3() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("top o' the stack");
			}
		});
		t.start();
	}

	static void start4() {
		Thread t = new Thread(() -> {
			System.out.println("top o' the stack");
		});
		t.start();
	}

	public static void main(String[] args) {
		start1();
		System.out.println("back in main");
	}
}
