package thread.base;

public class RunThreads implements Runnable {
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i=0; i<25; i++) {
			System.out.println(name + " is running");
		}
	}
	
	public static void main(String[] args) {
		RunThreads runner = new RunThreads();
		Thread alpha = new Thread(runner);
		Thread beta = new Thread(runner);
		alpha.setName("Alpha");
		beta.setName("Beta");
		alpha.start();
		beta.start();
	}
}
