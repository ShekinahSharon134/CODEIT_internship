import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * WeatherApiClient
 *
 * A simple REST API client that fetches weather data for a city
 * and displays it in a structured format using Java.
 *
 * Task 2: CodTech Java Internship
 */
public class WeatherApiClient {

    // Replace with your actual API key from https://www.weatherapi.com
    private static final String API_KEY = "d593d10c9dff418bb9e140525251907";
    private static final String CITY = "Chennai";

    public static void main(String[] args) {
        try {
            String urlString = String.format(
                "http://api.weatherapi.com/v1/current.json?key=%s&q=%s",
                API_KEY, CITY
            );

            // Setup HTTP connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject json = new JSONObject(response.toString());

            String cityName = json.getJSONObject("location").getString("name");
            String region = json.getJSONObject("location").getString("region");
            String country = json.getJSONObject("location").getString("country");
            double tempC = json.getJSONObject("current").getDouble("temp_c");
            String condition = json.getJSONObject("current")
                                   .getJSONObject("condition")
                                   .getString("text");

            // Display result
            System.out.println("Location: " + cityName + ", " + region + ", " + country);
            System.out.println("Temperature: " + tempC + "°C");
            System.out.println("Condition: " + condition);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
