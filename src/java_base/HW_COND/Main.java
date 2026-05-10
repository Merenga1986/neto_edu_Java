package java_base.HW_COND;

import java.util.Scanner;
// не стал выносить проверку в отдельный класс-метод, т.к. на мой взгляд это избыточно
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int x = 0;

        while (true) {
            System.out.println("Введите год в формате yyyy: ");
            int year = input.nextInt();
            System.out.println("Введите количество дней: ");
            int dayCount = input.nextInt();
            // задаем условие проверки високосный год или нет, присваиваем переменной значение в зависимости от условия
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            int correctDays = isLeapYear ? 366 : 365;

            if (dayCount == correctDays) {
                x++;  // увеличиваем счётчик очков при правильном ответе
            } else {
                // при неправильном ответе завершаем цикл и подводим итог
                System.out.println("Неправильно! В этом году " + correctDays + " дней!");
                System.out.println("Набрано очков: " + x);
                break;
            }
        }
        input.close();
    }
}