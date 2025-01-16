import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Storage {
    private static final String USERS_FILE = "customers.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public void loadWallets(Map<UUID, Customer> customers) {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Type type = new TypeToken<Map<UUID, Customer>>() {
            }.getType();
            Map<UUID, Customer> loadedUsers = gson.fromJson(reader, type);
            if (loadedUsers != null) {
                customers.putAll(loadedUsers);
            }
        } catch (FileNotFoundException ignored) {
        } catch (Exception e) {
            System.err.println("\nОшибка при загрузке пользователей из файла: " + e.getMessage());
        }
    }

    public  void saveWallets(Map<UUID, Customer> customers) {
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            gson.toJson(customers, writer);
        } catch (IOException e) {
            System.err.println("\nОшибка при сохранении пользователей в файл: " + e.getMessage());
        }
    }
}