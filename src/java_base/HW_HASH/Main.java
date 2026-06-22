import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // --- Заполнение справочника адресов и цен доставки ---
        Map<Address, Integer> costPerAddress = new HashMap<>();

        costPerAddress.put(new Address("Россия", "Москва"),    200);
        costPerAddress.put(new Address("Россия", "Казань"),    200);
        costPerAddress.put(new Address("Россия", "Новосибирск"), 250);
        costPerAddress.put(new Address("Беларусь", "Минск"),   180);
        costPerAddress.put(new Address("Беларусь", "Гомель"),  190);
        costPerAddress.put(new Address("Германия", "Берлин"),  500);
        costPerAddress.put(new Address("Германия", "Мюнхен"),  520);
        costPerAddress.put(new Address("США", "Нью-Йорк"),    900);
        costPerAddress.put(new Address("США", "Лос-Анджелес"), 950);
        costPerAddress.put(new Address("Китай", "Пекин"),     600);

        // --- Переменные для накопления статистики ---
        int totalCost = 0;                      // общая сумма всех доставок
        Set<String> uniqueCountries = new HashSet<>(); // уникальные страны заказов

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Заполнение нового заказа.");
            System.out.print("Введите страну (или 'end' для выхода): ");
            String country = scanner.nextLine().trim();

            if (country.equalsIgnoreCase("end")) {
                break;
            }

            System.out.print("Введите город: ");
            String city = scanner.nextLine().trim();

            System.out.print("Введите вес (кг): ");
            int weight;
            try {
                weight = Integer.parseInt(scanner.nextLine().trim());
                if (weight <= 0) {
                    System.out.println("Вес должен быть положительным числом.\n");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный вес. Попробуйте снова.\n");
                continue;
            }

            Address inputAddress = new Address(country, city);

            if (!costPerAddress.containsKey(inputAddress)) {
                System.out.println("Доставки по этому адресу нет\n");
            } else {
                int pricePerKg  = costPerAddress.get(inputAddress);
                int deliveryCost = pricePerKg * weight;
                totalCost += deliveryCost;
                uniqueCountries.add(country);

                System.out.println("Стоимость доставки составит: " + deliveryCost + " руб.");
                System.out.println("Общая стоимость всех доставок: " + totalCost + " руб.");
                System.out.println("Количество уникальных стран доставки: " + uniqueCountries.size() + "\n");
            }
        }

        // --- Итоговая сводка ---
        System.out.println("=== Итоги работы ===");
        System.out.println("Общая сумма всех доставок: " + totalCost + " руб.");
        System.out.println("Уникальных стран доставки: " + uniqueCountries.size());
        if (!uniqueCountries.isEmpty()) {
            System.out.println("Страны: " + uniqueCountries);
        }

        scanner.close();
    }
}
