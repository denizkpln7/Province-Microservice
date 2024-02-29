package org.denizkpln.provinceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProvinceserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvinceserviceApplication.class, args);
	}

}
