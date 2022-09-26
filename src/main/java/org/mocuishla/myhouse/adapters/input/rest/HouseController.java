package org.mocuishla.myhouse.adapters.input.rest;

import org.mocuishla.myhouse.domain.business.House;
import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Action> getActionsByType(@RequestParam(required = false) ActionType actionType, @RequestParam(required = false) Optional<Double> temperature) {
        if (actionType != null) {
            return house.getAllActionsByType(actionType);
        }
        if (temperature.isPresent()) {
            return house.getAllActionsByTemperature(temperature.get());
        }

        return house.getAllActions();
    }

}
