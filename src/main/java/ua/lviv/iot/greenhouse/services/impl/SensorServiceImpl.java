package ua.lviv.iot.greenhouse.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.greenhouse.dao.SensorDAO;
import ua.lviv.iot.greenhouse.dto.SensorDTO;
import ua.lviv.iot.greenhouse.exception.NoDataFoundException;
import ua.lviv.iot.greenhouse.models.Sensor;
import ua.lviv.iot.greenhouse.models.type.SensorType;
import ua.lviv.iot.greenhouse.services.SensorService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
            List<Sensor> sensorData = sensorDAO.findAll();

            if (sensorData.isEmpty()) {
                throw new NoDataFoundException("There is no sensor data yet");
            } else {
                return sensorData;
            }

        } else {
            LocalDate localDate = LocalDate.parse(date);

            List<Sensor> sensorData = sensorDAO.findSensorByData_LocalDateTimeBetween(
                    localDate.atTime(LocalTime.MIN),
                    localDate.atTime(LocalTime.MAX)
            );

            if (sensorData.isEmpty()) {
                throw new NoDataFoundException("There is no sensor data for this time");
            } else {
                return sensorData;
            }
        }
    }

    @Override
    public List<Sensor> getSensorDataBySensorType(SensorType sensorType, String date) {
        if (date == null) {
            List<Sensor> sensorData = sensorDAO.findSensorByData_SensorType(sensorType);

            if (sensorData.isEmpty()) {
                throw new NoDataFoundException("There is no " + sensorType + " sensor data yet");
            } else {
                return sensorData;
            }

        } else {
            LocalDate localDate = LocalDate.parse(date);

            List<Sensor> sensorData = sensorDAO.findSensorByData_SensorTypeAndData_LocalDateTimeBetween(
                    sensorType,
                    localDate.atTime(LocalTime.MIN),
                    localDate.atTime(LocalTime.MAX)
            );

            if (sensorData.isEmpty()) {
                throw new NoDataFoundException("There is no " + sensorType + " sensor data for this time");
            } else {
                return sensorData;
            }
        }
    }

    @Override
    public Sensor updateDataById(Long id, double currentData) {
        if (!sensorDAO.existsById(id)) {
            throw new NoDataFoundException("There is no data for the sensor with ID " + id);
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
