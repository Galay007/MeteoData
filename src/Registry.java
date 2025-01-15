import java.util.Map;
import java.util.Scanner;

public class Registry {
    CheckInput ch = new CheckInput();

    public Wallet register(Scanner scanner, Map<String, Wallet> wallets) {
        System.out.print("Введите логин: ");
        String username = ch.readString(scanner);
        System.out.print("Введите пароль: ");
        String password = ch.readString(scanner);
        System.out.print("Введите начальную сумму кошелька: ");
        double beginAmount = ch.readDouble(scanner);


        if (wallets.containsKey(username)) {
            System.out.println("\nПользователь с таким логином уже существует.");
            return null;
        }

        wallets.put(username, new Wallet(username, password, beginAmount));
        System.out.println("\nРегистрация прошла успешно!");
        return wallets.get(username);
    }
}
