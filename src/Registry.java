import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Registry {
    CheckInput ch = new CheckInput();

    public Customer register(Scanner scanner, Map<UUID, Customer> customers, Customer currentCustomer) {
        if (currentCustomer != null) {
            System.out.println("Вы уже авторизованы. Вы хотите создать нового юзера, введите Да/Нет?");
            if (ch.yesNo(scanner).equalsIgnoreCase("нет")) {
                return null;
            }
        }
        UUID uuid = UUID.randomUUID();

        if (customers.containsKey(uuid)) {
            System.out.println("\nПользователь с таким UUID уже существует.");
            return null;
        }

        customers.put(uuid, new Customer(uuid));
        System.out.println("\nРегистрация прошла успешно!");
        System.out.println("Ваш UUID " + uuid + ". Запомните его, пожалуйста!");
        return customers.get(uuid);
    }
}

