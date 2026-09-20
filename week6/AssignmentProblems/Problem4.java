class GymMember4 {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember4(
            String memberId,
            int monthlyFee) {

        if (memberId == null ||
            memberId.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid member ID"
            );
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
            "Standard | Sessions: " +
            sessionsAttended
        );
    }
}

class PremiumMember4 extends GymMember4 {

    protected String trainerName;

    public PremiumMember4(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {

        System.out.println(
            "Premium | Trainer: " +
            trainerName +
            " | Sessions: " +
            sessionsAttended
        );
    }

    public String getTrainerName() {
        return trainerName;
    }
}

public class Problem4 {

    public static String batchPrint(
            GymMember4[] members) {

        StringBuilder result =
                new StringBuilder();

        for (GymMember4 member : members) {

            // Polymorphic call
            member.displayInfo();

            if (member instanceof PremiumMember4) {

                PremiumMember4 premium =
                        (PremiumMember4) member;

                result.append(
                    "Premium | Trainer: " +
                    premium.getTrainerName() +
                    " | Sessions: " +
                    premium.getSessionsAttended()
                );

            } else {

                result.append(
                    "Standard | Sessions: " +
                    member.getSessionsAttended()
                );
            }

            result.append(" [Trainer via downcast: ");

            if (member instanceof PremiumMember4) {

                PremiumMember4 premium =
                        (PremiumMember4) member;

                result.append(
                    premium.getTrainerName()
                );
            } else {
                result.append("N/A");
            }

            result.append("] | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember4[] members = {

            new GymMember4(
                "MEM6",
                1000
            ),

            new PremiumMember4(
                "MEM7",
                2000,
                "Coach Riya"
            )
        };

        System.out.println(
            batchPrint(members)
        );
    }
}