package multithread.varmultithread;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final AtomicInteger count3 = new AtomicInteger(0);
    public static final AtomicInteger count4 = new AtomicInteger(0);
    public static final AtomicInteger count5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread palindromeThread = new Thread(() -> checkTexts(texts, Main::isPalindrome));
        Thread sameLetterThread = new Thread(() -> checkTexts(texts, Main::isSameLetter));
        Thread increasingThread = new Thread(() -> checkTexts(texts, Main::isNonDecreasing));

        palindromeThread.start();
        sameLetterThread.start();
        increasingThread.start();

        palindromeThread.join();
        sameLetterThread.join();
        increasingThread.join();

        System.out.println("Красивых слов с длиной 3: " + count3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + count4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + count5.get() + " шт");
    }

    private static void checkTexts(String[] texts, java.util.function.Predicate<String> criterion) {
        for (String text : texts) {
            if (criterion.test(text)) {
                incrementCounter(text.length());
            }
        }
    }

    private static void incrementCounter(int length) {
        switch (length) {
            case 3 -> count3.incrementAndGet();
            case 4 -> count4.incrementAndGet();
            case 5 -> count5.incrementAndGet();
            default -> throw new IllegalStateException("Unexpected length: " + length);
        }
    }

    private static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isSameLetter(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != text.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNonDecreasing(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1)) {
                return false;
            }
        }
        return true;
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