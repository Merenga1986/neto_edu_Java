package HW_STRUCTURE;

import java.util.Scanner;
// тут немного отошел от задания, не стал создавать пакеты ru.netology.service, прошу отнестись с пониманием
import HW_STRUCTURE.CustomsService;

public class Main {

    public static void main(String[] args) {
        // новый объект сканер
        Scanner input = new Scanner(System.in);
        // запрос цены
        System.out.print("Введите цену товара (в руб.): ");
        int price = input.nextInt();
        // запрос веса
        System.out.print("Введите вес товара (в кг.): ");
        int weight = input.nextInt();
        //обращение к методу и вывод результата
        int duty =  calcDuty(price, weight);
        System.out.println("Размер пошлины (в руб.) составит: " + duty);
    }
}
