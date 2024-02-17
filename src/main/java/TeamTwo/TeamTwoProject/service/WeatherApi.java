package TeamTwo.TeamTwoProject.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApi {

    private static final String GEOLOCATION_API_KEY = "YOUR_GOOGLE_GEOLOCATION_API_KEY";
    private static final String OPENWEATHERMAP_API_KEY = "YOUR_OPENWEATHERMAP_API_KEY";
    private static final String GEOLOCATION_API_URL = "https://www.googleapis.com/geolocation/v1/geolocate?key=%s";
    private static final String OPENWEATHERMAP_API_URL = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";

    public static void main(String[] args) {
        try {
            // 현재 위치의 위도와 경도를 가져옴
            double[] location = getCurrentLocation();
            double latitude = location[0];
            double longitude = location[1];

            // 현재 위치의 날씨 정보를 가져옴
            WeatherData weatherData = getCurrentWeather(latitude, longitude);
            displayWeatherInfo(weatherData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[] getCurrentLocation() throws IOException {
        String apiUrl = String.format(GEOLOCATION_API_URL, GEOLOCATION_API_KEY);
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        String postData = "{}";
        connection.setDoOutput(true);
        connection.getOutputStream().write(postData.getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
        JsonObject location = jsonObject.getAsJsonObject("location");
        double latitude = location.get("lat").getAsDouble();
        double longitude = location.get("lng").getAsDouble();

        return new double[]{latitude, longitude};
    }

    private static WeatherData getCurrentWeather(double latitude, double longitude) throws IOException {
        String apiUrl = String.format(OPENWEATHERMAP_API_URL, latitude, longitude, OPENWEATHERMAP_API_KEY);
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        Gson gson = new Gson();
        return gson.fromJson(response.toString(), WeatherData.class);
    }

    private static void displayWeatherInfo(WeatherData weatherData) {
        System.out.println("City: " + weatherData.getName());
        System.out.println("Temperature: " + weatherData.getTemperature() + "°C");
        System.out.println("Weather: " + weatherData.getWeatherDescription());
    }
}

class WeatherData {
    private String name;
    private double temperature;
    private String weatherDescription;

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