package org.baileyseye.hwspringdb;

import org.baileyseye.hwspringdb.product.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.baileyseye.hwspringdb.product")
public class HwSpringDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwSpringDbApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductService productService) {
		return (args) -> {
			productService.printAllProducts();
		};
	}

}
