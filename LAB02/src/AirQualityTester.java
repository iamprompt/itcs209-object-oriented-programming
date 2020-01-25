public class AirQualityTester {

    public static void main(String[] args) {
            AirQualityProfile data1 = new AirQualityProfile("2020-01-24 13:00", "Salaya", 85, 28, 32);
            data1.printAirQualityInfo();
            System.out.println();

            AirQualityProfile data2 = new AirQualityProfile("2020-01-24 13:00", "Pathum Wan", 85, 89, 32);
            data2.printAirQualityInfo();
    }

}