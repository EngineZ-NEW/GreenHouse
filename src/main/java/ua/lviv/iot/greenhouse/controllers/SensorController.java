package ua.lviv.iot.greenhouse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.greenhouse.models.Sensor;
import ua.lviv.iot.greenhouse.models.SensorType;
import ua.lviv.iot.greenhouse.services.SensorService;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    public List<Sensor> getAllSensorData() {
        return sensorService.getAllSensorData();
    }

    @GetMapping("/temperature-data")
    public List<Sensor> getTemperature() {
        return sensorService.getSensorDataBySensorType(SensorType.TEMPERATURE);
    }

    @GetMapping("/humidity-data")
    public List<Sensor> getHumidity() {
        return sensorService.getSensorDataBySensorType(SensorType.HUMIDITY);
    }

    @GetMapping("/luminosity-data")
    public List<Sensor> getLuminosity() {
        return sensorService.getSensorDataBySensorType(SensorType.LUMINOSITY);
    }

    // date should be in format yyyy-mm-dd
    @GetMapping("/{date}")
    public List<Sensor> getAllSensorDataForDay(final @PathVariable("date") String date) {
        return sensorService.getAllSensorDataForDate(date);
    }

    @GetMapping("/temperature-data/{date}")
    public List<Sensor> getTemperatureForDay(final @PathVariable("date") String date) {
        return sensorService.getSensorDataForDate(date, SensorType.TEMPERATURE);
    }

    @GetMapping("/humidity-data/{date}")
    public List<Sensor> getHumidityDay(final @PathVariable("date") String date) {
        return sensorService.getSensorDataForDate(date, SensorType.HUMIDITY);
    }

    @GetMapping("/luminosity-data/{date}")
    public List<Sensor> getLuminosityForDay(final @PathVariable("date") String date) {
        return sensorService.getSensorDataForDate(date, SensorType.LUMINOSITY);
    }

    @PostMapping("/temperature-data")
    public Sensor addTemperatureData(final @RequestBody Sensor sensor) {
        return sensorService.createSensorData(sensor, SensorType.TEMPERATURE);
    }

    @PostMapping("/humidity-data")
    public Sensor addHumidityData(final @RequestBody Sensor sensor) {
        return sensorService.createSensorData(sensor, SensorType.HUMIDITY);
    }

    @PostMapping("/luminosity-data")
    public Sensor addLuminosityData(final @RequestBody Sensor sensor) {
        return sensorService.createSensorData(sensor, SensorType.LUMINOSITY);
    }

    @DeleteMapping
    public void deleteAllSensorData() {
        sensorService.deleteAllSensorData();
    }

    @DeleteMapping("/temperature-data")
    public void deleteTemperature() {
        sensorService.deleteSensorDataBySensorType(SensorType.TEMPERATURE);
    }

    @DeleteMapping("/humidity-data")
    public void deleteHumidity() {
        sensorService.deleteSensorDataBySensorType(SensorType.HUMIDITY);
    }

    @DeleteMapping("/luminosity-data")
    public void deleteLuminosity() {
        sensorService.deleteSensorDataBySensorType(SensorType.LUMINOSITY);
    }

    @DeleteMapping("/{date}")
    public void deleteSensorDataForDay(final @PathVariable("date") String date) {
        sensorService.deleteAllSensorDataForDate(date);
    }

    @DeleteMapping("/temperature-data/{date}")
    public void deleteTemperatureForDay(final @PathVariable("date") String date) {
        sensorService.deleteSensorDataForDate(date, SensorType.TEMPERATURE);
    }

    @DeleteMapping("/humidity-data/{date}")
    public void deleteHumidityForDay(final @PathVariable("date") String date) {
        sensorService.deleteSensorDataForDate(date, SensorType.HUMIDITY);
    }

    @DeleteMapping("/luminosity-data/{date}")
    public void deleteLuminosityForDay(final @PathVariable("date") String date) {
        sensorService.deleteSensorDataForDate(date, SensorType.LUMINOSITY);
    }

    @PutMapping("{id}")
    public Sensor updateCurrentDataById(final @RequestBody Sensor sensor,
                                        final @PathVariable("id") Long id) {
        return sensorService.updateDataById(id, sensor.getData().getCurrentData());
    }
}
