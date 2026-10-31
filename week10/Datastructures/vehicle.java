import java.util.*;

interface Chargeable {
    void bookChargingBay();
}

abstract class Vehicle {
    String passNumber;
    String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int getPassFee();

    void display() {
        System.out.println(passNumber + " (" +
                getClass().getSimpleName() +
                ") pass fee " + getPassFee());
    }
}

class Bike extends Vehicle {
    Bike(String pass, String owner) {
        super(pass, owner);
    }

    int getPassFee() {
        return 300;
    }
}

class Car extends Vehicle {
    Car(String pass, String owner) {
        super(pass, owner);
    }

    int getPassFee() {
        return 1000;
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String pass, String owner) {
        super(pass, owner);
    }

    int getPassFee() {
        return 300;
    }

    public void bookChargingBay() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String pass, String owner) {
        super(pass, owner);
    }

    int getPassFee() {
        return 1000;
    }

    public void bookChargingBay() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class vehicle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String pass = sc.next();
                String owner = sc.next();

                Vehicle v = null;

                switch (type) {
                    case "Bike":
                        v = new Bike(pass, owner);
                        break;
                    case "Car":
                        v = new Car(pass, owner);
                        break;
                    case "EBike":
                        v = new EBike(pass, owner);
                        break;
                    case "ECar":
                        v = new ECar(pass, owner);
                        break;
                }

                if (v != null) {
                    vehicles.put(pass, v);
                    v.display();
                }

            } else if (operation.equals("CHARGE")) {
                String pass = sc.next();

                Vehicle v = vehicles.get(pass);

                if (v == null) {
                    System.out.println("Vehicle not found");
                } else if (v instanceof Chargeable) {
                    ((Chargeable) v).bookChargingBay();
                } else {
                    System.out.println(pass +
                            " rejected: charging unsupported");
                }
            }
        }
        sc.close();
    }
}
