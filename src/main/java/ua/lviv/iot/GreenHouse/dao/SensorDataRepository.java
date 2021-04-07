package ua.lviv.iot.GreenHouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.GreenHouse.models.SensorData;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {
    List<SensorData> findSensorDataBySensorId(int sensorId);

    @Transactional
    void deleteSensorDataBySensorId(int sensorId);
}