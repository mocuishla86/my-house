package org.mocuishla.myhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MyHouseConfiguration.class)
public class MyHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHouseApplication.class, args);
	}

}
