package com.nowcoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.nowcoder.*")
public class ToutiaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToutiaoApplication.class, args);
	}
}
