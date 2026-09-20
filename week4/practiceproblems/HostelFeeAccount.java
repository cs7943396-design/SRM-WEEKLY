class feeaccount {
    String name;
    double balance;

    feeaccount(String name) {
        this.name = name;
        this.balance = 0;
    }

    void pay(double amount) {
        balance = balance + amount;
        System.out.println(name + " | Payment: Rs " + amount);
    }
}

class HostelFeeAccount extends feeaccount {

    HostelFeeAccount(String name) {
        super(name);
    }

    @Override
    void pay(double amount) {
        balance = balance + amount;
        System.out.println(name + " | Hostel Payment: Rs " + amount);
    }
}

class PaymentProcessor {

    void processPayment(feeaccount account, double amount) {

        if (account instanceof HostelFeeAccount) {
            account.pay(amount);
        } else {
            account.pay(amount);
        }
    }

    public static void main(String[] args) {

        feeaccount[] accounts = {
            new HostelFeeAccount("Hostel-1"),
            new HostelFeeAccount("Hostel-2"),
            new feeaccount("FeeAccount-1"),
            new feeaccount("FeeAccount-2")
        };

        double amount = 60000;

        int hostelCount = 0;
        int feeAccountCount = 0;

        PaymentProcessor processor = new PaymentProcessor();

        for (feeaccount account : accounts) {

            processor.processPayment(account, amount);

            if (account instanceof HostelFeeAccount) {
                hostelCount++;
            } else {
                feeAccountCount++;
            }
        }

        System.out.println("Hostel accounts processed: " + hostelCount);
        System.out.println("Fee accounts processed: " + feeAccountCount);
    }
}