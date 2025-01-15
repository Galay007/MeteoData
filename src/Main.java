import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Main {

    private static final Map<String, Wallet> wallets = new HashMap<>();
    private static Wallet currentWallet;
    private static CheckInput ch = new CheckInput();
    private static Storage repo = new Storage();
    private static Services services = new Services();
    private static Authentication auth = new Authentication();
    private static Registry registry = new Registry();

    public static void main(String[] args) {
        repo.loadWallets(wallets);
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\nВыберите действие: \t\t\t\t\t" + identifyUsername()
                    + "(" + services.calculateBalance(currentWallet) + " у.е.)");
            System.out.println("1. Авторизация");
            System.out.println("2. Регистрация");
            System.out.println("3. Доходы");
            System.out.println("4. Расходы");
            System.out.println("5. Отчеты");
            System.out.println("6. Выход");

            System.out.print("Ваш выбор: ");
            choice = scanner.next();

            switch (choice) {
                case "1":
                    currentWallet = auth.authenticate(scanner, wallets);
                    break;
                case "2":
                    currentWallet = registry.register(scanner, wallets);
                    break;
                case "3":
                    if (currentWallet != null) {
                        String incomeCat = services.handleCategories(scanner, Operation.INCOME,
                                currentWallet.getCategories(),
                                currentWallet.getIncomeTransactions(), currentWallet, identifyUsername());
                        if (!incomeCat.isEmpty()) {
                            services.handleTransaction(scanner, Operation.INCOME, incomeCat,
                                    currentWallet.getIncomeTransactions(), currentWallet, identifyUsername());
                        }
                    } else {
                        System.out.println("\nВы не авторизованы");
                    }
                    break;
                case "4":
                    if (currentWallet != null) {
                        String expenseCat = services.handleCategories(scanner, Operation.EXPENSE,
                                currentWallet.getCategories(), currentWallet.getExpenseTransactions(),
                                currentWallet, identifyUsername());
                        if (!expenseCat.isEmpty()) {
                            services.handleTransaction(scanner, Operation.EXPENSE, expenseCat,
                                    currentWallet.getExpenseTransactions(), currentWallet, identifyUsername());
                        }
                    } else {
                        System.out.println("\nВы не авторизованы");
                    }
                    break;
                case "5":
                    if (currentWallet != null) {
                        services.handleReports(scanner, currentWallet, identifyUsername());

                    } else {
                        System.out.println("\nВы не авторизованы");
                    }
                    break;
                case "6":
                    System.out.println("\nДо новых встреч...");
                    break;
                default:
                    System.out.println("\nНеверный выбор. Попробуйте снова.");
            }
        } while (!choice.equals("6"));

        repo.saveWallets(wallets);
        scanner.close();
    }

    private static String modifyFormat(double amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(amount);
    }

    private static String identifyUsername() {
        if (currentWallet == null) {
            return "Пользователь: не выбран ";
        } else {
            return "Пользователь: " + currentWallet.getUser().getUsername() + " ";
        }
    }
}
