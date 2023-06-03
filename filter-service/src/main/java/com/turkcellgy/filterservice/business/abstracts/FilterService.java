package com.turkcellgy.filterservice.business.abstracts;

import com.turkcellgy.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.turkcellgy.filterservice.business.dto.responses.GetFilterResponse;
import com.turkcellgy.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();
    GetFilterResponse getById(UUID id);
    void add(Filter filter);
    void delete(UUID id);
    void deleteByCarId(UUID carId);
    void deleteAllByBrandId(UUID brandId);
    void deleteAllByModelId(UUID modelId);
    Filter getByCarId(UUID carId);
}
