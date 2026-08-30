package multithread.first;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        long startTs = System.currentTimeMillis(); // start time

        int[] results = new int[texts.length];

        for (int t = 0; t < texts.length; t++) {
            final String text = texts[t];
            int n = text.length();
            int chunkSize = (n + numThreads - 1) / numThreads;

            List<Future<Integer>> futures = new ArrayList<>();

            for (int th = 0; th < numThreads; th++) {
                final int startI = th * chunkSize;
                final int endI = Math.min(n, startI + chunkSize);
                if (startI >= endI) {
                    continue;
                }

                futures.add(executor.submit(() -> {
                    int localMax = 0;
                    // Тот же самый алгоритм, только i бежит по своему диапазону
                    for (int i = startI; i < endI; i++) {
                        for (int j = 0; j < text.length(); j++) {
                            if (i >= j) {
                                continue;
                            }
                            boolean bFound = false;
                            for (int k = i; k < j; k++) {
                                if (text.charAt(k) == 'b') {
                                    bFound = true;
                                    break;
                                }
                            }
                            if (!bFound && localMax < j - i) {
                                localMax = j - i;
                            }
                        }
                    }
                    return localMax;
                }));
            }

            int maxSize = 0;
            for (Future<Integer> f : futures) {
                maxSize = Math.max(maxSize, f.get());
            }
            results[t] = maxSize;
        }

        long endTs = System.currentTimeMillis(); // end time

        for (int t = 0; t < texts.length; t++) {
            System.out.println(texts[t].substring(0, 100) + " -> " + results[t]);
        }
        System.out.println("Time: " + (endTs - startTs) + "ms");

        executor.shutdown();
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