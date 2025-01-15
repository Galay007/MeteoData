import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class Storage {
    private static final String USERS_FILE = "wallets.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void loadWallets(Map<String, Wallet> wallets) {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Type type = new TypeToken<Map<String, Wallet>>() {
            }.getType();
            Map<String, Wallet> loadedUsers = gson.fromJson(reader, type);
            if (loadedUsers != null) {
                wallets.putAll(loadedUsers);
            }
        } catch (FileNotFoundException ignored) {
        } catch (Exception e) {
            System.err.println("\nОшибка при загрузке пользователей из файла: " + e.getMessage());
        }
    }

    public  void saveWallets(Map<String, Wallet> wallets) {
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            gson.toJson(wallets, writer);
        } catch (IOException e) {
            System.err.println("\nОшибка при сохранении пользователей в файл: " + e.getMessage());
        }
    }
}
