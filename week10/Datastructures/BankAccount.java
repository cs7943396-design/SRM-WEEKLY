import java.util.*;

abstract class Account {
    String id;
    double balance;

    Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract void withdraw(double amount);
}

class Savings extends Account {
    Savings(String id, double balance) {
        super(id, balance);
    }

    void withdraw(double amount) {
        if (balance - amount < 1000) {
            System.out.println(id + " rejected: minimum balance 1000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + (int) balance);
        }
    }
}

class Current extends Account {
    Current(String id, double balance) {
        super(id, balance);
    }

    void withdraw(double amount) {
        if (balance - amount < -5000) {
            System.out.println(id + " rejected: overdraft limit 5000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + (int) balance);
        }
    }
}

public class BankAccount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("Savings")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new Savings(id, balance));

            } else if (operation.equals("Current")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new Current(id, balance));

            } else if (operation.equals("WITHDRAW")) {
                String id = sc.next();
                double amount = sc.nextDouble();

                if (!accounts.containsKey(id)) {
                    System.out.println("Account not found");
                } else {
                    accounts.get(id).withdraw(amount);
                }
            }
        }
        sc.close();
    }
}
