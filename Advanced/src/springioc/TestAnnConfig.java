package springioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAnnConfig {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);
		HelloWorldAnn objA = (HelloWorldAnn) context.getBean("helloWorldAnn");
		objA.setMessage("I'm object A");
		objA.getMessage();
	}

}
