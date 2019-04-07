package thread.base;

public class TestTestSync {
	public static void main(String[] args) throws InterruptedException {
		TestSync job = new TestSync();
		Thread a = new Thread(job);
		Thread b = new Thread(job);
		a.start();
		b.start();
	}
}
