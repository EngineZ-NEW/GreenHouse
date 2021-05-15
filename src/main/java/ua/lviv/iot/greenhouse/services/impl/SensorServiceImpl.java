package ua.lviv.iot.greenhouse.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.greenhouse.dao.SensorDAO;
import ua.lviv.iot.greenhouse.models.Sensor;
import ua.lviv.iot.greenhouse.models.type.SensorType;
import ua.lviv.iot.greenhouse.services.SensorService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional // At class level is applied to all methods of the class
public class SensorServiceImpl implements SensorService {

    private final SensorDAO sensorDAO;

    @Override
    public Sensor createSensorData(Sensor sensor, SensorType sensorType) {

        Optional<Sensor> sensorOptional = sensorDAO.findSensorByData_SensorTypeAndData_LocalDateTime(sensorType,
                sensor.getData().getLocalDateTime());

        if (sensorOptional.isPresent()) {
            throw new IllegalStateException("There already is Data for this time!");
        }

        sensor.getData().setSensorType(sensorType);
        sensorDAO.save(sensor);
        return sensor;
    }

    @Override
    public List<Sensor> getAllSensorData() {
        return sensorDAO.findAll();
    }

    @Override
    public List<Sensor> getAllSensorDataForDate(String date) {

        LocalDate localDate = LocalDate.parse(date);

        return sensorDAO.findSensorByData_LocalDateTimeBetween(
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    @Override
    public List<Sensor> getSensorDataBySensorType(SensorType sensorType) {

        return sensorDAO.findSensorByData_SensorType(sensorType);
    }

    @Override
    public List<Sensor> getSensorDataForDate(String date, SensorType sensorType) {

        LocalDate localDate = LocalDate.parse(date);

        return sensorDAO.findSensorByData_SensorTypeAndData_LocalDateTimeBetween(
                sensorType,
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    @Override
    public Sensor updateDataById(Long id, double currentData) {

        if (!sensorDAO.existsById(id)) {
            throw new IllegalStateException("There is no data with id " + id);
        }

        Sensor sensor = sensorDAO.findSensorById(id);
        sensor.getData().setCurrentData(currentData);

        sensorDAO.save(sensor);
        return sensor;
    }

    @Override
    public void deleteAllSensorData() {
        sensorDAO.deleteAll();
    }

    @Override
    public void deleteAllSensorDataForDate(String date) {

        LocalDate localDate = LocalDate.parse(date);

        sensorDAO.deleteSensorByData_LocalDateTimeBetween(
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    @Override
    public void deleteSensorDataBySensorType(SensorType sensorType) {

        sensorDAO.deleteSensorByData_SensorType(sensorType);
    }

    @Override
    public void deleteSensorDataForDate(String date, SensorType sensorType) {

        LocalDate localDate = LocalDate.parse(date);

        sensorDAO.deleteSensorByData_SensorTypeAndData_LocalDateTimeBetween(
                sensorType,
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }
}
