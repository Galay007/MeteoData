import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Main {
    private static final String CONFIG_PATH = "config.json";
    private static Map<UUID, Customer> customers = new HashMap<>();
    private static Customer currentCustomer;
    private static CheckInput ch = new CheckInput();
    private static Storage repo = new Storage();
    private static Authentication auth = new Authentication();
    private static Registry registry = new Registry();
    private static ShortLinkGenerator generator = new ShortLinkGenerator();
    private static ConfigReader config = new ConfigReader();

    public static void main(String[] args) {
        repo.loadWallets(customers);
        config.readConfigs(CONFIG_PATH);
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("\nДОБРО ПОЖАЛОВАТЬ В СЕРВИС ГЕНЕРАЦИИ КОРОТКИХ ССЫЛОК!");

        do {
            System.out.println("\nВыберите действие: \t\t\t\t\t" + identifyCustomer());
            System.out.println("1. Авторизация");
            System.out.println("2. Регистрация");
            System.out.println("3. Сгенерировать короткую ссылку");
            System.out.println("4. Переход по короткой ссылке");
            System.out.println("5. Показать все сохраненные ссылки");
            System.out.println("6. Редактировать лимит переходов");
            System.out.println("7. Удалить короткую ссылку");
            System.out.println("8. Выход");

            System.out.print("Ваш выбор: ");
            choice = ch.readString(scanner);

            switch (choice) {
                case "1":
                    currentCustomer = auth.authenticate(scanner, customers);
                    break;
                case "2":
                    currentCustomer = registry.register(scanner, customers, currentCustomer);
                    break;
                case "3":
                    if (currentCustomer == null) {
                        currentCustomer = registry.register(scanner, customers, currentCustomer);
                    }
                    generator.generateShortLink(currentCustomer, customers, scanner, config);
                    break;
                case "4":
                    useShortLink(scanner);
                    break;
                case "5":
                    showAllLinks(scanner);
                    break;
                case "6":
                    editClickLimits(scanner);
                    break;
                case "7":
                    deleteShortLink(scanner);
                    break;
                case "8":
                    System.out.println("\nДо новых встреч...");
                    break;
                default:
                    System.out.println("\nНеверный выбор. Попробуйте снова.");
            }
        }
        while (!choice.equals("8"));

        repo.saveWallets(customers);
        scanner.close();
    }

    private static void deleteShortLink(Scanner scanner) {
        if (currentCustomer != null) {
            if (!currentCustomer.getShortLinks().isEmpty()) {
                System.out.print("\nВведите короткую ссылку для перехода: ");
                String input = ch.readString(scanner);
                if (checkExist(input)) {

                    currentCustomer.getShortLinks().remove(input);
                    System.out.println("\nСсылка успешно удалена");
                } else {
                    System.out.println("\nТакая ссылка не найдена");
                }
            } else {
                System.out.println("\nНет сохраненных ссылок");
            }
        } else {
            System.out.println("\nВы не авторизованы");
        }
    }

    private static void editClickLimits(Scanner scanner) {
        if (currentCustomer != null) {
            if (!currentCustomer.getShortLinks().isEmpty()) {
                System.out.print("\nВведите короткую ссылку для перехода: ");
                String input = ch.readString(scanner);
                if (checkExist(input)) {
                    if (currentCustomer.getShortLinks().get(input).timeIsNotExpired()) {
                        System.out.println("\nВведите новый лимит кликов ");
                        int newClicks = ch.readInt(scanner);
                        currentCustomer.getShortLinks().get(input).setClickLimit(newClicks);
                        System.out.println("\nДанные успешно обновлены");
                    } else {
                        currentCustomer.getShortLinks().remove(input);
                        System.out.println("\nСсылка удалена из-за просроченного срока");
                    }
                } else {
                    System.out.println("\nТакая ссылка не найдена");
                }
            } else {
                System.out.println("\nНет сохраненных ссылок");
            }
        } else {
            System.out.println("\nВы не авторизованы");
        }
    }

    private static void showAllLinks(Scanner scanner) {
        if (currentCustomer != null) {
            if (!currentCustomer.getShortLinks().isEmpty()) {
                System.out.println();
                currentCustomer.getShortLinks().forEach((key, value) ->
                        System.out.println(value.toString()));
            } else {
                System.out.println("\nСохраненных ссылок не найдено");
            }
        } else {
            System.out.println("\nВы не авторизованы");
        }
    }

    private static void useShortLink(Scanner scanner) {
        if (currentCustomer != null) {
            if (!currentCustomer.getShortLinks().isEmpty()) {
                System.out.print("\nВведите короткую ссылку для перехода: ");
                String input = ch.readString(scanner);
                if (checkExist(input)) {
                    if (currentCustomer.getShortLinks().get(input).validation()) {
                        openLink(currentCustomer.getShortLinks().get(input).useUrl());
                    } else if (!currentCustomer.getShortLinks().get(input).timeIsNotExpired()) {
                        currentCustomer.getShortLinks().remove(input);
                        System.out.println("\nСсылка удалена из-за просроченного срока.");
                    } else {
                        System.out.println("\nСсылка заблокирована из-за достигнутого лимита кликов.");
                    }
                } else {
                    System.out.println("\nТакая ссылка не найдена");
                }
            } else {
                System.out.println("\nНет сохраненных ссылок");
            }
        } else {
            System.out.println("\nВы не авторизованы");
        }
    }

    private static boolean checkExist(String input) {
        return currentCustomer.getShortLinks().containsKey(input);
    }

    private static String identifyCustomer() {
        if (currentCustomer == null) {
            return "Пользователь: не выбран ";
        } else {
            return "Пользователь: " + currentCustomer.getUser().getUuid() + " ";
        }
    }

    public static void openLink(String link) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                URI uri = new URI(link);
                URL url = uri.toURL();
                desktop.browse(url.toURI());
            } catch (IOException | URISyntaxException e) {
                System.err.println("Не удалось открыть ссылку: " + link + ". Ошибка: " + e.getMessage());
            }
        } else {
            System.err.println("Поддержка Desktop API отсутствует.");
        }
    }
}