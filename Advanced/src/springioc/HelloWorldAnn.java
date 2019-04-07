package springioc;

import org.springframework.stereotype.Component;

// 基于注解的配置
@Component("helloWorldAnn")
public class HelloWorldAnn {
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public void getMessage() {
		System.out.println("Your Message : " + message);
	}
}
