package ua.lviv.iot.greenhouse.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 102 - temperature; 103 - humidity; 104 - luminosity
    private int sensorId;
    private LocalDateTime localDateTime;
    private double data;

    public SensorData(int sensorId, LocalDateTime localDateTime, double data) {
        this.sensorId = sensorId;
        this.localDateTime = localDateTime;
        this.data = data;
    }
}
