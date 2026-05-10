package algo.sorts;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        // многомерный массив 0-2
        int[][] teams = {
                { 45, 31, 24, 22, 20, 17, 14, 13, 12, 10 },
                { 31, 18, 15, 12, 10, 8, 6, 4, 2, 1 },
                { 51, 30, 10, 9, 8, 7, 6, 5, 2, 1 }
        };

        int[] nationalTeam = mergeAll(teams);
        System.out.println(Arrays.toString(nationalTeam)); // [51, 45, 31, 31, 30, 24, 22, 20, 18, 17]
    }

    /** Метод для слияния всех команд в одну национальную */
    public static int[] mergeAll(int[][] teams) {
        // берём первую команду как начальный промежуточный результат
        int[] result = teams[0];
        // сливаем промежуточный результат с каждой следующей командой
        for (int i = 1; i < teams.length; i++) {
            result = merge(result, teams[i]);
        }
        return result;
    }

    /** Метод для слияния двух команд в одну */
    public static int[] merge(int[] teamA, int[] teamB) {
        // итоговый массив топ-10
        int[] result = new int[10];
        int i = 0; // указатель на teamA
        int j = 0; // указатель на teamB

        // набираем 10 лучших игроков из двух команд
        for (int k = 0; k < 10; k++) {
            // берём из teamA если: teamB закончился, или текущий игрок teamA не хуже
            if (i < teamA.length && (j >= teamB.length || teamA[i] >= teamB[j])) {
                result[k] = teamA[i];
                i++;
            } else {
                // иначе берём из teamB
                result[k] = teamB[j];
                j++;
            }
        }
        return result;
    }
}