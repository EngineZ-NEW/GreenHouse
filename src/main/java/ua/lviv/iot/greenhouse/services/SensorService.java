package ua.lviv.iot.greenhouse.services;

import ua.lviv.iot.greenhouse.models.Sensor;
import ua.lviv.iot.greenhouse.models.SensorType;

import java.util.List;

public interface SensorService {

    Sensor createSensorData(Sensor sensor, SensorType sensorType);

    List<Sensor> getAllSensorData();

    List<Sensor> getAllSensorDataForDate(String date);

    List<Sensor> getSensorDataBySensorType(SensorType sensorType);

    List<Sensor> getSensorDataForDate(String date, SensorType sensorType);

    Sensor updateDataById(Long id, double data);

    void deleteAllSensorData();

    void deleteAllSensorDataForDate(String date);

    void deleteSensorDataBySensorType(SensorType sensorType);

    void deleteSensorDataForDate(String date, SensorType sensorType);
}
