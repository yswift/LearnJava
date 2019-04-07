package thread.base;

public class TestSync implements Runnable {
	int balance;

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			increment();
			System.out.println("balance is: " + balance);
		}
	}

	public synchronized void increment() {
		int i = balance;
		try {
			Thread.sleep((int) (Math.random() * 5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		balance = i + 1;
	}
}
