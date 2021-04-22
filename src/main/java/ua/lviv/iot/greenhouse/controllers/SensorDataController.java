package ua.lviv.iot.greenhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.greenhouse.models.SensorData;
import ua.lviv.iot.greenhouse.models.SensorType;
import ua.lviv.iot.greenhouse.services.SensorDataService;

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
    public List<SensorData> getAllSensorData() {
        return sensorDataService.getAllSensorData();
    }

    @GetMapping("/temperature-data")
    public List<SensorData> getTemperature() {
        return sensorDataService.getSensorDataBySensorType(SensorType.TEMPERATURE);
    }

    @GetMapping("/humidity-data")
    public List<SensorData> getHumidity() {
        return sensorDataService.getSensorDataBySensorType(SensorType.HUMIDITY);
    }

    @GetMapping("/luminosity-data")
    public List<SensorData> getLuminosity() {
        return sensorDataService.getSensorDataBySensorType(SensorType.LUMINOSITY);
    }

    // date should be in format yyyy-mm-dd
    @GetMapping("/{date}")
    public List<SensorData> getAllSensorDataForDay(final @PathVariable("date") String date) {
        return sensorDataService.getAllSensorDataForDate(date);
    }

    @GetMapping("/temperature-data/{date}")
    public List<SensorData> getTemperatureForDay(final @PathVariable("date") String date) {
        return sensorDataService.getSensorDataForDate(date, SensorType.TEMPERATURE);
    }

    @GetMapping("/humidity-data/{date}")
    public List<SensorData> getHumidityDay(final @PathVariable("date") String date) {
        return sensorDataService.getSensorDataForDate(date, SensorType.HUMIDITY);
    }

    @GetMapping("/luminosity-data/{date}")
    public List<SensorData> getLuminosityForDay(final @PathVariable("date") String date) {
        return sensorDataService.getSensorDataForDate(date, SensorType.LUMINOSITY);
    }

    @PostMapping("/temperature-data")
    public SensorData addTemperatureData(final @RequestBody SensorData sensorData) {
        return sensorDataService.createSensorData(sensorData, SensorType.TEMPERATURE);
    }

    @PostMapping("/humidity-data")
    public SensorData addHumidityData(final @RequestBody SensorData sensorData) {
        return sensorDataService.createSensorData(sensorData, SensorType.HUMIDITY);
    }

    @PostMapping("/luminosity-data")
    public SensorData addLuminosityData(final @RequestBody SensorData sensorData) {
        return sensorDataService.createSensorData(sensorData, SensorType.LUMINOSITY);
    }

    @DeleteMapping
    public void deleteAllSensorData() {
        sensorDataService.deleteAllSensorData();
    }

    @DeleteMapping("/temperature-data")
    public void deleteTemperature() {
        sensorDataService.deleteSensorDataBySensorType(SensorType.TEMPERATURE);
    }

    @DeleteMapping("/humidity-data")
    public void deleteHumidity() {
        sensorDataService.deleteSensorDataBySensorType(SensorType.HUMIDITY);
    }

    @DeleteMapping("/luminosity-data")
    public void deleteLuminosity() {
        sensorDataService.deleteSensorDataBySensorType(SensorType.LUMINOSITY);
    }

    @DeleteMapping("/{date}")
    public void deleteSensorDataForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteAllSensorDataForDate(date);
    }

    @DeleteMapping("/temperature-data/{date}")
    public void deleteTemperatureForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteSensorDataForDate(date, SensorType.TEMPERATURE);
    }

    @DeleteMapping("/humidity-data/{date}")
    public void deleteHumidityForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteSensorDataForDate(date, SensorType.HUMIDITY);
    }

    @DeleteMapping("/luminosity-data/{date}")
    public void deleteLuminosityForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteSensorDataForDate(date, SensorType.LUMINOSITY);
    }

    @PutMapping("{id}")
    public SensorData updateDataById(final @RequestBody SensorData sensorData, final @PathVariable("id") Long id) {
        return sensorDataService.updateDataById(id, sensorData.getData());
    }
}
