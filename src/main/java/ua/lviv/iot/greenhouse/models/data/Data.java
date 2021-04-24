package ua.lviv.iot.greenhouse.models.data;

import lombok.*;
import ua.lviv.iot.greenhouse.models.SensorType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Data {

    @Enumerated(EnumType.STRING)
    private SensorType sensorType;
    private LocalDateTime localDateTime;
    private double currentData;
}
