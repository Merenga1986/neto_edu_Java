package algo.recursion;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = { 21, 1, 20, 23 };
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, new int[day]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    // Основной метод без мемоизации (простая рекурсия)
    public static int chooseHobbyRecursive(int[] startNumbers, int day) {
        // Базовый случай: если день <= 0, возвращаем значение из начального массива
        if (day <= 0) {
            return startNumbers[day + 3];   // например, для day=0 берём startNumbers[3]
        }
        // Рекурсивно считаем результат для предыдущего дня
        int prev = chooseHobbyRecursive(startNumbers, day - 1);
        // Рекурсивно считаем результат для дня, который был 3 дня назад
        int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3);
        // Вычисляем новое значение по формуле и возвращаем
        return (prev * prePrePrev) % 10 + 1;
    }
    // Перегруженный метод — рекурсия с мемоизацией (запоминанием результатов)
    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {
        // Базовый случай — те же дни <= 0
        if (day <= 0) {
            return startNumbers[day + 3];
        }
        // Проверяем, не посчитали ли мы уже результат для этого дня
        if (memory[day - 1] != 0) {
            return memory[day - 1];   // если уже есть — сразу возвращаем, не считаем заново
        }
        // Считаем результат для предыдущего дня (рекурсия)
        int prev = chooseHobbyRecursive(startNumbers, day - 1, memory);
        // Считаем результат для дня -3 (рекурсия)
        int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3, memory);
        // Вычисляем текущий результат по формуле
        int result = (prev * prePrePrev) % 10 + 1;
        // Сохраняем результат в массив памяти, чтобы потом не пересчитывать
        memory[day - 1] = result;

        return result;
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4;
            int prev = numbers.get(index - 1);
            int prePrePrev = numbers.get(index - 3);
            numbers.add((prev * prePrePrev) % 10 + 1);
        }
        return numbers.get(numbers.size() - 1);
    }
}