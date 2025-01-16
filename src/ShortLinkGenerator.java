import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

public class ShortLinkGenerator {
    private static final String BASE_URL = "clck.ru";
    private static int counter = 1000;
    CheckInput ch = new CheckInput();

    public void generateShortLink(Customer customer, Map<UUID,Customer> customers, Scanner scanner, ConfigReader configs) {
        System.out.print("\nВведите адрес url или Назад: ");
        String url = ch.readUrl(scanner);
        System.out.print("\nВведите ограничение на количество кликов: ");
        int clicks = ch.readInt(scanner);
        System.out.print("Введите ограничение на время действия в минутах: ");
        int minutes = ch.readInt(scanner);


            String shortLink = BASE_URL + "/" + generateUniqueKey(customers,customer, url);
            if (shortLink != null) {
                customer.getShortLinks().put(shortLink,
                        new Link(url,shortLink,Math.max(clicks,configs.getClickLimitConfig()),
                                Math.min(minutes,configs.getTimeLimitConfig())));
                System.out.println("Короткая ссылка создана: " + shortLink + " для " + url);
            }
    }

    private static String generateUniqueKey(Map<UUID,Customer> customers, Customer customer, String longUrl) {

        String hash = generateHash(longUrl);

        if (hash == null) return null;
        String key = hash.substring(0, 6);

        List<String> allShortLinks = customers.values().stream()
                .map(Customer::getShortLinks)         // Получаем Link из каждого Customer
                .filter(Objects::nonNull)      // Оставляем только не-null Link
                .flatMap(links -> links.keySet().stream()) // Получаем Stream<String> из ключей каждой мапы
                .toList();

        while (allShortLinks.contains(key)) {
            counter++;
            hash = generateHash(longUrl + counter);
            if (hash == null) return null;
            key = hash.substring(0, 6);
        }
        return key;
    }

    private static String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Ошибка алгоритма хеширования: " + e.getMessage());
            return null;
        }

    }
}
