package java_base.HW_LIST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TodoManager {

    private static final List<String> todos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Ваш выбор: ");

            switch (choice) {
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                case 1 -> addTodo();
                case 2 -> printTodos();
                case 3 -> deleteByNumber();
                case 4 -> deleteByText();
                case 5 -> deleteByKeyword();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nВыберите операцию:");
        System.out.println("0. Выход из программы");
        System.out.println("1. Добавить дело");
        System.out.println("2. Показать дела");
        System.out.println("3. Удалить дело по номеру");
        System.out.println("4. Удалить дело по названию");
        System.out.println("5. Удалить дела по ключевому слову");
    }

    private static void printTodos() {
        if (todos.isEmpty()) {
            System.out.println("Список дел пуст.");
            return;
        }
        System.out.println("Ваш список дел:");
        for (int i = 0; i < todos.size(); i++) {
            System.out.println((i + 1) + ". " + todos.get(i));
        }
    }

    private static void addTodo() {
        System.out.print("Введите название задачи: ");
        String task = scanner.nextLine().trim();

        if (task.isEmpty()) {
            System.out.println("Название задачи не может быть пустым.");
            return;
        }
        if (todos.contains(task)) {
            System.out.println("Такая задача уже есть в списке.");
            return;
        }

        todos.add(task);
        System.out.println("Добавлено!");
        printTodos();
    }

    private static void deleteByNumber() {
        int number = readInt("Введите номер для удаления: ");

        if (number < 1 || number > todos.size()) {
            System.out.println("Нет дела с номером " + number + ".");
            return;
        }

        todos.remove(number - 1);
        System.out.println("Удалено!");
        printTodos();
    }

    private static void deleteByText() {
        System.out.print("Введите задачу для удаления: ");
        String task = scanner.nextLine().trim();

        if (!todos.remove(task)) {
            System.out.println("Дело с таким текстом не найдено.");
            return;
        }

        System.out.println("Удалено!");
        printTodos();
    }

    private static void deleteByKeyword() {
        System.out.print("Введите ключевое слово для удаления: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Ключевое слово не может быть пустым.");
            return;
        }

        // Используем Iterator, чтобы безопасно удалять элементы во время обхода
        int removedCount = 0;
        Iterator<String> iterator = todos.iterator();
        while (iterator.hasNext()) {
            String todo = iterator.next();
            if (todo.contains(keyword)) {
                iterator.remove();
                removedCount++;
            }
        }

        if (removedCount == 0) {
            System.out.println("Задачи с ключевым словом \"" + keyword + "\" не найдены.");
        } else {
            System.out.println("Удалено задач: " + removedCount + ".");
            printTodos();
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите целое число.");
            }
        }
    }
}