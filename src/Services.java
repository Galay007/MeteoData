import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Services {
    CheckInput ch = new CheckInput();

    public void handleTransaction(Scanner scanner, Operation operation,
                                          String chosenCategory,
                                  List<Transaction> transactions, Wallet wallet, String userName) {
        String name;
        String name2;
        if (operation.equals(Operation.INCOME)) {
            name = "доходов";
            name2 = "Доходы:";
        } else {
            name = "расходов";
            name2 = "Расходы:";
        }
        while (true) {
            System.out.println("\nУправление транзакциями " + name + ": \t\t\t\t\t" + userName
                    + "(" + calculateBalance(wallet) + " у.е.)");
            System.out.println("1. Добавить транзакцию у категории " + chosenCategory);
            System.out.println("2. Добавить транзакцию с комментарием у категории " + chosenCategory);
            System.out.println("3. Показать все транзакции");
            System.out.println("0 Назад");
            System.out.print("Ваш выбор: ");
            String choice = ch.readString(scanner);
            double amount = 0;
            double result = 0;

            switch (choice) {
                case "1":
                    System.out.print("\nВведите сумму " + name + ": ");
                    amount = ch.readDouble(scanner);
                    transactions.add(new Transaction(operation, chosenCategory, amount));
                    System.out.println("\nТранзакция записана успешно");
                    result = checkOverBudget(operation, chosenCategory, transactions, wallet);
                    if (result < 0) {
                        System.out.println("Расход по категории " + chosenCategory
                                + " превышен на " + modifyFormat(result * -1) + "!");
                    }
                    break;
                case "2":
                    System.out.print("\nВведите сумму " + name + ": ");
                    amount = ch.readDouble(scanner);
                    scanner.nextLine();
                    System.out.print("Добавьте комментарий: ");
                    String comment = ch.readStringLIne(scanner);
                    transactions.add(new Transaction(operation, chosenCategory, amount, comment));
                    System.out.println("\nТранзакция записана успешно");
                    result = checkOverBudget(operation, chosenCategory, transactions, wallet);
                    if (result < 0) {
                        System.out.println("Расход по категории " + chosenCategory
                                + " превышен на " + modifyFormat(result * -1) + "!");
                    }
                    break;
                case "3":
                    printTransactions(name, name2, transactions);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nНеверный выбор. Попробуйте снова.");
            }
        }
    }


    public String handleCategories(Scanner scanner, Operation flag, Category categories,
                                   List<Transaction> transactions, Wallet wallet, String userName) {
        String name;
        String name2;
        if (flag.equals(Operation.INCOME)) {
            name = "доходов";
            name2 = "Доходы:";
        } else {
            name = "расходов";
            name2 = "Расходы:";
        }

        while (true) {
            System.out.println("\nУправление категориями " + name + ":\t\t\t\t\t" + userName
                    + "(" + calculateBalance(wallet) + " у.е.)");
            System.out.println("1. Добавить категорию");
            System.out.println("2. Посмотреть все категории");
            System.out.println("3. Выбрать категорию");
            System.out.println("4. Показать все транзакции");
            if (flag.equals(Operation.EXPENSE)) {
                System.out.println("5. Изменить бюджет");
            }
            System.out.println("0 Назад");
            System.out.print("Ваш выбор: ");
            String choice = ch.readString(scanner);
            String currentCategory = null;

            switch (choice) {
                case "1":
                    System.out.print("\nВведите название категории " + name + ": ");
                    currentCategory = ch.readString(scanner);

                    if (flag.equals(Operation.EXPENSE)) {
                        System.out.print("Введите сумму бюджета для этой категории: ");
                        double budget = ch.readDouble(scanner);
                        categories.getExpenseCat().put(currentCategory, budget);
                    } else {
                        categories.getIncomeCat().add(currentCategory);
                    }
                    break;
                case "2":
                    if (checkIsEmpty(flag, categories)) {
                        System.out.println("\nКатегории " + name + " не созданы");
                        continue;
                    }
                    printCategoriesWithBud(flag, categories);
                    break;
                case "3":
                    if (checkIsEmpty(flag, categories)) {
                        System.out.println("\nКатегории " + name + " не созданы");
                        continue;
                    }
                    System.out.println("\nВыберете категорию " + name + " из:");
                    printCategories(flag, categories);
                    currentCategory = checkIsEqual(flag, ch.readString(scanner), categories);

                    if (currentCategory != null) {
                        return currentCategory;
                    } else {
                        System.out.println("\nТакой категории нет");
                        continue;
                    }
                case "4":
                    printTransactions(name, name2, transactions);
                    break;
                case "5":
                    if (flag.equals(Operation.INCOME)) {
                        continue;
                    }
                    if (checkIsEmpty(flag, categories)) {
                        System.out.println("\nКатегории " + name + " не созданы");
                        continue;
                    }
                    System.out.println("\nУкажите категорию ");
                    printCategoriesWithBud(flag, categories);
                    currentCategory = checkIsEqual(flag, ch.readString(scanner), categories);

                    if (currentCategory != null) {
                        System.out.println("\nВведите новую сумму бюджета для категории " + currentCategory);
                        double newBudget = ch.readDouble(scanner);
                        categories.getExpenseCat().put(currentCategory, newBudget);
                        System.out.println("\nБюджет для категории " + currentCategory + " успешно изменен на " + newBudget);
                    } else {
                        System.out.println("\nТакой категории нет");
                    }
                    continue;
                case "0":
                    return "";
                default:
                    System.out.println("\nНеверный выбор. Попробуйте снова.");
            }
        }
    }

    public void handleReports(Scanner scanner, Wallet wallet, String userName) {
        while (true) {
            System.out.println("\nУправление отчетами: \t\t\t\t\t" + userName
                    + "(" + calculateBalance(wallet) + " у.е.)");
            System.out.println("1. Показать все транзакции");
            System.out.println("2. Показать бюджеты по категориям");
            System.out.println("3. Показать итоговые результаты");
            System.out.println("0. Назад");
            System.out.print("Ваш выбор: ");
            String choice = ch.readString(scanner);

            switch (choice) {
                case "1":
                    System.out.println();
                    printTransactions("доходов", "Доходы", wallet.getIncomeTransactions());
                    printTransactions("расходов", "Расходы", wallet.getExpenseTransactions());
                    break;
                case "2":
                    if (!wallet.getCategories().getExpenseCat().isEmpty()) {
                        System.out.println("Бюджеты: ");
                        printCategoriesWithBud(Operation.EXPENSE, wallet.getCategories());
                    } else {
                        System.out.println("\nКатегории не созданы");
                        continue;
                    }
                    break;
                case "3":
                    System.out.println();
                    System.out.print("Сальдо на начало " + modifyFormat(wallet.getUser().getBeginAmount()));

                    if (!wallet.getIncomeTransactions().isEmpty()) {
                        System.out.print("\n\nОбщий доход: ");
                        calcResults(Operation.INCOME, wallet.getIncomeTransactions(), wallet.getCategories());
                    }
                    if (!wallet.getExpenseTransactions().isEmpty()) {
                        System.out.print("Общий расход: ");
                        calcResults(Operation.EXPENSE, wallet.getExpenseTransactions(), wallet.getCategories());
                    }
                    System.out.println("\nСальдо на конец " + calculateBalance(wallet));
                    if (!wallet.getExpenseTransactions().isEmpty()) {
                        System.out.println("\nБюджеты по категориям:");
                        calcBudgets(wallet.getExpenseTransactions(), wallet.getCategories().getExpenseCat(), wallet);
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nНеверный выбор. Попробуйте снова.");
            }

        }
    }

    private static void calcResults(Operation operation, List<Transaction> transactions, Category categories) {
        double result = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println(modifyFormat(result));

        if (operation.equals(Operation.INCOME)) {
            System.out.println("\tДоходы по категориям:");
            for (String incomeCat : categories.getIncomeCat()) {
                printTransByCat(incomeCat, transactions);
            }
        } else {
            System.out.println("\tРасходы по категориям:");
            for (Map.Entry<String, Double> entry : categories.getExpenseCat().entrySet()) {
                printTransByCat(entry.getKey(), transactions);
            }
        }
    }

    private static void printTransByCat(String category, List<Transaction> transactions) {
        double catResult = transactions.stream().filter(k -> k.getCategory()
                        .equals(category))
                .mapToDouble(Transaction::getAmount)
                .sum();
        if (catResult > 0) {
            System.out.println("\t\t- " + category + ": " + modifyFormat(catResult));
        }
    }

    private static void calcBudgets(List<Transaction> transactions, Map<String, Double> expenseCat, Wallet currentWallet) {
        for (Map.Entry<String, Double> entry : expenseCat.entrySet()) {
            double remainBud = checkOverBudget(Operation.EXPENSE, entry.getKey(), transactions, currentWallet);
            double budget = currentWallet.getCategories().getExpenseCat().get(entry.getKey());
            System.out.println("\t" + entry.getKey() + ": " + modifyFormat(budget)
                    + ". Оставшийся бюджет: " + modifyFormat(remainBud));
        }
    }

    public String calculateBalance(Wallet currentWallet) {
        if (currentWallet != null) {
            double income = currentWallet.getIncomeTransactions().stream().mapToDouble(Transaction::getAmount).sum();
            double expenses = currentWallet.getExpenseTransactions().stream().mapToDouble(Transaction::getAmount).sum();
            return modifyFormat(currentWallet.getUser().getBeginAmount() + income - expenses);
        }
        return "";
    }

    private static double checkOverBudget(Operation operation, String chosenCategory,
                                          List<Transaction> transactions, Wallet currentWallet) {
        if (operation.equals(Operation.EXPENSE)) {
            double budget = currentWallet.getCategories().getExpenseCat().get(chosenCategory);
            double totalTransAmount = transactions.stream().filter(k -> k.getCategory()
                            .equals(chosenCategory))
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            return budget - totalTransAmount;
        } else return 0;
    }

    private static void printCategoriesWithBud(Operation flag, Category categories) {
        if (flag.equals(Operation.INCOME)) {
            categories.getIncomeCat().forEach(k -> System.out.println("\t - " + k));
        } else {
            categories.getExpenseCat().forEach((key, value) -> System.out.println("\t - " + key + ", бюджет " + value));
        }
    }

    private static void printTransactions(String name, String name2, List<Transaction> transactions) {
        if (!transactions.isEmpty()) {
            System.out.println(name2);
            for (Transaction trans : transactions) {
                System.out.println("\t- " + trans.toString());
            }
        } else {
            System.out.println("\nУ " + name + " нет транзакций");
        }
    }

    private static String modifyFormat(double amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(amount);
    }

    private static String checkIsEqual(Operation flag, String currentCategory, Category categories) {
        if (flag.equals(Operation.INCOME)) {
            Optional<String> str = categories.getIncomeCat().stream()
                    .filter(category -> category.equalsIgnoreCase(currentCategory)).findFirst();
            if (str.isPresent()) return str.get();
        } else {
            Optional<String> str = categories.getExpenseCat().keySet()
                    .stream().filter(key -> key.equalsIgnoreCase(currentCategory)).findFirst();
            if (str.isPresent()) return str.get();
        }
        return null;
    }
    private static void printCategories(Operation flag, Category categories) {
        if (flag.equals(Operation.INCOME)) {
            System.out.println(categories.getIncomeCat());
        } else {
            System.out.println(new HashSet<>(categories.getExpenseCat().keySet()));
        }
    }

    private static boolean checkIsEmpty(Operation flag, Category categories) {
        return flag.equals(Operation.INCOME) && categories.getIncomeCat().isEmpty() ||
                flag.equals(Operation.EXPENSE) && categories.getExpenseCat().isEmpty();
    }

}
