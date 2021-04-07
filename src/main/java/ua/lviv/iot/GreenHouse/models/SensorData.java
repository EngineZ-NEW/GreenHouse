package ua.lviv.iot.GreenHouse.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // 102 - temperature; 103 - humidity; 104 - luminosity
    private int sensorId;
    private Timestamp timestamp;
    private double data;

    public SensorData() {
    }

    public SensorData(int id, int sensorId, Timestamp timestamp, double data) {
        this.id = id;
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", timeStamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}
