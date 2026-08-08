package java_core.calculator;

public class Main {

    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);   // a = 1 + 2 = 3
        int b = calc.minus.apply(1, 1);  // b = 1 - 1 = 0


// ПОЧЕМУ КОД НЕ РАБОТАЕТ:
//
// b = 1 - 1 = 0. Дальше вызывается деление a / b, то есть 3 / 0.
//
// В Java при делении целых чисел на ноль программа не выдаёт бесконечность
// (как было бы с дробными числами), а сразу падает с ошибкой
// ArithmeticException: "/ by zero".
// Компилятор эту ошибку не видит, потому что значение b становится известно
// только когда программа запущена.
//
// КАК ИСПРАВИТЬ:
// 1) Проверять, что делитель не равен нулю, перед делением.
// 2) Использовать try-catch, чтобы поймать ошибку и не дать программе упасть.
//
// Ниже — исправленный код с try-catch.

        try {
            int c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: деление на ноль невозможно (" + e.getMessage() + ")");
        }

        // Демонстрация остальных операций калькулятора
        calc.println.accept(calc.multiply.apply(a, 4));   // 12
        calc.println.accept(calc.pow.apply(5));            // 25
        calc.println.accept(calc.abs.apply(-7));            // 7
        System.out.println(calc.isPositive.test(-3));       // false
    }
}
