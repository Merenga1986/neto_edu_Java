package HW_PRIMITIVES;

import java.util.Scanner;

public class Main {

    private static int taxEarnings(int earnings) {
        int tax = earnings * 6 / 100;
        return tax;
    }

    private static int taxEarningsMinusSpendings(int earnings, int spendings) {
        int tax = (earnings - spendings) * 15 / 100;
        return tax;
    }

    private static void bestTax(int earnings, int spendings) {
        int taxA = taxEarnings(earnings);
        int taxB = taxEarningsMinusSpendings(earnings, spendings);

        if (taxA < taxB) {
            System.out.println("Мы советуем вам УСН доходы");
            System.out.println("Ваш налог составит: " + taxA + " рублей");
            System.out.println("Налог на другой системе: " + taxB + " рублей");
            System.out.println("Экономия: " + (taxB - taxA) + " рублей");
        } else if (taxB < taxA) {
            System.out.println("Мы советуем вам УСН доходы минус расходы");
            System.out.println("Ваш налог составит: " + taxB + " рублей");
            System.out.println("Налог на другой системе: " + taxA + " рублей");
            System.out.println("Экономия: " + (taxA - taxB) + " рублей");
        } else {
            System.out.println("Можете выбрать любую систему налогообложения");
            System.out.println("Налог на обеих системах составит: " + taxA + " рублей");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int earnings = 0;
        int spendings = 0;

        while (true) {
            System.out.println("Выберите операцию и введите её номер:");
            System.out.println("1. Добавить новый доход");
            System.out.println("2. Добавить новый расход");
            System.out.println("3. Выбрать систему налогообложения");

            // обрезаем пробелы и кладем в переменную
            String input = scanner.nextLine().trim();
            // если end сразу заканчиваем цикл
            if ("end".equals(input)) {
                break;
            }
            // парсим стрингу в число
            int operation = Integer.parseInt(input);

            switch (operation) {
                // 1 кейс суммируем весь введенный доход
                case 1: {
                    System.out.println("Введите сумму дохода:");
                    String earningsStr = scanner.nextLine();
                    int money = Integer.parseInt(earningsStr.trim());
                    earnings += money;
                    System.out.println("Доход добавлен. Текущие доходы: " + earnings + " рублей");
                    break;
                }
                // 2 кейс суммируем весь введенный расход
                case 2: {
                    System.out.println("Введите сумму расхода:");
                    String spendingsStr = scanner.nextLine();

                    int money = Integer.parseInt(spendingsStr.trim());
                    spendings += money;
                    System.out.println("Расход добавлен. Текущие расходы: " + spendings + " рублей");
                    break;
                }
                // 3 кейс передаем доход и расход в метод
                case 3:
                    bestTax(earnings, spendings);
                    break;

                default:
                    System.out.println("Такой операции нет");
            }
        }

        System.out.println("Программа завершена!");
        scanner.close();
    }
}
