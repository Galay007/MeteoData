import com.google.gson.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.util.Scanner;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Main {
    static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    static JsonElement jsonElement;
    static String lat;
    static String lon;
    static String limit;

    public static void main(String[] args) {

        runScanner();
        jsonElement = yandexApi(lat, lon, limit);
        if (jsonElement == null) return;

        System.out.println("Json ответ от API: " + GSON.toJson(jsonElement));
        System.out.println("Текущая температура в градусах цельсия: " + getCurrentTemp());
        System.out.println("Средняя температура в градусах цельсия: " + getAverageTemp());

    }

    static void runScanner() {
        boolean check = false;

        while (!check) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите широту: ");
            lat = scanner.next();

            System.out.print("Введите долготу: ");
            lon = scanner.next();

            System.out.print("Введите кол-во дней от 1 до 6: ");
            limit = scanner.next();

            try {
                if ((parseFloat(lat) > 0 && parseFloat(lat) <= 90)
                        && (parseFloat(lon) > 0 && parseFloat(lon) <= 180)
                        && (parseInt(limit) >= 1 && parseInt(limit) <= 6)) {
                    check = true;
                }
            } catch (Exception e) {
            }
        }
    }

    static JsonElement yandexApi(String lat, String lon, String limit) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weather.yandex.ru/v2/forecast?lat="
                        + lat + "&lon=" + lon + "&limit=" + limit + "&hours=false&extra=false"))
                .GET()
                .header("X-Yandex-Weather-Key", "9d44432d-1d5e-476d-a9e0-153957166f42")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return new JsonParser().parse(response.body());
        } catch (Exception e) {
            System.err.println("Error making HTTP request " + e.getMessage());
        }
        return null;
    }

    static String getCurrentTemp() {
        JsonObject data = (JsonObject) jsonElement;
        JsonObject fact = (JsonObject) data.get("fact");

        return fact.get("temp").toString();
    }

    static Double getAverageTemp() {
        Float avgTemp = 0f;

        for (int i = 0; i <= parseInt(limit) - 1; i++) {
            JsonObject data = (JsonObject) jsonElement;
            JsonArray days = (JsonArray) data.get("forecasts");
            JsonObject dayX = (JsonObject) days.get(i);
            JsonObject parts = (JsonObject) dayX.get("parts");
            JsonObject day = (JsonObject) parts.get("day");
            String tempAvg = day.get("temp_avg").toString();

            avgTemp += parseFloat(tempAvg);
        }
        avgTemp /= parseInt(limit);

        return Math.ceil(avgTemp * Math.pow(10, 1)) / Math.pow(10, 1);
    }
}
