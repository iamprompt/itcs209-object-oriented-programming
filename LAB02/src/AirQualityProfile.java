public class AirQualityProfile {
    private String location;
    private String datetime;
    private int aqi, pm25;
    private double temp;

    /* CONSTRUCTOR */
    public AirQualityProfile(String dt, String loc, int aqi, int pm25, double temp) {
        setDateTime(dt);
        setLocation(loc);
        setAQI(aqi);
        setPM25(pm25);
        setTemperature(temp);
    }

    /* GET DATA */
    public String getDateTime() {
        return datetime;
    }

    public String getLocation() {
        return location;
    }

    public int getAQI() {
        return aqi;
    }

    public int getPM25() {
        return pm25;
    }

    public double getTemperature() {
        return temp;
    }

    /* WRITE DATA */
    public void setDateTime(String dt) {
        datetime = dt;
    }

    public void setLocation(String loc) {
        location = loc;
    }

    public void setAQI(int val) {
        aqi = val;
    }

    public void setPM25(int val) {
        pm25 = val;
    }

    public void setTemperature(double val) {
        temp = val;
    }

    /* PRINT DATA */
    public void printAirQualityInfo() {
        System.out.println(getLocation() + " at " + getDateTime());
        System.out.println("AQI: " + getAQI() + ", PM2.5: " + getPM25() + " microgram/m3");
        System.out.println("Temperature: " + getTemperature() + " Celsius");
    }
}
