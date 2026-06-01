package java_base.HW_POLY.taxes;

public class UsnIncome extends TaxSystem {
    @Override
    public int calcTaxFor(int debit, int credit) {
        return (int) (debit * 0.06);
    }
}