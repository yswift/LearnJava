package thread.base;

public class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("top o' the stack");
	}
}
