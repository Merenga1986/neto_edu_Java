package java_core.population;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamMain {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        intList.stream()
                .filter(x -> x > 0)                    // положительные
                .filter(x -> x % 2 == 0)                // чётные
                .sorted(Comparator.naturalOrder())      // по возрастанию
                .forEach(System.out::println);          // вывод в консоль
    }
}
