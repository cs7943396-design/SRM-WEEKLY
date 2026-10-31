import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract int calculatePay();

    abstract String getType();

    public String toString() {
        return "Payslip[name=" + name +
                ", type=" + getType() +
                ", pay=" + calculatePay() + "]";
    }
}

class FullTime extends Staff {
    int salary;

    FullTime(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    int calculatePay() {
        return salary;
    }

    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    int hours, rate;

    PartTime(String name, int hours, int rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    int calculatePay() {
        return hours * rate;
    }

    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    int stipend;

    Intern(String name, int stipend) {
        super(name);
        this.stipend = stipend;
    }

    int calculatePay() {
        return stipend;
    }

    String getType() {
        return "Intern";
    }
}

public class payroll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Staff> staffList = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();

            switch (type) {
                case "FullTime":
                    staffList.add(
                            new FullTime(name, sc.nextInt()));
                    break;

                case "PartTime":
                    int hours = sc.nextInt();
                    int rate = sc.nextInt();

                    staffList.add(
                            new PartTime(name, hours, rate));
                    break;

                case "Intern":
                    staffList.add(
                            new Intern(name, sc.nextInt()));
                    break;
            }
        }

        int total = 0;
        int maxPay = -1;
        String topper = "";

        for (Staff s : staffList) {
            System.out.println(s);

            int pay = s.calculatePay();
            total += pay;

            if (pay > maxPay) {
                maxPay = pay;
                topper = s.name;
            }
        }

        System.out.println("Total " + total);
        System.out.println("top earner " + topper);

        sc.close();
    }
}
