class LibraryMember5 {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private final String memberNumber;

    private static int nextNumber = 101;

    public LibraryMember5(
            String memberId,
            int borrowLimit) {

        if (memberId == null ||
            memberId.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid member ID"
            );
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        memberNumber = "LIB-" + nextNumber;
        nextNumber++;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {

        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public static boolean isValidRenewalCode(
            String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'R') {
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

class FacultyMember5 extends LibraryMember5 {

    public FacultyMember5(
            String memberId,
            int borrowLimit) {

        super(memberId, borrowLimit);
    }
}

public class Problem5 {

    public static void processNightlyAudit(
            LibraryMember5[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        for (LibraryMember5 member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember5) {
                facultyCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println(
            processed + " processed | " +
            nullSkipped + " null skipped | " +
            facultyCount + " faculty | " +
            regularCount + " regular"
        );
    }

    public static void main(String[] args) {

        LibraryMember5 member =
                new LibraryMember5("LIB1", 3);

        FacultyMember5 faculty =
                new FacultyMember5("FAC1", 5);

        System.out.println(
            member.getMemberNumber()
        );

        System.out.println(
            LibraryMember5.isValidRenewalCode("R12A")
        );

        System.out.println(
            LibraryMember5.isValidRenewalCode("R1A")
        );

        System.out.println(
            LibraryMember5.isValidRenewalCode("X12A")
        );

        LibraryMember5[] members = {
            member,
            faculty,
            null
        };

        processNightlyAudit(members);
    }
}