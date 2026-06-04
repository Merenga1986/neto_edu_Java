package java_base.HW_ABST;

public class SimpleAccount extends Account {

    public SimpleAccount(long initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean add(long amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || balance < amount) return false;
        balance -= amount;
        return true;
    }
}
