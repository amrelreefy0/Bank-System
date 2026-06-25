import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Current_account extends Bank_Account {
    private double overdraft;

    public Current_account(String account_number, double balance, double overdraft) {
        super(account_number, balance);
        this.overdraft = overdraft;
    }

    @Override
    void withdraw(double amount) {
        if (amount <= 0) {
           operations.add("Withdraw " + amount + " EGP -> Error: Invalid amount");
        } else if (getBalance()-amount < -overdraft) {
            operations.add("Withdraw " + amount + " EGP -> Error: Cannot exceed overdraft limit (" + overdraft + " EGP)");
        }
       else {
           setBalance(getBalance()-amount);
           operations.add("Withdraw " + amount + " EGP -> Balance: " + getBalance()+" EGP");
           transactions.add(new Transaction("Withdraw", amount, LocalDateTime.now(), getBalance()));
       }
    }
    @Override
    void transfer(double amount, Bank_Account destination) {
        if (amount <= 0) {
            operations.add("Transfer " + amount + " EGP to " + destination.getAccount_number() + " -> Error: Invalid amount");
        } else if (getBalance() - amount < -overdraft) { // ← شرط الـ overdraft
            operations.add("Transfer " + amount + " EGP to " + destination.getAccount_number() + " → Insufficient funds");
        } else {
            setBalance(getBalance() - amount);
            operations.add("Transfer " + amount +" EGP to " + destination.getAccount_number() + " -> Balance: " + getBalance() + " EGP");
            transactions.add(new Transaction("Transfer", amount, LocalDateTime.now(), getBalance()));
            destination.deposit(amount);
        }
    }

    @Override
    void print() {
        System.out.println("Account: [" + getAccount_number() + " | Current | Balance: " + getInitial_balance() + " EGP]");
        for (int i = 0; i < operations.size(); i++) {
            System.out.println("Operation: " + operations.get(i));
        }
        System.out.println("--- Statement ---");
        for (int i = 0; i < transactions.size(); i++) {
            String sign;
            if(transactions.get(i).getType().equals("Deposit")) {
                sign = "+";
            }
            else {sign = "-";};
            System.out.println("[" + transactions.get(i).getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "] " + transactions.get(i).getType() + " " + sign + transactions.get(i).getAmount() + " EGP | Balance: " + transactions.get(i).getBalanceAfter() + " EGP");
        }
    }
}
