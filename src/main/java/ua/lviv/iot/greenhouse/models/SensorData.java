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
    private SensorType sensorType;
    private LocalDateTime localDateTime;
    private double data;

    public SensorData(SensorType sensorType, LocalDateTime localDateTime, double data) {
        this.sensorType = sensorType;
        this.localDateTime = localDateTime;
        this.data = data;
    }
}
