class GymMember3 {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    private int[] lateFeeHistory;
    private int lateFeeCount;

    public GymMember3(
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

        lateFeeHistory = new int[10];
        lateFeeCount = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    protected void chargeLateFee(int amount) {

        if (lateFeeCount < 10) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }

    public int[] getLateFeeHistory() {

        int[] copy = new int[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }

    public int getTotalLateFees() {

        int total = 0;

        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
    }
}

class PremiumMember3 extends GymMember3 {

    private String trainerName;

    public PremiumMember3(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {

        super.chargeLateFee(amount / 2);
    }
}

public class Problem3 {

    public static void main(String[] args) {

        PremiumMember3 p =
                new PremiumMember3(
                    "MEM5",
                    2000,
                    "Coach Riya"
                );

        p.chargeLateFee(200);

        System.out.println(
            p.getTotalLateFees()
        );

        int[] history =
                p.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
            p.getLateFeeHistory()[0]
        );
    }
}