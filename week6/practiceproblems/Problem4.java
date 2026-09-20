class LibraryMember4 {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember4(String memberId, int borrowLimit) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println(
            "Library Member: " + memberId
        );
    }
}

class StudentMember4 extends LibraryMember4 {

    private String course;

    public StudentMember4(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void displayInfo() {

        System.out.println(
            "Student: " + memberId +
            " | Course: " + course
        );
    }

    public String getCourse() {
        return course;
    }
}

public class Problem4 {

    public static String batchPrint(
            LibraryMember4[] members) {

        StringBuilder result = new StringBuilder();

        for (LibraryMember4 member : members) {

            if (member == null) {
                continue;
            }

            if (result.length() > 0) {
                result.append("\n");
            }

            if (member instanceof StudentMember4) {

                StudentMember4 student =
                        (StudentMember4) member;

                result.append(
                    "Student: " +
                    student.memberId +
                    " | Course: " +
                    student.getCourse()
                );

            } else {

                result.append(
                    "Library Member: " +
                    member.memberId
                );
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        LibraryMember4[] members = {

            new StudentMember4(
                "STU1", 3, "CSE"
            ),

            new LibraryMember4(
                "LIB1", 4
            ),

            new StudentMember4(
                "STU2", 3, "AIML"
            )
        };

        System.out.println(
            batchPrint(members)
        );
    }
}