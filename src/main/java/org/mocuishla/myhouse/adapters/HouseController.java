package org.mocuishla.myhouse.adapters;

import org.mocuishla.myhouse.adapters.fake.FakeActionRepository;
import org.mocuishla.myhouse.adapters.fake.FakeAirConditioner;
import org.mocuishla.myhouse.domain.House;
import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.AirConditioner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseController {

    private House house = new House(new FakeAirConditioner(), new FakeActionRepository());

    @PostMapping("/temperature")
    public void registerTemperature(@RequestBody TemperatureDTO temperatureDTO){
        house.setTemperature(temperatureDTO.getTemperature());
    }

    @PostMapping("/humidity")
    public void registerHumidity(@RequestBody HumidityDTO humidityDTO){
        house.setHumidity(humidityDTO.getHumidity());
    }

    @GetMapping("/actions")
    public List<Action> getActions(){
        return house.getAllActions();
    }
}
