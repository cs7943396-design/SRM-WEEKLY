class GymMember5 {

    protected int monthlyFee;

    private final String membershipNumber;

    private int feesPaid;

    private static int nextNumber = 2001;

    private static int membersEnrolled = 0;

    public GymMember5(int monthlyFee) {

        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;

        nextNumber++;

        membershipNumber =
                "GYM-" + (nextNumber - 1);

        membersEnrolled++;
    }

    public void payFee(int amount) {

        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {

        System.out.println(
            "Payment Mode: " + mode
        );

        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public static boolean isValidReferralCode(
            String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'G') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }
}

class GroupClassMember5 extends GymMember5 {

    private String className;

    public GroupClassMember5(
            int monthlyFee,
            String className) {

        super(monthlyFee);
        this.className = className;
    }
}

public class Problem5 {

    public static String processWeeklyCheckIn(
            GymMember5[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (GymMember5 member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof GroupClassMember5) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed +
               " processed | " +
               nullSkipped +
               " null skipped | " +
               groupCount +
               " group | " +
               individualCount +
               " individual";
    }

    public static void main(String[] args) {

        GymMember5 m1 =
                new GymMember5(1000);

        System.out.println(
            m1.getMembershipNumber()
        );

        System.out.println(
            GymMember5.getMembersEnrolled()
        );

        System.out.println(
            GymMember5.isValidReferralCode("G45B")
        );

        System.out.println(
            GymMember5.isValidReferralCode("G4B")
        );

        System.out.println(
            GymMember5.isValidReferralCode("X45B")
        );

        m1.payFee(500);
        m1.payFee(500, "UPI");

        System.out.println(
            m1.getFeesPaid()
        );

        GymMember5[] members = {

            new GroupClassMember5(
                1500,
                "Zumba"
            ),

            null,

            new GymMember5(1000)
        };

        System.out.println(
            processWeeklyCheckIn(members)
        );
    }
}