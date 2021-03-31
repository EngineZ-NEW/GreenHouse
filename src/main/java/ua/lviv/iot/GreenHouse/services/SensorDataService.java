package ua.lviv.iot.GreenHouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.GreenHouse.dao.SensorDataRepository;
import ua.lviv.iot.GreenHouse.models.SensorData;

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

    public List<SensorData> getSensorData() {
        return sensorDataRepository.findAll();
    }

    public SensorData createSensorData(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
        return sensorData;
    }

    public void deleteSensorData() {
        sensorDataRepository.deleteAll();
    }

    public List<SensorData> getSensorDataForDate(String date) {
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

    public void deleteSensorDataForDate(String date) {
        for (SensorData sensorData : getSensorDataForDate(date)) {
            sensorDataRepository.delete(sensorData);
        }
    }
}

