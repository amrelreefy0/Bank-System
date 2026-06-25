import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public abstract class Bank_Account {
    private String account_number;
    private double balance;
    private double Initial_balance;
    List<Transaction> transactions;
    List<String> operations = new ArrayList<>();
    protected Bank_Account(String account_number, double balance) {
        this.account_number = account_number;
        this.balance = balance;
        this.Initial_balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInitial_balance() {
        return Initial_balance;
    }

    abstract void withdraw(double amount);

    void deposit(double amount) {
        if (amount <= 0) {
            operations.add("Deposit "+ amount +" EGP -> Error: Invalid amount");
        } else {
            balance += amount;
            operations.add("Deposit " + amount + " EGP -> Balance: " + balance + " EGP");
            transactions.add(new Transaction("Deposit", amount, LocalDateTime.now(), balance));
        }
    }

    ;

    abstract void transfer(double amount, Bank_Account destination);

    double check_balance() {
        return balance;
    }

    ;

    abstract void print();
}
