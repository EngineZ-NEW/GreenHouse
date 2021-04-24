package ua.lviv.iot.greenhouse.models;

public enum SensorType {
    TEMPERATURE(102L),
    HUMIDITY(103L),
    LUMINOSITY(104L);

    private final Long sensorId;

    SensorType(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Long getSensorId() {
        return sensorId;
    }
}
