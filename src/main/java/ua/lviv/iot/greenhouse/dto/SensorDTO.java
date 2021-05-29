package ua.lviv.iot.greenhouse.dto;

import lombok.*;
import ua.lviv.iot.greenhouse.models.type.SensorType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SensorDTO {

    private SensorType sensorType;
    private LocalDateTime localDateTime;
    @NonNull private double currentData;
}
