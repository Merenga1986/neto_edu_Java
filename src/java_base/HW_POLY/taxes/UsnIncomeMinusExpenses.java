package java_base.HW_POLY.taxes;

public class UsnIncomeMinusExpenses extends TaxSystem {
    @Override
    public int calcTaxFor(int debit, int credit) {
        int profit = debit - credit;
        return profit > 0 ? (int) (profit * 0.15) : 0;
    }
}