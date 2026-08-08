package java_core.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        // 1. Отфильтровать положительные числа
        List<Integer> positive = new ArrayList<>();
        for (Integer number : intList) {
            if (number > 0) {
                positive.add(number);
            }
        }

        // 2. Среди положительных отобрать чётные
        List<Integer> evenPositive = new ArrayList<>();
        for (Integer number : positive) {
            if (number % 2 == 0) {
                evenPositive.add(number);
            }
        }

        // 3. Отсортировать по возрастанию
        Collections.sort(evenPositive);

        // 4. Вывести результат
        for (Integer number : evenPositive) {
            System.out.println(number);
        }
    }
}
