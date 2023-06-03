package com.turkcellGY.rentalservice.business.concretes;

import com.turkcellGY.commonpackage.events.rental.RentalCreatedEvent;
import com.turkcellGY.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellGY.rentalservice.api.clients.CarClient;
import com.turkcellGY.rentalservice.business.abstracts.RentalService;
import com.turkcellGY.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcellGY.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.turkcellGY.rentalservice.business.dto.responses.CreateRentalResponse;
import com.turkcellGY.rentalservice.business.dto.responses.GetAllRentalsResponse;
import com.turkcellGY.rentalservice.business.dto.responses.GetRentalResponse;
import com.turkcellGY.rentalservice.business.dto.responses.UpdateRentalResponse;
import com.turkcellGY.rentalservice.business.kafka.producer.RentalProducer;
import com.turkcellGY.rentalservice.business.rules.RentalBusinessRules;
import com.turkcellGY.rentalservice.entities.Rental;
import com.turkcellGY.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;
    private final CarClient carClient;
    private final RentalProducer producer;
    @Override
    public List<GetAllRentalsResponse> getAll() {
        var rentals = repository.findAll();
        var response = rentals
                .stream()
                .map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        var rental = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        carClient.checkIfCarAvailable(request.getCarId());
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setRentedAt(LocalDate.now());
        repository.save(rental);
        sendKafkaRentalCreatedEvent(request.getCarId());
        var response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        return response;
    }
    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);
        var response = mapper.forResponse().map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental){
        return rental.getDailyPrice()*rental.getRentedForDays();
    }
    private void sendKafkaRentalCreatedEvent(UUID carId) {
        producer.sendMessage(new RentalCreatedEvent(carId));
    }
}
