package ua.lviv.iot.greenhouse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.greenhouse.dao.SensorDataDAO;
import ua.lviv.iot.greenhouse.models.SensorData;
import ua.lviv.iot.greenhouse.models.SensorType;
import ua.lviv.iot.greenhouse.services.SensorDataService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional // At class level is applied to all methods of the class
public class SensorDataServiceImpl implements SensorDataService {

    private final SensorDataDAO sensorDataDAO;

    @Autowired
    public SensorDataServiceImpl(SensorDataDAO sensorDataDAO) {
        this.sensorDataDAO = sensorDataDAO;
    }

    @Override
    public SensorData createSensorData(SensorData sensorData, SensorType sensorType) {
        sensorData.setSensorType(sensorType);
        for (SensorData savedSensorData : sensorDataDAO.findSensorDataBySensorType(sensorType)) {
            if (savedSensorData.getLocalDateTime().equals(sensorData.getLocalDateTime())) {
                throw new IllegalStateException("There already is Data for this time!");
            }
        }
        sensorDataDAO.save(sensorData);
        return sensorData;
    }

    @Override
    public List<SensorData> getAllSensorData() {
        return sensorDataDAO.findAll();
    }

    @Override
    public List<SensorData> getAllSensorDataForDate(String date) {

        LocalDate localDate = LocalDate.parse(date);

        return sensorDataDAO.findSensorDataByLocalDateTimeBetween(
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    @Override
    public List<SensorData> getSensorDataBySensorType(SensorType sensorType) {

        return sensorDataDAO.findSensorDataBySensorType(sensorType);
    }

    @Override
    public List<SensorData> getSensorDataForDate(String date, SensorType sensorType) {

        LocalDate localDate = LocalDate.parse(date);

        return sensorDataDAO.findSensorDataBySensorTypeAndLocalDateTimeBetween(
                sensorType,
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    @Override
    public SensorData updateDataById(Long id, double data) {

        if (!sensorDataDAO.existsById(id)) {
            throw new IllegalStateException("There is no data with id " + id);
        }

        SensorData sensorData = sensorDataDAO.findSensorDataById(id);
        sensorData.setData(data);

        sensorDataDAO.save(sensorData);
        return sensorData;
    }

    @Override
    public void deleteAllSensorData() {
        sensorDataDAO.deleteAll();
    }

    @Override
    public void deleteAllSensorDataForDate(String date) {

        LocalDate localDate = LocalDate.parse(date);

        sensorDataDAO.deleteSensorDataByLocalDateTimeBetween(
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    @Override
    public void deleteSensorDataBySensorType(SensorType sensorType) {

        sensorDataDAO.deleteSensorDataBySensorType(sensorType);
    }

    @Override
    public void deleteSensorDataForDate(String date, SensorType sensorType) {

        LocalDate localDate = LocalDate.parse(date);

        sensorDataDAO.deleteSensorDataBySensorTypeAndLocalDateTimeBetween(
                sensorType,
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }
}
