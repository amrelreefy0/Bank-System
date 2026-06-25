import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Customer c1 = new Customer("Amr");
        Customer c2 = new Customer("Ahmed");

        Bank_Account s1 =
                new Saving("ACC-001", 2000, 500);

        Bank_Account cA1 =
                new Current_account("ACC-002", 1000, 2000);

        c1.addAccount(s1);
        c2.addAccount(cA1);

        ArrayList<Bank_Account> accounts =
                new ArrayList<>();

        accounts.add(s1);
        accounts.add(cA1);

        // ====================================
        // SAVINGS ACCOUNT TESTS
        // ====================================

        System.out.println("===== SAVINGS TESTS =====");

        // Deposit
        s1.deposit(500);
        s1.deposit(0);
        s1.deposit(-100);

        // Withdraw
        s1.withdraw(0);
        s1.withdraw(-50);
        s1.withdraw(2100);
        s1.withdraw(1500);

        // Transfer
        s1.transfer(600, cA1);
        s1.transfer(200, cA1);

        // Check Balance
        System.out.println(
                "Current Balance = "
                        + s1.check_balance());

        s1.print();

        System.out.println("\n=========================\n");

        // ====================================
        // CURRENT ACCOUNT TESTS
        // ====================================

        System.out.println("===== CURRENT TESTS =====");

        // Deposit
        cA1.deposit(1000);
        cA1.deposit(0);
        cA1.deposit(-100);

        // Withdraw
        cA1.withdraw(500);

        // Allowed because of overdraft
        cA1.withdraw(3000);

        // Exactly at overdraft limit
        cA1.withdraw(700);

        // Exceeds overdraft limit
        cA1.withdraw(1);

        // Transfer
        cA1.transfer(300, s1);

        // Should fail
        cA1.transfer(5000, s1);

        System.out.println(
                "Current Balance = "
                        + cA1.check_balance());

        cA1.print();

        System.out.println("\n=========================\n");

        // ====================================
        // ACCOUNT NOT FOUND TEST
        // ====================================

        Bank_Account test =
                findAccount(accounts, "ACC-999");

        if (test == null) {
            System.out.println(
                    "Error: Account number does not exist");
        }

        System.out.println("\n=========================\n");

        // ====================================
        // EXTRA TESTS
        // ====================================

        Bank_Account s2 =
                new Saving("ACC-003", 500, 500);

        System.out.println("===== EXTRA TESTS =====");

        // Exactly minimum balance
        s2.withdraw(1);

        // Transfer from empty account
        s2.transfer(100, s1);

        // Invalid deposit
        s2.deposit(-500);

        // Valid deposit
        s2.deposit(1000);

        // Valid withdraw
        s2.withdraw(500);

        s2.print();
    }

    public static Bank_Account findAccount(
            ArrayList<Bank_Account> accounts,
            String accNo) {

        for (Bank_Account a : accounts) {
            if (a.getAccount_number().equals(accNo)) {
                return a;
            }
        }

        return null;
    }
}