package ua.lviv.iot.greenhouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.greenhouse.models.SensorData;
import ua.lviv.iot.greenhouse.models.SensorType;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorDataDAO extends JpaRepository<SensorData, Long> {

    List<SensorData> findSensorDataBySensorType(SensorType sensorType);

    List<SensorData> findSensorDataByLocalDateTimeBetween(LocalDateTime after, LocalDateTime before);

    List<SensorData> findSensorDataBySensorTypeAndLocalDateTimeBetween(SensorType sensorType, LocalDateTime after,
                                                                       LocalDateTime before);

    void deleteSensorDataBySensorType(SensorType sensorType);

    void deleteSensorDataByLocalDateTimeBetween(LocalDateTime after, LocalDateTime before);

    void deleteSensorDataBySensorTypeAndLocalDateTimeBetween(SensorType sensorType, LocalDateTime after,
                                                             LocalDateTime before);

    SensorData findSensorDataById(Long id);
}
