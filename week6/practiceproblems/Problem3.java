class LibraryMember3 {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory;
    private int fineCount;

    public LibraryMember3(String memberId, int borrowLimit) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        fineHistory = new int[100];
        fineCount = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    protected void chargeFine(int amount) {

        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    public int[] getFineHistory() {

        int[] copy = new int[fineCount];

        for (int i = 0; i < fineCount; i++) {
            copy[i] = fineHistory[i];
        }

        return copy;
    }

    public int getTotalFine() {

        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }
}

class StudentMember3 extends LibraryMember3 {

    public StudentMember3(
            String memberId,
            int borrowLimit) {

        super(memberId, borrowLimit);
    }

    @Override
    protected void chargeFine(int amount) {

        super.chargeFine(amount / 2);
    }
}

public class Problem3 {

    public static void main(String[] args) {

        StudentMember3 student =
                new StudentMember3("STU10", 3);

        student.chargeFine(100);

        System.out.println(
            "Fine history: " +
            student.getFineHistory()[0]
        );

        System.out.println(
            "Total fine: " +
            student.getTotalFine()
        );

        int[] history =
                student.getFineHistory();

        history[0] = 999;

        System.out.println(
            "After tampering: " +
            student.getFineHistory()[0]
        );
    }
}