package java_base.multiarray;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int SIZE = 8;

    public static void main(String[] args) {
        int[][] colors = new int[SIZE][SIZE];
        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                colors[i][j] = random.nextInt(256);
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите угол поворота (90, 180 или 270):");
        int angle = sc.nextInt();

        System.out.println("Матрица до поворота:");
        printMatrix(colors);

        int[][] rotated = rotate(colors, angle);

        System.out.println("\nМатрица после поворота на " + angle + " градусов:");
        printMatrix(rotated);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.format("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // Поворот на 90 градусов по часовой стрелке:
    // rotated[j][SIZE - 1 - i] = colors[i][j]
    // то есть: строка i, столбец j исходной → строка j, столбец (SIZE-1-i) новой
    public static int[][] rotate90(int[][] colors) {
        int[][] rotated = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rotated[j][SIZE - 1 - i] = colors[i][j];
            }
        }
        return rotated;
    }

    public static int[][] rotate(int[][] colors, int angle) {
        int times = (angle / 90) % 4; // сколько раз применить поворот на 90°
        int[][] result = colors;
        for (int i = 0; i < times; i++) {
            result = rotate90(result);
        }
        return result;
    }
}