package com.turkcellgy.filterservice.api.controller;

import com.turkcellgy.filterservice.business.abstracts.FilterService;
import com.turkcellgy.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.turkcellgy.filterservice.business.dto.responses.GetFilterResponse;
import com.turkcellgy.filterservice.entities.Filter;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/filters")
public class FiltersController {
    private final FilterService service;

//    Databaseyi oluşturmak için
//    @PostConstruct
//    public void createDb(){
//        System.err.println("Post Construct Çalıştı");
//        System.err.println("Post Construct Çalıştı");
//        System.err.println("Post Construct Çalıştı");
//        System.err.println("Post Construct Çalıştı");
//        System.err.println("Post Construct Çalıştı");
//        service.add(new Filter());
//    }
    @GetMapping
    public List<GetAllFiltersResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFilterResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }
}