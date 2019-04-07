package springioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJavaConfig {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);
		HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
		objA.getMessage();
	}
}
