package ua.lviv.iot.greenhouse.services;

import ua.lviv.iot.greenhouse.dto.SensorDTO;
import ua.lviv.iot.greenhouse.models.Sensor;
import ua.lviv.iot.greenhouse.models.type.SensorType;

import java.util.List;

public interface SensorService {

    Sensor createSensorData(SensorDTO sensorDTO);

    List<Sensor> getAllSensorData(String date);

    List<Sensor> getSensorDataBySensorType(SensorType sensorType, String date);

    Sensor updateDataById(Long id, double currentData);

    void deleteAllSensorData(String date);

    void deleteSensorDataBySensorType(SensorType sensorType, String date);
}
