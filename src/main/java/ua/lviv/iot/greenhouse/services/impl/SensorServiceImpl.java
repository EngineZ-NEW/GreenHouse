package ua.lviv.iot.greenhouse.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.greenhouse.dao.SensorDAO;
import ua.lviv.iot.greenhouse.dto.SensorDTO;
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
@Transactional
public class SensorServiceImpl implements SensorService {

    private final SensorDAO sensorDAO;

    @Override
    public Sensor createSensorData(SensorDTO sensorDTO) {
        return sensorDAO.save(new Sensor(new Sensor.Data(
                sensorDTO.getSensorType(),
                sensorDTO.getLocalDateTime(),
                sensorDTO.getCurrentData()
        )));
    }

    @Override
    public List<Sensor> getAllSensorData(String date) {
        if (date == null) {
            return sensorDAO.findAll();
        } else {
            LocalDate localDate = LocalDate.parse(date);

            return sensorDAO.findSensorByData_LocalDateTimeBetween(
                    localDate.atTime(LocalTime.MIN),
                    localDate.atTime(LocalTime.MAX)
            );
        }
    }

    @Override
    public List<Sensor> getSensorDataBySensorType(SensorType sensorType, String date) {
        if (date == null) {
            return sensorDAO.findSensorByData_SensorType(sensorType);
        } else {
            LocalDate localDate = LocalDate.parse(date);

            return sensorDAO.findSensorByData_SensorTypeAndData_LocalDateTimeBetween(
                    sensorType,
                    localDate.atTime(LocalTime.MIN),
                    localDate.atTime(LocalTime.MAX)
            );
        }
    }

    @Override
    public Sensor updateDataById(Long id, double currentData) {

        if (!sensorDAO.existsById(id)) {
            throw new IllegalStateException("There is no data with id " + id);
        }

        Sensor sensor = sensorDAO.findSensorById(id);
        sensor.getData().setCurrentData(currentData);

        return sensorDAO.save(sensor);
    }

    @Override
    public void deleteAllSensorData(String date) {
        if (date == null) {
            sensorDAO.deleteAll();
        } else {
            LocalDate localDate = LocalDate.parse(date);

            sensorDAO.deleteSensorByData_LocalDateTimeBetween(
                    localDate.atTime(LocalTime.MIN),
                    localDate.atTime(LocalTime.MAX)
            );
        }
    }

    @Override
    public void deleteSensorDataBySensorType(SensorType sensorType, String date) {
        if (date == null) {
            sensorDAO.deleteSensorByData_SensorType(sensorType);
        } else {
            LocalDate localDate = LocalDate.parse(date);

            sensorDAO.deleteSensorByData_SensorTypeAndData_LocalDateTimeBetween(
                    sensorType,
                    localDate.atTime(LocalTime.MIN),
                    localDate.atTime(LocalTime.MAX)
            );
        }

    }
}
