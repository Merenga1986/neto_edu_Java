package java_base.HW_ABST;

public class Main {
    public static void main(String[] args) {
        // --- Счета ---
        SimpleAccount simple = new SimpleAccount(1000);
        System.out.println("Баланс: " + simple.getBalance());   // 1000
        System.out.println("Оплата 300: " + simple.pay(300));   // true
        System.out.println("Оплата 800: " + simple.pay(800));   // false (недостаточно)
        System.out.println("Пополнение 500: " + simple.add(500)); // true
        System.out.println("Баланс: " + simple.getBalance());   // 1200

        CreditAccount credit = new CreditAccount(5000);
        System.out.println("Кредитный баланс: " + credit.getBalance()); // 0
        System.out.println("Оплата 3000: " + credit.pay(3000));    // true
        System.out.println("Кредитный баланс: " + credit.getBalance());   // -3000
        System.out.println("Оплата 3000: " + credit.pay(3000));    // false (лимит)
        System.out.println("Пополнение 1000: " + credit.add(1000));       // true
        System.out.println("Кредитный баланс: " + credit.getBalance());   // -2000
        System.out.println("Пополнение 3000: " + credit.add(3000));       // false (ушёл бы в плюс)

        System.out.println("Перевод 500 simple -> credit: " + simple.transfer(credit, 500));  // true
        System.out.println("Simple баланс: " + simple.getBalance());      // 700
        System.out.println("Credit баланс: " + credit.getBalance());      // -1500

        // --- Логгеры ---
        System.out.println();
        Logger simpleLogger = new SimpleLogger();
        simpleLogger.log("Сервер запущен");
        simpleLogger.log("Соединение установлено");

        System.out.println();
        SmartLogger smartLogger = new SmartLogger();
        smartLogger.log("Сервер запущен");
        smartLogger.log("Критическая Error соединения");
        smartLogger.log("Повторное подключение");
        smartLogger.log("ERROR: база данных недоступна");
    }
}
