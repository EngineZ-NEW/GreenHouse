package ua.lviv.iot.greenhouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.greenhouse.models.SensorData;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorDataDAO extends JpaRepository<SensorData, Long> {

    List<SensorData> findSensorDataBySensorId(int sensorId);

    List<SensorData> findSensorDataByLocalDateTimeBetween(LocalDateTime after, LocalDateTime before);

    List<SensorData> findSensorDataBySensorIdAndLocalDateTimeBetween(int id, LocalDateTime after, LocalDateTime before);

    void deleteSensorDataBySensorId(int sensorId);

    void deleteSensorDataByLocalDateTimeBetween(LocalDateTime after, LocalDateTime before);

    void deleteSensorDataBySensorIdAndLocalDateTimeBetween(int id, LocalDateTime after, LocalDateTime before);

    SensorData findSensorDataById(Long id);
}
