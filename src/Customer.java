import java.util.ArrayList;
import java.util.List;

public class Customer {
    String name;
    List<Bank_Account> accounts;

    public Customer() {
    }
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bank_Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Bank_Account> accounts) {
        this.accounts = accounts;
    }
    public void addAccount(Bank_Account account) {
        this.accounts.add(account);
    }
}
