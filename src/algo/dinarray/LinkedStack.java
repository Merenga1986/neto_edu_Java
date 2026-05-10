package algo.dinarray;

public class LinkedStack {
    private Node tail; // ссылка на последний добавленный узел (обёртку)
    private int size; // размер стека, т.е. количество элементов в нём

    public void push(int value) {
        Node node = new Node(value); // создаём новый узел
        if (tail != null) { // если в стеке уже есть элементы
            node.setPrev(tail); // связываем новый узел с последним
        }
        tail = node; // назначаем новый узел последним узлом
        size++; // увеличиваем счётчик элементов
    }

    public int pop() {
        // берём значение из последнего узла
        int value = tail.getValue();
        // назначаем предыдущий узел последним
        tail = tail.getPrev();
        // уменьшаем счётчик элементов
        size--;
        return value;
    }

    public int getSize() {
        // возвращаем размер стека
        return size;
    }

    public boolean isEmpty() {
        // стек пуст, если размер равен нулю
        return size == 0;
    }

    public String toString() {
        // если стек пуст — выводим EMPTY
        if (isEmpty()) {
            return "EMPTY";
        }
        // проходим по связному списку от tail к началу, не меняя стек
        StringBuilder sb = new StringBuilder();
        Node current = tail;
        while (current != null) {
            sb.append(current.getValue());
            // если есть следующий узел — добавляем разделитель
            if (current.getPrev() != null) {
                sb.append(" -> ");
            }
            current = current.getPrev();
        }
        return sb.toString();
    }
}