abstract class Vehicle {
    protected String id;
    private boolean available = true;

    Vehicle(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnVehicle() {
        available = true;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    SUV(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    Truck(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private boolean active = true;

    Rental(Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
    }

    public void returnVehicle() {
        if (active) {
            vehicle.returnVehicle();
            active = false;

            System.out.println(vehicle.getId()
                    + " returned by " + customer.name + ".");
        }
    }
}

class RentalService {
    public Rental rentVehicle(Customer customer,
                              Vehicle vehicle, int days) {
        if (days <= 0) {
            System.out.println("Invalid rental duration.");
            return null;
        }

        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getId()
                    + " is currently unavailable.");
            return null;
        }

        vehicle.rent();

        Rental rental = new Rental(customer, vehicle);

        System.out.println(vehicle.getClass().getSimpleName()
                + " " + vehicle.getId()
                + " rented successfully by " + customer.name + ".");

        System.out.printf("Rental charge: $%.2f.%n",
                vehicle.calculateCharge(days));

        return rental;
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedan = new Sedan("A");
        Vehicle suv = new SUV("B");

        RentalService service = new RentalService();

        Rental r1 = service.rentVehicle(c1, sedan, 3);

        service.rentVehicle(c2, sedan, 2);

        if (r1 != null) {
            r1.returnVehicle();
        }

        service.rentVehicle(c3, suv, 5);
    }
}