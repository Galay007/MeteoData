import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

public class CheckInput {
    public double readDouble(Scanner in) {
        double z = 0;
        while (true) {
            if (in.hasNextDouble()) {
                z = in.nextDouble();
                if (!Double.isFinite(z) || z <= 0) {
                    System.out.println("\nВы ввели некооректное значение! Повторите ввод...");
                } else {
                    return z;
                }
            } else {
                System.out.println("\nВы ввели некорректное значение. Повторите ввод: ");
                in.next();
            }
        }
    }

    public String readString(Scanner in) {
        String z;
        while (true) {
            if (in.hasNext()) {
                z = in.next();
                return z;
            }
        }
    }

    public int readInt(Scanner in) {
        int z;
        while (true) {
            if (in.hasNextInt()) {
                z = in.nextInt();
                return z;
            }
        }
    }

    public String readStringLIne(Scanner in) {
        String z;
        while (true) {
            if (in.hasNext()) {
                z = in.nextLine();
                return z;
            }
        }
    }

    public String yesNo(Scanner in) {
        String input;
        while (true) {
            input = in.next();
            if (input.equalsIgnoreCase("Да") || input.equalsIgnoreCase("Нет")) {
                return input;
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите 'Да' или 'Нет'.");
            }
        }
    }

    public UUID readUUID(Scanner in) {
        String input;

        while (true) {
            input = in.next();

            if (input.equalsIgnoreCase("назад")) return null;

            if (isValidUUID(input)) {
                System.out.println("Строка является корректным UUID.");
                return UUID.fromString(input);
            } else {
                System.out.println("Строка не является корректным UUID. Попробуйте еще раз.");
            }
        }
    }

    public static boolean isValidUUID(String in) {
        if (in == null) {
            return false;
        }
        try {
            UUID.fromString(in);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String readUrl(Scanner in) {
        String input;

        while (true) {
            input = in.next();

            if (input.equalsIgnoreCase("назад")) return null;

            if (input != null && input.contains("://") && input.contains(".")) {
                if (isUrlReachable(input)) {
                    System.out.println("\nURL " + input + " ответ 200 ОК получен.");
                    return input;
                } else {
                    System.out.println("\nСайт " + input + " не отвечает. Попробуйте ввести с https://... или Назад");
                }
            } else {
                System.out.println("\nСигнатура ввода адреса неполная. Попробуйте еще раз или Назад.");
            }
        }
    }

    public boolean isUrlReachable(String in) {
        try {
            URL url = new URL(in);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK || responseCode == 418);
        } catch (IOException e) {
            return false;
        }
    }


}