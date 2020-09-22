package com.nokia.ee.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableSwagger2  // swagger doc Url : http://localhost:8080/swagger-ui.html
public class ImportExportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImportExportApplication.class, args);
		System.out.println("Hello Nokia");
	}

}
