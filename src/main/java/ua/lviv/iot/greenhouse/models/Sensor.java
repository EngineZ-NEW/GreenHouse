package ua.lviv.iot.greenhouse.models;

import lombok.*;
import ua.lviv.iot.greenhouse.models.type.SensorType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Data data;

    public Sensor(Data data) {
        this.data = data;
    }

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Data {
        @Enumerated(EnumType.STRING)
        private SensorType sensorType;
        private LocalDateTime localDateTime;
        private double currentData;
    }
}
