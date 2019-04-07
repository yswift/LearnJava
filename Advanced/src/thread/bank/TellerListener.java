package thread.bank;

// 用于柜员为顾客服务时的通知
public interface TellerListener {
	public void onServe(Customer customer);
}
