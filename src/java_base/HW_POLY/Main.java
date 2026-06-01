package java_base.HW_POLY;

import java_base.HW_POLY.taxes.UsnIncome;
import java_base.HW_POLY.taxes.UsnIncomeMinusExpenses;

public class Main {
    public static void main(String[] args) {
        // --- Задание 1 ---
        Company company1 = new Company("ООО Ромашка", new UsnIncome());
        company1.shiftMoney(100_000);
        company1.shiftMoney(-40_000);
        company1.payTaxes();
        // Налог: 100000 * 6% = 6000 руб.

        Company company2 = new Company("ИП Иванов", new UsnIncomeMinusExpenses());
        company2.shiftMoney(200_000);
        company2.shiftMoney(-50_000);
        company2.payTaxes();
        // Налог: (200000 - 50000) * 15% = 22500 руб.

        // Смена системы налогооблажения
        company2.setTaxSystem(new UsnIncome());
        company2.shiftMoney(80_000);
        company2.payTaxes();
        // Налог: 80000 * 6% = 4800 руб.

        System.out.println();

        // --- Задание 2 ---
        Company company3 = new Company("АО Технологии", new UsnIncomeMinusExpenses());
        Deal[] deals = {
                new Sale("Ноутбук", 120_000),
                new Sale("Телефон", 60_000),
                new Expenditure("Аренда офиса", 30_000),
                new Expenditure("Зарплата", 50_000)
        };

        int profit = company3.applyDeals(deals);
        // Доходы: 180000, Расходы: 80000
        // Налог: (180000 - 80000) * 15% = 15000 руб.
        System.out.println("Прибыль до уплаты налогов: " + profit + " руб.");
    }
}
