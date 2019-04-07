package c03;

public class TestPerson {
/**
 *  求平均身高
 * @param persons Person数组
 * @return 给定数组的平均身高
 */
double avgHeight(Person[] persons) {
	return 0;
}
	
	public static void main(String[] args) {
		int a = 10;
		int b = a;
		a = 20;
		System.out.println("a=" + a + ", b=" + b);
		
		Person p1 = new Person();
		p1.name = "张三";
		Person p2 = p1;
		p2.name = "李四";
		System.out.println("p1.name=" + p1.name + ", p2.name=" + p2.name);
	}

}
