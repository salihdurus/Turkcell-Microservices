package com.turkcellgy.filterservice.business.concrete;

import com.turkcellGY.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellgy.filterservice.business.abstracts.FilterService;
import com.turkcellgy.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.turkcellgy.filterservice.business.dto.responses.GetFilterResponse;
import com.turkcellgy.filterservice.entities.Filter;
import com.turkcellgy.filterservice.repository.FilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllFiltersResponse> getAll() {
        var filters = repository.findAll();
        var response = filters.stream()
                .map(filter ->
                        mapper.forResponse()
                                .map(filter, GetAllFiltersResponse.class)).toList();
        return response;
    }

    @Override
    public GetFilterResponse getById(UUID id) {
        var filter = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(filter, GetFilterResponse.class);
        return response;
    }

    @Override
    public void add(Filter filter) {
        filter.setId(UUID.randomUUID());
        repository.save(filter);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllByBrandId(UUID brandId) {
    }

    @Override
    public void deleteAllByModelId(UUID modelId) {

    }
}
