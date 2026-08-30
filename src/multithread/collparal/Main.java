package multithread.collparal;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    private static final int TEXTS_COUNT = 10_000;
    private static final int TEXT_LENGTH = 100_000;
    private static final int QUEUE_CAPACITY = 100;

    // Уникальный маркер конца потока данных.
    // Сравниваем по ссылке (==), поэтому случайно сгенерированный текст
    // никогда не будет спутан с ним, независимо от содержимого.
    private static final String POISON_PILL = new String("POISON_PILL");

    public static final BlockingQueue<String> queueA = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    public static final BlockingQueue<String> queueB = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    public static final BlockingQueue<String> queueC = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) throws InterruptedException {
        long startTs = System.currentTimeMillis();

        Thread producer = new Thread(Main::produceTexts);

        Result[] results = new Result[3];
        Thread threadA = new Thread(() -> results[0] = analyze(queueA, 'a'));
        Thread threadB = new Thread(() -> results[1] = analyze(queueB, 'b'));
        Thread threadC = new Thread(() -> results[2] = analyze(queueC, 'c'));

        producer.start();
        threadA.start();
        threadB.start();
        threadC.start();

        producer.join();
        threadA.join();
        threadB.join();
        threadC.join();

        long endTs = System.currentTimeMillis();

        System.out.println("Максимум 'a': " + results[0].count
                + " (" + preview(results[0].text) + ")");
        System.out.println("Максимум 'b': " + results[1].count
                + " (" + preview(results[1].text) + ")");
        System.out.println("Максимум 'c': " + results[2].count
                + " (" + preview(results[2].text) + ")");
        System.out.println("Time: " + (endTs - startTs) + "ms");
    }

    private static void produceTexts() {
        for (int i = 0; i < TEXTS_COUNT; i++) {
            String text = generateText("abc", TEXT_LENGTH);
            try {
                // put блокируется, если очередь заполнена (размер 100) —
                // это и есть та самая обратная связь (backpressure) между
                // производителем и потребителями.
                queueA.put(text);
                queueB.put(text);
                queueC.put(text);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        try {
            queueA.put(POISON_PILL);
            queueB.put(POISON_PILL);
            queueC.put(POISON_PILL);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static Result analyze(BlockingQueue<String> queue, char target) {
        int maxCount = -1;
        String bestText = "";
        try {
            while (true) {
                String text = queue.take();
                if (text == POISON_PILL) {
                    break;
                }
                int count = countChar(text, target);
                if (count > maxCount) {
                    maxCount = count;
                    bestText = text;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Result(maxCount, bestText);
    }

    private static int countChar(String text, char target) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    private static String preview(String text) {
        return text.substring(0, Math.min(100, text.length()));
    }

    private static class Result {
        final int count;
        final String text;

        Result(int count, String text) {
            this.count = count;
            this.text = text;
        }
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}