package TeamTwo.TeamTwoProject.service;

public class WeatherData {
    public String name;
    public double temperature;
    public String weatherDescription;

    public String getName() {
        return name;
    }

    public double getTemperature() {
        return temperature - 273.15; // Kelvin to Celsius 변환
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }
}
