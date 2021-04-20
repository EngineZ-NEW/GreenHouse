package ua.lviv.iot.greenhouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.greenhouse.dao.SensorDataDAO;
import ua.lviv.iot.greenhouse.models.SensorData;

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

    public SensorData createSensorData(SensorData sensorData, int sensorId) {
        sensorData.setSensorId(sensorId);
        for (SensorData savedSensorData : sensorDataDAO.findSensorDataBySensorId(sensorId)) {
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

    public List<SensorData> getSensorData(int sensorId) {

        return sensorDataDAO.findSensorDataBySensorId(sensorId);
    }

    public List<SensorData> getSensorDataForDate(String date, int sensorId) {

        LocalDate localDate = LocalDate.parse(date);

        return sensorDataDAO.findSensorDataBySensorIdAndLocalDateTimeBetween(
                sensorId,
                localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX)
        );
    }

    public void deleteSensorData(int sensorId) {

        sensorDataDAO.deleteSensorDataBySensorId(sensorId);
    }

    public void deleteSensorDataForDate(String date, int sensorId) {

        LocalDate localDate = LocalDate.parse(date);

        sensorDataDAO.deleteSensorDataBySensorIdAndLocalDateTimeBetween(
                sensorId,
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
