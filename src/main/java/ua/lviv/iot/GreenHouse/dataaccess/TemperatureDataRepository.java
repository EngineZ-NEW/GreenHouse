package ua.lviv.iot.GreenHouse.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.GreenHouse.models.TemperatureData;

@Repository
public interface TemperatureDataRepository extends JpaRepository<TemperatureData, Integer> {
}
