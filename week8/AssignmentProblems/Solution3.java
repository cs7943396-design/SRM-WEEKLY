interface MembershipPlan {
    int getMonths();
    double calculateFee();
    String getName();
}

class MonthlyPlan implements MembershipPlan {
    public int getMonths() {
        return 1;
    }

    public double calculateFee() {
        return 1000;
    }

    public String getName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {
    public int getMonths() {
        return 3;
    }

    public double calculateFee() {
        return 3000 * 0.90;
    }

    public String getName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {
    public int getMonths() {
        return 12;
    }

    public double calculateFee() {
        return 12000 * 0.75;
    }

    public String getName() {
        return "Annual";
    }
}

class Member {
    private String name;

    Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private String status = "Active";

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
    }

    public String getStatus() {
        return status;
    }

    public void checkIn() {
        if (status.equals("Active")) {
            System.out.println(member.getName()
                    + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: "
                    + member.getName()
                    + "'s membership is " + status + ".");
        }
    }

    public void freeze() {
        if (status.equals("Expired")) {
            System.out.println("Cannot freeze an Expired membership.");
        } else if (status.equals("Frozen")) {
            System.out.println("Membership is already Frozen.");
        } else {
            status = "Frozen";
            System.out.println(member.getName()
                    + "'s membership frozen.");
            System.out.println("Status: " + status + ".");
        }
    }

    public void unfreeze() {
        if (status.equals("Expired")) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(member.getName()
                    + "'s membership unfrozen.");
            System.out.println("Status: " + status + ".");
        } else {
            System.out.println("Membership is already Active.");
        }
    }

    public void expire() {
        status = "Expired";
        System.out.println(member.getName()
                + "'s membership expired.");
        System.out.println("Status: " + status + ".");
    }
}

public class Solution3 {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        MembershipPlan quarterly = new QuarterlyPlan();
        MembershipPlan monthly = new MonthlyPlan();

        Membership m1 = new Membership(asha, quarterly);
        Membership m2 = new Membership(ravi, monthly);

        System.out.println(quarterly.getName()
                + " membership created for Asha.");
        System.out.printf("Fee: ₹%.2f.%n", quarterly.calculateFee());
        System.out.println("Status: " + m1.getStatus() + ".");

        System.out.println();

        System.out.println(monthly.getName()
                + " membership created for Ravi.");
        System.out.printf("Fee: ₹%.2f.%n", monthly.calculateFee());
        System.out.println("Status: " + m2.getStatus() + ".");

        System.out.println();

        m1.checkIn();
        m1.freeze();
        m1.checkIn();

        m2.expire();
        m2.freeze();
    }
}