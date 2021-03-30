package ua.lviv.iot.GreenHouse.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TemperatureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp timeStamp;
    private double temperature;

    public TemperatureData() {
    }

    public TemperatureData(int id, Timestamp timeStamp, double temperature) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "TemperatureData{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", temperature=" + temperature +
                '}';
    }
}
