import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Authentication {
    CheckInput ch = new CheckInput();

    public Wallet authenticate(Scanner scanner, Map<String, Wallet> wallets) {
        System.out.print("Введите логин: ");
        String username = ch.readString(scanner);
        System.out.print("Введите пароль: ");
        String password = ch.readString(scanner);

        if (authenticateUser(username, password, wallets)) {
            System.out.println("\nАвторизация прошла успешно!");
            return wallets.get(username);
        } else {
            System.out.println("\nНеверный логин или пароль. Попробуйте снова.");
            return  null;
        }
    }

    public boolean authenticateUser(String username, String password, Map<String, Wallet> wallets ) {
        return wallets.containsKey(username) && wallets.get(username).getUser().getPassword().equals(password);
    }
}
