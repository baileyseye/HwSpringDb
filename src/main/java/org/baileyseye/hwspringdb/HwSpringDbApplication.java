package org.baileyseye.hwspringdb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HwSpringDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwSpringDbApplication.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

	}

}
