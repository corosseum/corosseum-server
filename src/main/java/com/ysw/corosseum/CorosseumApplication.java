package com.ysw.corosseum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CorosseumApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorosseumApplication.class, args);
	}

}
