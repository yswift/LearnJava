package thread.bank;

public class Customer {
	private static int gId = 0;
	
	private final int id = ++gId;
	
	public int getId() {
		return id;
	}
}
