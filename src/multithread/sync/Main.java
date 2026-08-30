package multithread.sync;

import java.util.*;

public class Main {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        int threadsCount = 1000;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                String route = generateRoute("RLRFR", 100);
                int rCount = countR(route);
                System.out.println(route + " -> " + rCount);
                updateFreq(rCount);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        printSummary();
    }

    private static int countR(String route) {
        int count = 0;
        for (int i = 0; i < route.length(); i++) {
            if (route.charAt(i) == 'R') {
                count++;
            }
        }
        return count;
    }

    private static void updateFreq(int size) {
        synchronized (sizeToFreq) {
            sizeToFreq.merge(size, 1, Integer::sum);
        }
    }

    private static void printSummary() {
        int mostFrequentSize = 0;
        int mostFrequentCount = -1;

        for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
            if (entry.getValue() > mostFrequentCount) {
                mostFrequentCount = entry.getValue();
                mostFrequentSize = entry.getKey();
            }
        }

        System.out.println("Самое частое количество повторений " + mostFrequentSize
                + " (встретилось " + mostFrequentCount + " раз)");
        System.out.println("Другие размеры:");

        final int excludedSize = mostFrequentSize;
        List<Map.Entry<Integer, Integer>> others = new ArrayList<>(sizeToFreq.entrySet());
        others.removeIf(e -> e.getKey() == excludedSize);
        others.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<Integer, Integer> entry : others) {
            System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}