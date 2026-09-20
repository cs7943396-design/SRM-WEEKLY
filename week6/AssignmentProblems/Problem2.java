class GymMember2 {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember2(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println(
            "Standard Member | Sessions: " +
            sessionsAttended
        );
    }
}

class PremiumMember2 extends GymMember2 {

    protected String trainerName;

    public PremiumMember2(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Premium Member | Trainer: " +
            trainerName +
            " | Sessions: " +
            sessionsAttended
        );
    }

    public String getTrainerName() {
        return trainerName;
    }
}

class EliteMember2 extends PremiumMember2 {

    private String lockerNumber;

    public EliteMember2(
            String memberId,
            int monthlyFee,
            String trainerName,
            String lockerNumber) {

        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Elite Member | Trainer: " +
            trainerName +
            " | Locker: " +
            lockerNumber +
            " | Sessions: " +
            sessionsAttended
        );
    }
}

class GroupClassMember2 extends GymMember2 {

    private String className;

    public GroupClassMember2(
            String memberId,
            int monthlyFee,
            String className) {

        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Group Class Member | Class: " +
            className +
            " | Sessions: " +
            sessionsAttended
        );
    }
}

public class Problem2 {

    public static String classifyGeneration(
            GymMember2 member) {

        if (member instanceof EliteMember2) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember2) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof PremiumMember2) {
            return "Second generation";
        }

        return "Base member";
    }

    public static int getTotalSessionsAttended(
            GymMember2[] members) {

        int total = 0;

        for (GymMember2 member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }

    public static void main(String[] args) {

        GymMember2 member =
                new GymMember2("MEM1", 1000);

        PremiumMember2 premium =
                new PremiumMember2(
                    "MEM2",
                    2000,
                    "Coach Riya"
                );

        EliteMember2 elite =
                new EliteMember2(
                    "MEM3",
                    3000,
                    "Coach Arjun",
                    "L12"
                );

        GroupClassMember2 group =
                new GroupClassMember2(
                    "MEM4",
                    1500,
                    "Zumba"
                );

        member.displayInfo();
        premium.displayInfo();
        elite.displayInfo();
        group.displayInfo();

        System.out.println(
            classifyGeneration(elite)
        );

        System.out.println(
            classifyGeneration(group)
        );

        premium.attendSession();
        premium.attendSession();
        premium.attendSession();

        elite.attendSession();
        elite.attendSession();

        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();

        GymMember2[] members = {
            premium,
            elite,
            group
        };

        System.out.println(
            getTotalSessionsAttended(members)
        );
    }
}