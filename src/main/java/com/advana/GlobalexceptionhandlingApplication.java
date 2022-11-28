package com.advana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients
public class GlobalexceptionhandlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalexceptionhandlingApplication.class, args);
	}

}
