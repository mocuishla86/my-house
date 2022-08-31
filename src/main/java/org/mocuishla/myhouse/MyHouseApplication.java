package org.mocuishla.myhouse;

import org.mocuishla.myhouse.configuration.AdaptersConfiguration;
import org.mocuishla.myhouse.configuration.DomainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DomainConfiguration.class, AdaptersConfiguration.class})
public class MyHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHouseApplication.class, args);
	}

}
