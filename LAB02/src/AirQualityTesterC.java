import java.time.LocalDateTime;

public class AirQualityTesterC {

    public static void main(String[] args) {
        AirQualityProfileC data1 = new AirQualityProfileC(LocalDateTime.of(2020, 1, 24, 13, 0));
        data1.setLocation("Salaya");
        data1.setAQI(85);
        data1.setPM25(28);
        data1.setTemperature(32);
        data1.printAirQualityInfo();
        System.out.println();

        AirQualityProfileC data2 = new AirQualityProfileC(LocalDateTime.of(2020, 1, 24, 13, 0));
        data2.setLocation("Pathum Wan");
        data2.setAQI(85);
        data2.setPM25(28);
        data2.setTemperature(32);
        data2.printAirQualityInfo();
    }
}