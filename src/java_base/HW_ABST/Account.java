package java_base.HW_ABST;

public abstract class Account {
    protected long balance;

    public abstract boolean add(long amount);
    public abstract boolean pay(long amount);

    public boolean transfer(Account account, long amount) {
        if (this.pay(amount)) {
            if (account.add(amount)) {
                return true;
            }
            // Откат: если add не удался — возвращаем деньги обратно
            this.add(amount);
        }
        return false;
    }

    public long getBalance() {
        return balance;
    }
}
