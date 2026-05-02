package HW_STRUCTURE;

public class CustomsService {

    public static final int RATE_PER_KG = 100;

    // Статический метод для расчёта пошлины
    public static int calcDuty(int price, int weight) {
        return price / 100 + weight * RATE_PER_KG;
    }
}