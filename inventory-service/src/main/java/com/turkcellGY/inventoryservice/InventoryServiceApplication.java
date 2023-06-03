package com.turkcellGY.inventoryservice;

import com.turkcellGY.commonpackage.utils.constans.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Inventory.ServiceBasePackage})
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
}
