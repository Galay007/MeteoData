import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class ConfigReader {
    private int clickLimitConfig;
    private int timeLimitConfig;

    public void readConfigs(String configFilePath) {
        Gson gson = new GsonBuilder().create();
        try (Reader reader = new FileReader(configFilePath)) {
            Config config = gson.fromJson(reader, Config.class);
            this.clickLimitConfig = config.clickLimitConfig;
            this.timeLimitConfig = config.timeLimitConfig;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла конфигурации: " + e.getMessage());
            this.clickLimitConfig = 0;
            this.timeLimitConfig = 0;
        } catch (JsonSyntaxException | JsonIOException e) {
            System.err.println("Ошибка при парсинге JSON: " + e.getMessage());
            this.clickLimitConfig = 0;
            this.timeLimitConfig = 0;
        }
    }

    public int getClickLimitConfig() {
        return clickLimitConfig;
    }

    public int getTimeLimitConfig() {
        return timeLimitConfig;
    }
}