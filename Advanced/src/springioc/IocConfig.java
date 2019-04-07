package springioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("springioc")
public class IocConfig {
	@Bean
	HelloWorld helloWorld() {
		HelloWorld o = new HelloWorld();
		o.setMessage("init message");
		return o;
	}
}
