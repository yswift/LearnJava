package springioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// bean 作用域
public class TestScope {
	static void testSingleton() {
		ApplicationContext context = new ClassPathXmlApplicationContext("springioc/Beans.xml");
		HelloWorld objA = (HelloWorld) context.getBean("helloWorldSingleton");
		objA.setMessage("I'm object A");
		objA.getMessage();
		HelloWorld objB = (HelloWorld) context.getBean("helloWorldSingleton");
		objB.getMessage();
	}
	
	static void testPrototype() {
		ApplicationContext context = new ClassPathXmlApplicationContext("springioc/Beans.xml");
		HelloWorld objA = (HelloWorld) context.getBean("helloWorldPrototype");
		objA.setMessage("I'm object A");
		objA.getMessage();
		HelloWorld objB = (HelloWorld) context.getBean("helloWorldPrototype");
		objB.getMessage();
	}
	
	public static void main(String[] args) {
		testSingleton();
		testPrototype();
	}
}
