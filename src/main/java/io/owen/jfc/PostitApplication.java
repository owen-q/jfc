package io.owen.jfc;

import io.owen.jfc.util.Wakeup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableAspectJAutoProxy
//@ComponentScan("io.owen.jfc")
public class PostitApplication {

	@Autowired
	private static Wakeup wakeup;

	public static void main(String[] args) {
		SpringApplication.run(PostitApplication.class, args);
	}
}