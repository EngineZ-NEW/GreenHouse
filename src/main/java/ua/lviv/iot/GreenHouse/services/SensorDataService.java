package ua.lviv.iot.GreenHouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.GreenHouse.dao.SensorDataRepository;
import ua.lviv.iot.GreenHouse.models.SensorData;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }

    public SensorData createSensorData(SensorData sensorData, int sensorId) {
        sensorData.setSensorId(sensorId);
        for (SensorData savedSensorData : sensorDataRepository.findSensorDataBySensorId(sensorId)) {
            if (savedSensorData.getTimestamp().equals(sensorData.getTimestamp())) {
                throw new IllegalStateException("There already is Data for this time!");
            }
        }
        sensorDataRepository.save(sensorData);
        return sensorData;
    }

    public void deleteAllSensorData() {
        sensorDataRepository.deleteAll();
    }

    public List<SensorData> getAllSensorDataForDate(String date) {
        List<SensorData> responseSensorData = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (SensorData sensorData : sensorDataRepository.findAll()) {
            long timestamp = sensorData.getTimestamp().getTime();
            calendar.setTimeInMillis(timestamp);
            String receivedDate = String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            if (receivedDate.equals(date))
                responseSensorData.add(sensorData);
        }
        return responseSensorData;
    }

    public void deleteAllSensorDataForDate(String date) {
        for (SensorData sensorData : getAllSensorDataForDate(date)) {
            sensorDataRepository.delete(sensorData);
        }
    }

    public List<SensorData> getSensorData(int sensorId) {
        return sensorDataRepository.findSensorDataBySensorId(sensorId);
    }

    public List<SensorData> getSensorDataForDate(String date, int sensorId) {
        List<SensorData> responseSensorData = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (SensorData sensorData : sensorDataRepository.findSensorDataBySensorId(sensorId)) {
            long timestamp = sensorData.getTimestamp().getTime();
            calendar.setTimeInMillis(timestamp);
            String receivedDate = String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            if (receivedDate.equals(date))
                responseSensorData.add(sensorData);
        }
        return responseSensorData;
    }

    @Transactional
    public void deleteSensorData(int sensorId) {
        sensorDataRepository.deleteSensorDataBySensorId(sensorId);
    }

    public void deleteSensorDataForDate(String date, int sensorId) {
        for (SensorData sensorData : getSensorDataForDate(date, sensorId)) {
            sensorDataRepository.delete(sensorData);
        }
    }

    public SensorData updateDataById(int id, double data) {
        boolean exists = sensorDataRepository.existsById(id);
        if (!exists)
            throw new IllegalStateException("There is no data with id " + id);
        SensorData sensorData = sensorDataRepository.findSensorDataById(id);
        sensorData.setData(data);
        sensorDataRepository.save(sensorData);
        return sensorData;
    }
}

