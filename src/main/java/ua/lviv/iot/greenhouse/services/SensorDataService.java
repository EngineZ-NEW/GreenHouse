package ua.lviv.iot.greenhouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.greenhouse.dao.SensorDataDAO;
import ua.lviv.iot.greenhouse.models.SensorData;
import ua.lviv.iot.greenhouse.models.SensorType;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional // At class level is applied to all methods of the class
public class SensorDataService {

    private final SensorDataDAO sensorDataDAO;

    @Autowired
    public SensorDataService(SensorDataDAO sensorDataDAO) {
        this.sensorDataDAO = sensorDataDAO;
    }

    public List<SensorData> getAllSensorData() {
        return sensorDataDAO.findAll();
    }

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

    public void deleteAllSensorData() {
        sensorDataDAO.deleteAll();
    }

    public List<SensorData> getAllSensorDataForDate(String date) {

        LocalDate localDate = LocalDate.parse(date);

        return sensorDataDAO.findSensorDataByLocalDateTimeBetween(
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    public void deleteAllSensorDataForDate(String date) {

        LocalDate localDate = LocalDate.parse(date);

        sensorDataDAO.deleteSensorDataByLocalDateTimeBetween(
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    public List<SensorData> getSensorData(SensorType sensorType) {

        return sensorDataDAO.findSensorDataBySensorType(sensorType);
    }

    public List<SensorData> getSensorDataForDate(String date, SensorType sensorType) {

        LocalDate localDate = LocalDate.parse(date);

        return sensorDataDAO.findSensorDataBySensorTypeAndLocalDateTimeBetween(
                sensorType,
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    public void deleteSensorData(SensorType sensorType) {

        sensorDataDAO.deleteSensorDataBySensorType(sensorType);
    }

    public void deleteSensorDataForDate(String date, SensorType sensorType) {

        LocalDate localDate = LocalDate.parse(date);

        sensorDataDAO.deleteSensorDataBySensorTypeAndLocalDateTimeBetween(
                sensorType,
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    public SensorData updateDataById(Long id, double data) {

        if (!sensorDataDAO.existsById(id)) {
            throw new IllegalStateException("There is no data with id " + id);
        }

        SensorData sensorData = sensorDataDAO.findSensorDataById(id);
        sensorData.setData(data);

        sensorDataDAO.save(sensorData);
        return sensorData;
    }
}
