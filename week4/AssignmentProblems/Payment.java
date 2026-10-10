class Payment {

    void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

class CardPayment extends Payment {

    void payWithProcessingFee(double amount) {

        double total = amount + (amount * 0.02);

        System.out.println(
            "Charged (card, incl. fee): Rs " + total
        );
    }
}

class PaymentProcessor {

    void processTransaction(Payment payment, double amount) {

        if (payment instanceof CardPayment) {

            CardPayment card =
                (CardPayment) payment;

            card.payWithProcessingFee(amount);

        } else {

            payment.pay(amount);
        }
    }

    public static void main(String[] args) {

        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {
            100, 50, 200, 75, 120
        };

        double totalCollected = 0;

        PaymentProcessor processor =
            new PaymentProcessor();

        for (int i = 0; i < payments.length; i++) {

            processor.processTransaction(
                payments[i], amounts[i]
            );

            // Calculate actual amount collected
            if (payments[i] instanceof CardPayment) {
                totalCollected =
                    totalCollected + amounts[i] * 1.02;
            } else {
                totalCollected =
                    totalCollected + amounts[i];
            }
        }

        System.out.println(
            "Total Collected: Rs " + totalCollected
        );
    }
}