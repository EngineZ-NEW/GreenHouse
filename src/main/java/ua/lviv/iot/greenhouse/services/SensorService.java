package ua.lviv.iot.greenhouse.services;

import ua.lviv.iot.greenhouse.models.Sensor;
import ua.lviv.iot.greenhouse.models.type.SensorType;

import java.util.List;

public interface SensorService {

    Sensor createSensorData(Sensor sensor);

    List<Sensor> getAllSensorData();

    List<Sensor> getAllSensorDataForDate(String date);

    List<Sensor> getSensorDataBySensorType(SensorType sensorType);

    List<Sensor> getSensorDataForDate(String date, SensorType sensorType);

    Sensor updateDataById(Long id, double currentData);

    void deleteAllSensorData();

    void deleteAllSensorDataForDate(String date);

    void deleteSensorDataBySensorType(SensorType sensorType);

    void deleteSensorDataForDate(String date, SensorType sensorType);
}
