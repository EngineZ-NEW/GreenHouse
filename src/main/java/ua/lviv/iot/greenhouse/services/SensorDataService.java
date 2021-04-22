package ua.lviv.iot.greenhouse.services;

import ua.lviv.iot.greenhouse.models.SensorData;
import ua.lviv.iot.greenhouse.models.SensorType;

import java.util.List;

public interface SensorDataService {

    SensorData createSensorData(SensorData sensorData, SensorType sensorType);

    List<SensorData> getAllSensorData();

    List<SensorData> getAllSensorDataForDate(String date);

    List<SensorData> getSensorDataBySensorType(SensorType sensorType);

    List<SensorData> getSensorDataForDate(String date, SensorType sensorType);

    SensorData updateDataById(Long id, double data);

    void deleteAllSensorData();

    void deleteAllSensorDataForDate(String date);

    void deleteSensorDataBySensorType(SensorType sensorType);

    void deleteSensorDataForDate(String date, SensorType sensorType);
}
