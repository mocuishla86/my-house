package org.mocuishla.myhouse.adapters.input.rest;

import org.mocuishla.myhouse.domain.business.House;
import org.mocuishla.myhouse.domain.business.model.Action;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseController {

    private House house;

    public HouseController(House house) {
        this.house = house;
    }

    @PostMapping("/temperature")
    public void registerTemperature(@RequestBody TemperatureDTO temperatureDTO) {
        house.setTemperature(temperatureDTO.getTemperature());
    }

    @PostMapping("/humidity")
    public void registerHumidity(@RequestBody HumidityDTO humidityDTO) {
        house.setHumidity(humidityDTO.getHumidity());
    }

    @GetMapping("/actions")
    public List<Action> getActions() {
        return house.getAllActions();
    }
}
