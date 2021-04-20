package ua.lviv.iot.greenhouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.greenhouse.dao.SensorDataDAO;
import ua.lviv.iot.greenhouse.models.SensorData;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        List<SensorData> responseSensorData = new ArrayList<>();
        for (SensorData sensorData : sensorDataDAO.findAll()) {
            findDataForDate(date, responseSensorData, sensorData);
        }
        return responseSensorData;
    }

    private void findDataForDate(String date, List<SensorData> responseSensorData, SensorData sensorData) {
        LocalDateTime localDateTime = sensorData.getLocalDateTime();
        String receivedDate = String.format("%04d-%02d-%02d", localDateTime.getYear(),
                localDateTime.getMonth().getValue(), localDateTime.getDayOfMonth());
        if (receivedDate.equals(date))
            responseSensorData.add(sensorData);
    }

    public void deleteAllSensorDataForDate(String date) {
        for (SensorData sensorData : getAllSensorDataForDate(date)) {
            sensorDataDAO.delete(sensorData);
        }
    }

    public List<SensorData> getSensorData(int sensorId) {
        return sensorDataDAO.findSensorDataBySensorId(sensorId);
    }

    public List<SensorData> getSensorDataForDate(String date, int sensorId) {
        List<SensorData> responseSensorData = new ArrayList<>();
        for (SensorData sensorData : sensorDataDAO.findSensorDataBySensorId(sensorId)) {
            findDataForDate(date, responseSensorData, sensorData);
        }
        return responseSensorData;
    }

    public void deleteSensorData(int sensorId) {
        sensorDataDAO.deleteSensorDataBySensorId(sensorId);
    }

    public void deleteSensorDataForDate(String date, int sensorId) {
        for (SensorData sensorData : getSensorDataForDate(date, sensorId)) {
            sensorDataDAO.delete(sensorData);
        }
    }

    public SensorData updateDataById(Long id, double data) {
        boolean exists = sensorDataDAO.existsById(id);
        if (!exists)
            throw new IllegalStateException("There is no data with id " + id);
        SensorData sensorData = sensorDataDAO.findSensorDataById(id);
        sensorData.setData(data);
        sensorDataDAO.save(sensorData);
        return sensorData;
    }
}

