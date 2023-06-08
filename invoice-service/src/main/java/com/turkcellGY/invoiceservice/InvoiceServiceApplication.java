package com.turkcellGY.invoiceservice;

import com.turkcellGY.commonpackage.utils.constans.Paths;
import com.turkcellGY.invoiceservice.entities.Invoice;
import com.turkcellGY.invoiceservice.repository.InvoiceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "com.turkcellGY.invoiceservice.repository")
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Invoice.ServiceBasePackage})
public class InvoiceServiceApplication {
    private final InvoiceRepository repository;

    public InvoiceServiceApplication(InvoiceRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(InvoiceServiceApplication.class, args);
    }

    @PostConstruct
    public void add(){
        repository.save(new Invoice());
    }
}
