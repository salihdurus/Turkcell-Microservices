package com.turkcellGY.inventoryservice.business.concretes;

import com.turkcellGY.inventoryservice.business.abstracts.BrandService;
import com.turkcellGY.inventoryservice.business.dto.requests.create.CreateBrandRequest;
import com.turkcellGY.inventoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.turkcellGY.inventoryservice.business.dto.responses.create.CreateBrandResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.get.GetBrandResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.update.UpdateBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    @Override
    public List<GetAllBrandsResponse> getAll() {
        return null;
    }

    @Override
    public GetBrandResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        return null;
    }

    @Override
    public UpdateBrandResponse update(UUID id, UpdateBrandRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
