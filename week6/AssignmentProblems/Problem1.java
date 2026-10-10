class GymMember1 {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember1(String memberId, int monthlyFee) {

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

    public static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {

            try {
                new GymMember1(id, monthlyFee);
                signedUp++;
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp +
               " | Rejected: " + rejected;
    }
}

class PremiumMember1 extends GymMember1 {

    private String trainerName;

    public PremiumMember1(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }
}

public class Problem1 {

    public static void main(String[] args) {

        PremiumMember1 p =
                new PremiumMember1(
                    "MEM01",
                    2000,
                    "Coach Riya"
                );

        p.attendSession();
        p.attendSession();

        System.out.println(
            p.getSessionsAttended()
        );

        String[] ids = {
            "MEM1",
            "GM1",
            "MEM2",
            " ",
            "MEM3"
        };

        System.out.println(
            GymMember1.signUpBatch(ids, 1000)
        );
    }
}