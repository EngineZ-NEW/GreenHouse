package ua.lviv.iot.GreenHouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.GreenHouse.models.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {
}
