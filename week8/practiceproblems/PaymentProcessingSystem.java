import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return false;
    }

    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String getName() {
        return "Bank Transfer";
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    private String orderId;
    private Customer customer;
    private Map<Product, Integer> items = new LinkedHashMap<>();
    private String status = "Pending";

    Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;

        System.out.println("Order created for "
                + customer.name + ".");
    }

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public double calculateTotal() {
        double total = 0;

        for (Map.Entry<Product, Integer> item : items.entrySet()) {
            total += item.getKey().price * item.getValue();
        }

        return total;
    }

    public void pay(PaymentMethod method) {
        if (items.isEmpty()) {
            System.out.println(
                    "Cannot process payment for an empty order.");
            return;
        }

        if (status.equals("Paid")) {
            System.out.println("Order is already Paid.");
            return;
        }

        System.out.println("Payment initiated via "
                + method.getName() + " for Order "
                + orderId + ".");

        boolean success = method.processPayment(calculateTotal());

        if (success) {
            status = "Paid";

            System.out.println("Payment for Order "
                    + orderId + " successful.");
        } else {
            System.out.println("Payment for Order "
                    + orderId + " failed.");
        }

        System.out.println("Order status: " + status + ".");
    }
}

public class PaymentProcessingSystem {
    public static void main(String[] args) {
        Customer x = new Customer("Customer X");
        Customer y = new Customer("Customer Y");
        Customer z = new Customer("Customer Z");

        Order orderX = new Order("X", x);

        orderX.addProduct(new Product("Product A", 100), 2);
        orderX.addProduct(new Product("Product B", 200), 1);

        orderX.pay(new CreditCardPayment());

        System.out.println();

        Order orderY = new Order("Y", y);
        orderY.pay(new CreditCardPayment());

        System.out.println();

        Order orderZ = new Order("Z", z);
        orderZ.addProduct(new Product("Product C", 500), 1);

        orderZ.pay(new PayPalPayment());
    }
}