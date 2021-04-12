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


    private static final int TEMPERATURE_SENSOR_ID = 102;
    private static final int HUMIDITY_SENSOR_ID = 103;
    private static final int LUMINOSITY_SENSOR_ID = 104;


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
        return sensorDataService.getSensorData(TEMPERATURE_SENSOR_ID);
    }

    @GetMapping("/humidity-data")
    public List<SensorData> getHumidity() {
        return sensorDataService.getSensorData(HUMIDITY_SENSOR_ID);
    }

    @GetMapping("/luminosity-data")
    public List<SensorData> getLuminosity() {
        return sensorDataService.getSensorData(LUMINOSITY_SENSOR_ID);
    }

    // date should be in format yyyy-mm-dd
    @GetMapping("/{date}")
    public List<SensorData> getAllSensorDataForDay(final @PathVariable("date") String date) {
        return sensorDataService.getAllSensorDataForDate(date);
    }

    @GetMapping("/temperature-data/{date}")
    public List<SensorData> getTemperatureForDay(final @PathVariable("date") String date) {
        return sensorDataService.getSensorDataForDate(date, TEMPERATURE_SENSOR_ID);
    }

    @GetMapping("/humidity-data/{date}")
    public List<SensorData> getHumidityDay(final @PathVariable("date") String date) {
        return sensorDataService.getSensorDataForDate(date, HUMIDITY_SENSOR_ID);
    }

    @GetMapping("/luminosity-data/{date}")
    public List<SensorData> getLuminosityForDay(final @PathVariable("date") String date) {
        return sensorDataService.getSensorDataForDate(date, LUMINOSITY_SENSOR_ID);
    }

    @PostMapping("/temperature-data")
    public SensorData addTemperatureData(final @RequestBody SensorData sensorData) {
        return sensorDataService.createSensorData(sensorData, TEMPERATURE_SENSOR_ID);
    }

    @PostMapping("/humidity-data")
    public SensorData addHumidityData(final @RequestBody SensorData sensorData) {
        return sensorDataService.createSensorData(sensorData, HUMIDITY_SENSOR_ID);
    }

    @PostMapping("/luminosity-data")
    public SensorData addLuminosityData(final @RequestBody SensorData sensorData) {
        return sensorDataService.createSensorData(sensorData, LUMINOSITY_SENSOR_ID);
    }

    @DeleteMapping
    public void deleteAllSensorData() {
        sensorDataService.deleteAllSensorData();
    }

    @DeleteMapping("/temperature-data")
    public void deleteTemperature() {
        sensorDataService.deleteSensorData(TEMPERATURE_SENSOR_ID);
    }

    @DeleteMapping("/humidity-data")
    public void deleteHumidity() {
        sensorDataService.deleteSensorData(HUMIDITY_SENSOR_ID);
    }

    @DeleteMapping("/luminosity-data")
    public void deleteLuminosity() {
        sensorDataService.deleteSensorData(LUMINOSITY_SENSOR_ID);
    }

    @DeleteMapping("/{date}")
    public void deleteSensorDataForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteAllSensorDataForDate(date);
    }

    @DeleteMapping("/temperature-data/{date}")
    public void deleteTemperatureForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteSensorDataForDate(date, TEMPERATURE_SENSOR_ID);
    }

    @DeleteMapping("/humidity-data/{date}")
    public void deleteHumidityForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteSensorDataForDate(date, HUMIDITY_SENSOR_ID);
    }

    @DeleteMapping("/luminosity-data/{date}")
    public void deleteLuminosityForDay(final @PathVariable("date") String date) {
        sensorDataService.deleteSensorDataForDate(date, LUMINOSITY_SENSOR_ID);
    }

    @PutMapping("{id}")
    public SensorData updateDataById(final @RequestBody SensorData sensorData,
                                       final @PathVariable("id") int id) {
        return sensorDataService.updateDataById(id, sensorData.getData());
    }
}
