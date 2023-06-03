package com.turkcellGY.commonpackage;

import com.turkcellGY.commonpackage.utils.constans.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Rental.ServiceBasePackage})
public class CommonPackageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonPackageApplication.class, args);
	}

}
