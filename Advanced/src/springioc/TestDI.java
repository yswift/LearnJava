package springioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 测试依赖注入
public class TestDI {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);
		TextEditor textEditor = (TextEditor) context.getBean("textEditor");
		textEditor.spellCheck();
	}
}
