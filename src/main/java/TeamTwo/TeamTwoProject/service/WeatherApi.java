package TeamTwo.TeamTwoProject.service;

import org.springframework.beans.factory.annotation.Value;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component

public class WeatherApi {

    @Value("${GOOGLE_GEOLOCATION_API_KEY}")
    private static String GOOGLE_GEOLOCATION_API_KEY;
    @Value("${OPENWEATHERMAP_API_KEY}")
    private static String OPENWEATHERMAP_API_KEY;
    private static String GOOGLE_GEOLOCATION_API_URL = "https://www.googleapis.com/geolocation/v1/geolocate?key=%s";
    private static String OPENWEATHERMAP_API_URL = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";

    public WeatherCustomData getCurrentWeather() {
        try {
            String[] location = getCurrentLocation();
            String latitude = location[0];
            String longitude = location[1];

            return getCurrentWeather(latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String[] getCurrentLocation() throws IOException {
        String apiUrl = String.format(GOOGLE_GEOLOCATION_API_URL, GOOGLE_GEOLOCATION_API_KEY);
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
        String latitude = location.get("lat").getAsString();
        String longitude = location.get("lng").getAsString();

        return new String[]{latitude, longitude};
    }

    public static WeatherCustomData getCurrentWeather(String latitude, String longitude) throws IOException {
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
        return gson.fromJson(response.toString(), WeatherCustomData.class);
    }

    private static void displayWeatherInfo(WeatherCustomData weatherData) {
        System.out.println("City: " + weatherData.getName());
        System.out.println("Temperature: " + weatherData.getTemperature() + "°C");
        System.out.println("Weather: " + weatherData.getWeatherDescription());
    }
}

