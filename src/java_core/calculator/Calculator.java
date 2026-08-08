package java_core.calculator;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Calculator {

    // Supplier позволяет получить экземпляр Calculator через ссылку на конструктор
    static Supplier<Calculator> instance = Calculator::new;

    // Бинарные операции над двумя числами
    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> devide = (x, y) -> x / y;

    // Унарные операции над одним числом
    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;

    // Проверка на положительность числа
    Predicate<Integer> isPositive = x -> x > 0;

    // Вывод числа в консоль
    Consumer<Integer> println = System.out::println;

    Calculator() {
    }
}
