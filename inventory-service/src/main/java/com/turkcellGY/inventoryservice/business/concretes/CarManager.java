package com.turkcellGY.inventoryservice.business.concretes;

import com.turkcellGY.commonpackage.events.inventory.CarCreatedEvent;
import com.turkcellGY.commonpackage.events.inventory.CarDeletedEvent;
import com.turkcellGY.commonpackage.utils.dto.ClientResponse;
import com.turkcellGY.commonpackage.utils.exceptions.BusinessException;
import com.turkcellGY.commonpackage.utils.kafka.producer.KafkaProducer;
import com.turkcellGY.commonpackage.utils.mappers.ModelMapperService;
import com.turkcellGY.inventoryservice.business.abstracts.CarService;
import com.turkcellGY.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.turkcellGY.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.turkcellGY.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.turkcellGY.commonpackage.utils.dto.GetCarResponse;
import com.turkcellGY.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import com.turkcellGY.inventoryservice.business.rules.CarBusinessRules;
import com.turkcellGY.inventoryservice.entities.Car;
import com.turkcellGY.commonpackage.utils.enums.State;
import com.turkcellGY.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapperService mapper;
    private final CarBusinessRules rules;
    private final KafkaProducer producer;

    @Override
    public List<GetAllCarsResponse> getAll() {
        var cars = repository.findAll();
        var response = cars
                .stream()
                .map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        rules.checkIfCarExists(id);
        var car = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID());
        car.setState(State.Available);
        var createdCar = repository.save(car);
        sendKafkaCarCreatedEvent(createdCar);

        var response = mapper.forResponse().map(createdCar, CreateCarResponse.class);

        return response;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        rules.checkIfCarExists(id);
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(id);
        repository.save(car);
        var response = mapper.forResponse().map(car, UpdateCarResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCarExists(id);
        repository.deleteById(id);
        sendKafkaCarDeletedEvent(id);
    }

    @Override
    public ClientResponse checkIfCarAvailable(UUID id) {
        var response = new ClientResponse();
        validateCarAvailability(id, response);
        return response;
    }

    @Override
    public void changeStateByCarId(State state, UUID id) {
        repository.changeStateByCarId(state, id);
    }

    private void sendKafkaCarDeletedEvent(UUID id) {
        //Car deleted event
        CarDeletedEvent event = new CarDeletedEvent();
        event.setCarId(id);
        producer.sendMessage(event, "car-deleted");
    }

    private void sendKafkaCarCreatedEvent(Car createdCar) {
        // Car created event
        var event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);
        producer.sendMessage(event, "car-created");
    }

    private void validateCarAvailability(UUID id, ClientResponse response) {
        try {
            rules.checkIfCarExists(id);
            rules.checkIfCarAvailability(id);
            response.setSuccess(true);

        } catch (BusinessException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
    }
}
