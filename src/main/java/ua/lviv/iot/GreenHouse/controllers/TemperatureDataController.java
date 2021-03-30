package ua.lviv.iot.GreenHouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.GreenHouse.models.TemperatureData;
import ua.lviv.iot.GreenHouse.services.TemperatureDataService;

import java.util.List;

@RestController
@RequestMapping("/sensors/temperature-data")
public class TemperatureDataController {

    private final TemperatureDataService temperatureDataService;

    @Autowired
    public TemperatureDataController(TemperatureDataService temperatureDataService) {
        this.temperatureDataService = temperatureDataService;
    }

    @GetMapping
    public List<TemperatureData> getTemperatureData() {
        return temperatureDataService.getTemperatureData();
    }

    @GetMapping("/{date}")
    public List<TemperatureData> getTemperatureDataForDay(final @PathVariable("date") String date) {
        return temperatureDataService.getTemperatureDataForDate(date);
    }

    @PostMapping
    public TemperatureData addTemperatureData(final @RequestBody TemperatureData temperatureData) {
        return temperatureDataService.createTemperatureData(temperatureData);
    }

    @DeleteMapping
    public void deleteTemperatureData() {
        temperatureDataService.deleteTemperatureData();
    }
}
