package ua.lviv.iot.greenhouse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.greenhouse.dto.SensorDTO;
import ua.lviv.iot.greenhouse.models.Sensor;
import ua.lviv.iot.greenhouse.models.type.SensorType;
import ua.lviv.iot.greenhouse.services.SensorService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    public List<Sensor> getAllSensorData(@RequestParam(required = false) String date) {
        return sensorService.getAllSensorData(date);
    }

    @GetMapping("/temperature-data")
    public List<Sensor> getTemperature(@RequestParam(required = false) String date) {
        return sensorService.getSensorDataBySensorType(SensorType.TEMPERATURE, date);
    }

    @GetMapping("/humidity-data")
    public List<Sensor> getHumidity(@RequestParam(required = false) String date) {
        return sensorService.getSensorDataBySensorType(SensorType.HUMIDITY, date);
    }

    @GetMapping("/luminosity-data")
    public List<Sensor> getLuminosity(@RequestParam(required = false) String date) {
        return sensorService.getSensorDataBySensorType(SensorType.LUMINOSITY, date);
    }

    @PostMapping("/temperature-data")
    public Sensor addTemperatureData(final @RequestBody SensorDTO sensorDTO) {
        sensorDTO.setSensorType(SensorType.TEMPERATURE);
        sensorDTO.setLocalDateTime(LocalDateTime.now());
        return sensorService.createSensorData(sensorDTO);
    }

    @PostMapping("/humidity-data")
    public Sensor addHumidityData(final @RequestBody SensorDTO sensorDTO) {
        sensorDTO.setSensorType(SensorType.HUMIDITY);
        sensorDTO.setLocalDateTime(LocalDateTime.now());
        return sensorService.createSensorData(sensorDTO);
    }

    @PostMapping("/luminosity-data")
    public Sensor addLuminosityData(final @RequestBody SensorDTO sensorDTO) {
        sensorDTO.setSensorType(SensorType.LUMINOSITY);
        sensorDTO.setLocalDateTime(LocalDateTime.now());
        return sensorService.createSensorData(sensorDTO);
    }

    @DeleteMapping
    public void deleteAllSensorData(@RequestParam(required = false) String date) {
        sensorService.deleteAllSensorData(date);
    }

    @DeleteMapping("/temperature-data")
    public void deleteTemperature(@RequestParam(required = false) String date) {
        sensorService.deleteSensorDataBySensorType(SensorType.TEMPERATURE, date);
    }

    @DeleteMapping("/humidity-data")
    public void deleteHumidity(@RequestParam(required = false) String date) {
        sensorService.deleteSensorDataBySensorType(SensorType.HUMIDITY, date);
    }

    @DeleteMapping("/luminosity-data")
    public void deleteLuminosity(@RequestParam(required = false) String date) {
        sensorService.deleteSensorDataBySensorType(SensorType.LUMINOSITY, date);
    }

    @PutMapping("{id}")
    public Sensor updateCurrentDataById(final @RequestBody SensorDTO sensorDTO,
                                        final @PathVariable("id") Long id) {
        return sensorService.updateDataById(id, sensorDTO.getCurrentData());
    }
}
