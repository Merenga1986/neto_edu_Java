package java_base.HW_QUEUE;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        List<String> firstClients = List.of(
                "Anya", "Sveta", "Olya", "Alexandra", "Ruslana", "Olesya", "Vika"
        );

        // Создаём пустую очередь и заполняем её первыми клиентами
        Queue<String> clientsQueue = new LinkedList<>(firstClients);

        // Разбираем очередь, пока она не опустеет
        while (!clientsQueue.isEmpty()) {
            // Извлекаем (и удаляем) клиента из начала очереди
            String currentClient = clientsQueue.poll();

            // "Обрабатываем" клиента
            System.out.println(currentClient + " сделала новый маникюр.");

            // С вероятностью 50% клиент приводит друга
            if (Math.random() < 0.5) {
                String friendName = "a friend of " + currentClient;
                clientsQueue.add(friendName);
            }
        }
    }
}