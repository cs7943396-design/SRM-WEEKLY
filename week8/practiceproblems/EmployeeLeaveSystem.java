import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Employee {
    protected String name;

    Employee(String name) {
        this.name = name;
    }

    public abstract boolean isLeaveAllowed(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 15;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 5;
    }
}

class LeaveRequest {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status = "Pending";

    LeaveRequest(Employee employee,
                 LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void submit() {
        if (endDate.isBefore(startDate)) {
            System.out.println("Invalid leave dates.");
            return;
        }

        int days = (int) ChronoUnit.DAYS.between(
                startDate, endDate) + 1;

        if (!employee.isLeaveAllowed(days)) {
            System.out.println("Leave policy limit exceeded.");
            return;
        }

        System.out.println("Leave request submitted for "
                + employee.name + " (" + startDate
                + " to " + endDate + ").");

        System.out.println("Status: " + status + ".");
    }

    public void review(String reviewer, boolean approved) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot review a request with status "
                    + status + ".");
            return;
        }

        status = approved ? "Approved" : "Rejected";

        System.out.println(employee.name
                + "'s leave request (" + startDate
                + " to " + endDate + ") "
                + status.toLowerCase() + " by " + reviewer + ".");

        System.out.println("Status: " + status + ".");
    }

    public void changeStatus(String newStatus) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                    + status + " to " + newStatus + ".");
        } else {
            System.out.println(
                    "Only the reviewer can approve or reject a pending request.");
        }
    }
}

public class EmployeeLeaveSystem {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 = new LeaveRequest(
                john,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5));

        LeaveRequest r2 = new LeaveRequest(
                jane,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11));

        r1.submit();
        r1.review("Alice", true);

        r2.submit();
        r2.review("Bob", false);

        r1.changeStatus("Pending");
    }
}