package ua.lviv.iot.GreenHouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.GreenHouse.models.SensorData;
import ua.lviv.iot.GreenHouse.services.SensorDataService;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @GetMapping
    public List<SensorData> getSensorData() {
        return sensorDataService.getSensorData();
    }

    // date should be in format yyyy-mm-dd
    @GetMapping("/{date}")
    public List<SensorData> getSensorDataForDay(final @PathVariable("date") String date) {
        return sensorDataService.getSensorDataForDate(date);
    }

    @PostMapping
    public SensorData addSensorData(final @RequestBody SensorData sensorData) {
        return sensorDataService.createSensorData(sensorData);
    }

    @DeleteMapping
    public void deleteSensorData() {
        sensorDataService.deleteSensorData();
    }

    @DeleteMapping("/{date}")
    public void deleteSensorDataForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteSensorDataForDate(date);
    }
}
