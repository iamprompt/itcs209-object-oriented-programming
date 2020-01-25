import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AirQualityProfileC {
    private String location;
    private LocalDateTime datetime;
    private int aqi, pm25;
    private double temp;
    public static int profile = 0;

    /* CONSTRUCTOR */
    public AirQualityProfileC(LocalDateTime dt) {
        profile = profile + 1;
        setDateTime(dt);
    }

    /* GET DATA */
    public String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = datetime.format(formatter);
        return formatDateTime;
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

    public boolean isUnhealthy() {
        if (aqi > 100)
            return true;
        else
            return false;
    }

    public String getAQIStatus() {
        if (aqi >= 0 && aqi <= 50)
            return "Good";
        else if (aqi >= 51 && aqi <= 100)
            return "Moderate";
        else if (aqi >= 101 && aqi <= 150)
            return "Unhealthy for Sensitive Groups";
        else if (aqi >= 151 && aqi <= 200)
            return "Unhealthy";
        else if (aqi >= 201 && aqi <= 300)
            return "Very Unhealthy";
        else if (aqi >= 301)
            return "Hazardous";
        return null;

    }

    public int getProfileNo() {
        return profile;
    }

    /* WRITE DATA */
    public void setDateTime(LocalDateTime dt) {
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
        System.out.println("Data " + getProfileNo());
        System.out.println(getLocation() + " at " + getDateTime());
        System.out.println("AQI: " + getAQI() + ", PM2.5: " + getPM25() + " microgram/m3");
        System.out.println("Temperature: " + getTemperature() + " Celsius");
        System.out.println("Unhealthy: " + isUnhealthy());
        System.out.println("Status: " + getAQIStatus());
    }
}