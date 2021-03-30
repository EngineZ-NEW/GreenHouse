package ua.lviv.iot.GreenHouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.GreenHouse.dataaccess.TemperatureDataRepository;
import ua.lviv.iot.GreenHouse.models.TemperatureData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TemperatureDataService {

    private final TemperatureDataRepository temperatureDataRepository;

    @Autowired
    public TemperatureDataService(TemperatureDataRepository temperatureDataRepository) {
        this.temperatureDataRepository = temperatureDataRepository;
    }

    public List<TemperatureData> getTemperatureData() {
        return temperatureDataRepository.findAll();
    }

    public TemperatureData createTemperatureData(TemperatureData temperatureData) {
        temperatureDataRepository.save(temperatureData);
        return temperatureData;
    }

    public void deleteTemperatureData() {
        temperatureDataRepository.deleteAll();
    }

    public List<TemperatureData> getTemperatureDataForDate(String date) {
        List<TemperatureData> responseTemperatureData = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (TemperatureData temperatureData : temperatureDataRepository.findAll()) {
            long timestamp = temperatureData.getTimestamp().getTime();
            calendar.setTimeInMillis(timestamp);
            String receivedDate = String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            System.out.println("Date: " + date);
            System.out.println("ReceivedDate: " + receivedDate);
            if (receivedDate.equals(date))
                responseTemperatureData.add(temperatureData);
        }
        return responseTemperatureData;
    }
}

