package java_base.HW_CLASS_INTRO;

public class Main {
    public static void main(String[] args) {
        Author griboedov = new Author("Александр", "Грибоедов", 9);
        Book woeFromWit = new Book("Горе от ума", 1824, griboedov, 400);

        Author pushkin = new Author("Александр", "Пушкин", 4);
        Book onegin = new Book("Евгений Онегин", 1833, pushkin, 501);

        // isBig()
        System.out.println("«Горе от ума» большая? " + woeFromWit.isBig());   // false (400 < 500)
        System.out.println("«Евгений Онегин» большая? " + onegin.isBig());     // ture

        // matches()
        System.out.println("«Горе от ума» содержит «горе»? " + woeFromWit.matches("горе"));           // true
        System.out.println("«Горе от ума» содержит «Пушкин»? " + woeFromWit.matches("Пушкин"));       // false

        // estimatePrice()
        System.out.println("Цена «Горе от ума»: " + woeFromWit.estimatePrice() + " руб.");
        System.out.println("Цена «Евгений Онегин»: " + onegin.estimatePrice() + " руб.");

        // проверка минимальной цены (маленькая книга с низким рейтингом)
        Author unknown = new Author("Иван", "Иванов", 1);
        Book tiny = new Book("Брошюра", 2020, unknown, 10);
        System.out.println("Цена «Брошюра»: " + tiny.estimatePrice() + " руб.");
    }
}
