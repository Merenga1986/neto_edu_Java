package java_base.HW_ABST;

public class CreditAccount extends Account {
    private long creditLimit;

    // Баланс кредитного счёта изначально 0 и не может быть > 0
    public CreditAccount(long creditLimit) {
        this.balance = 0;
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean add(long amount) {
        if (amount <= 0) return false;
        // Нельзя уходить в плюс
        if (balance + amount > 0) return false;
        balance += amount;
        return true;
    }

    @Override
    public boolean pay(long amount) {
        if (amount <= 0) return false;
        // Нельзя превышать кредитный лимит
        if (balance - amount < -creditLimit) return false;
        balance -= amount;
        return true;
    }
}