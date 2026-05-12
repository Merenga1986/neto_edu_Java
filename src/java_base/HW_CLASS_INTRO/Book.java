package java_base.HW_CLASS_INTRO;

public class Book {
    private String title;
    private int releaseYear;
    private Author author;
    private int pages;

    public Book(String title, int releaseYear, Author author, int pages) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.author = author;
        this.pages = pages;
    }

    // книга считается большой, если страниц больше 500
    public boolean isBig() {
        return pages > 500;
    }

    // проверяем, содержится ли слово в названии, имени или фамилии автора
    public boolean matches(String word) {
        return title.contains(word)
                || author.getName().contains(word)
                || author.getSurname().contains(word);
    }

    // 3 рубля за страницу * sqrt(рейтинг), округлено вниз, но не меньше 250
    public int estimatePrice() {
        int price = (int) Math.floor(3 * pages * Math.sqrt(author.getRating()));
        return Math.max(price, 250);
    }
}