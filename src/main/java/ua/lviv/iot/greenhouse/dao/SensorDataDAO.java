package ua.lviv.iot.greenhouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.greenhouse.models.SensorData;

import java.util.List;

@Repository
public interface SensorDataDAO extends JpaRepository<SensorData, Long> {

    List<SensorData> findSensorDataBySensorId(int sensorId);

    void deleteSensorDataBySensorId(int sensorId);

    SensorData findSensorDataById(Long id);
}
