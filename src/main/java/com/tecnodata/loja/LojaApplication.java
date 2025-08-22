package com.tecnodata.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class LojaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}

}
