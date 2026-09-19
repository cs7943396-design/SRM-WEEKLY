public class EmployeeStatic {

    // Instance fields
    String empName;
    double salary;

    // Static field
    static String companyName =
            "Bright Horizon Technologies";

    // Static counter
    static int employeeCount = 0;

    EmployeeStatic(String empName, double salary) {

        this.empName = empName;
        this.salary = salary;

        employeeCount++;
    }

    static void printCompanyInfo() {

        System.out.println(companyName);
        System.out.println(
                "Employees on record: " + employeeCount
        );
    }

    public static void main(String[] args) {

        EmployeeStatic e1 =
                new EmployeeStatic("Ravi", 50000);

        EmployeeStatic e2 =
                new EmployeeStatic("Priya", 60000);

        EmployeeStatic e3 =
                new EmployeeStatic("Arjun", 45000);

        EmployeeStatic.printCompanyInfo();
    }
}