package ua.lviv.iot.GreenHouse.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // 102 - temperature; 103 - humidity; 104 - luminosity
    private int sensorId;
    private Timestamp timestamp;
    private double data;

    public SensorData(int sensorId, Timestamp timestamp, double data) {
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.data = data;
    }
}
