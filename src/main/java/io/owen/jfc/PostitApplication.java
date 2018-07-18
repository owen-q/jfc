package io.owen.jfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PostitApplication {
	public static void main(String[] args) {
		SpringApplication.run(PostitApplication.class, args);

	}
}