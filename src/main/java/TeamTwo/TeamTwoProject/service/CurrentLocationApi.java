package TeamTwo.TeamTwoProject.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class CurrentLocationApi {
    @Value("${GOOGLE_GEOLOCATION_API_KEY}")
    private static String GOOGLE_GEOLOCATION_API_KEY;
    private static String GOOGLE_GEOLOCATION_API_URL = "https://www.googleapis.com/geolocation/v1/geolocate?key=%s";

    public static double[] getCurrentLocation() throws IOException {
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
        double latitude = location.get("lat").getAsDouble();
        double longitude = location.get("lng").getAsDouble();

        return new double[]{latitude, longitude};
    }
}
