package c02;

public class GuessGame {
	Player p1;
	
	public void startGame() {
		// 创建玩家
		p1 = new Player();
		
		// 生成谜底数
		int target = (int)(Math.random()*10);
		System.out.println("target is " + target);
		
		// 循环
		while (true) {
			// 玩家猜
			p1.guess();
			System.out.println("玩家猜 " + p1.number);
			
			// 检查玩家猜测是否正确
			if (target == p1.number) {
				System.out.println("猜中");
				break;
			} else {
				System.out.println("没猜中");
			}
		}
	}

}
