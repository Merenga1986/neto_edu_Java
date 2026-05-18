package java_base.array;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] products = {"Молоко", "Хлеб", "Гречневая крупа"};
        int[] prices = {50, 14, 80};
        int[] cart = new int[products.length]; // количество каждого товара в корзине

        // Вывод списка товаров
        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }

        // Цикл ввода
        while (true) {
            System.out.println("Выберите товар и количество или введите end");
            String inputString = sc.nextLine().trim();

            if (inputString.equals("end")) {
                break;
            }

            String[] parts = inputString.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1; // -1, т.к. вводим с 1
            int productCount = Integer.parseInt(parts[1]);

            cart[productNumber] += productCount;
        }

        // Вывод корзины и подсчёт итога
        System.out.println("\nВаша корзина:");
        int sumProducts = 0;

        for (int i = 0; i < products.length; i++) {
            if (cart[i] > 0) {
                int total = cart[i] * prices[i];
                sumProducts += total;
                System.out.println(products[i] + " " + cart[i] + " шт " +
                        prices[i] + " руб/шт " + total + " руб в сумме");
            }
        }

        System.out.println("Итого " + sumProducts + " руб");
    }
}