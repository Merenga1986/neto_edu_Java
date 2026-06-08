package java_base.HW_EXC;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordChecker checker = new PasswordChecker();

        // --- Считываем настройки ---
        System.out.print("Введите мин. длину пароля: ");
        int minLength = scanner.nextInt();

        System.out.print("Введите макс. допустимое количество повторений символа подряд: ");
        int maxRepeats = scanner.nextInt();
        scanner.nextLine(); // сбрасываем перевод строки после nextInt()

        try {
            checker.setMinLength(minLength);
            checker.setMaxRepeats(maxRepeats);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка настройки: " + e.getMessage());
            return;
        }

        // --- Бесконечный цикл проверки паролей ---
        while (true) {
            System.out.print("Введите пароль или end: ");
            String input = scanner.nextLine();

            if (input.equals("end")) {
                break;
            }

            if (checker.verify(input)) {
                System.out.println("Подходит!");
            } else {
                System.out.println("Не подходит!");
            }
        }

        System.out.println("Программа завершена");
    }
}