import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Authentication {
    CheckInput ch = new CheckInput();

    public Customer authenticate(Scanner scanner, Map<UUID, Customer> customers) {
        System.out.print("Введите Ваш UUID или Назад: ");
        UUID uuid = ch.readUUID(scanner);

        if (uuid == null) return null;

        if (authenticateUser(customers, uuid)) {
            System.out.println("\nАвторизация прошла успешно!");
            return customers.get(uuid);
        } else {
            System.out.println("\nВведен неверный UUID. Попробуйте снова.");
            return  null;
        }
    }

    public boolean authenticateUser(Map<UUID, Customer> customers, UUID uuid) {
        return customers.containsKey(uuid);
    }
}

