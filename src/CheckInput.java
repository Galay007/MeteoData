import java.util.Scanner;

public class CheckInput {
    public double readDouble(Scanner in) {
        double z = 0;
        while (true) {
            if (in.hasNextDouble()) {
                z = in.nextDouble();
                if (!Double.isFinite(z) || z <= 0) {
                    System.out.println("\nВы ввели некооректное значение! Повторите ввод...");
                    continue;
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

    public String readStringLIne(Scanner in) {
        String z;
        while (true) {
            if (in.hasNext()) {
                z = in.nextLine();
                return z;
            }
        }
    }
}
