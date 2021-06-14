package ua.lviv.iot.greenhouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.greenhouse.models.Sensor;
import ua.lviv.iot.greenhouse.models.type.SensorType;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorDAO extends JpaRepository<Sensor, Long> {

    List<Sensor> findSensorByData_SensorType(SensorType sensorType);

    List<Sensor> findSensorByData_LocalDateTimeBetween(LocalDateTime after, LocalDateTime before);

    List<Sensor> findSensorByData_SensorTypeAndData_LocalDateTimeBetween(SensorType sensorType, LocalDateTime after,
                                                                         LocalDateTime before);

    void deleteSensorByData_SensorType(SensorType sensorType);

    void deleteSensorByData_LocalDateTimeBetween(LocalDateTime after, LocalDateTime before);

    void deleteSensorByData_SensorTypeAndData_LocalDateTimeBetween(SensorType sensorType, LocalDateTime after,
                                                                   LocalDateTime before);

    Sensor findSensorById(Long id);
}
