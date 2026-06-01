package java_base.HW_POLY;

public class Expenditure extends Deal {
    public Expenditure(String productName, int price) {
        super("Покупка " + productName + " на " + price + " руб.", 0, price);
    }
}
