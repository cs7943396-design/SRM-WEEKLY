class LibraryMember2 {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember2(String memberId, int borrowLimit) {

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
        System.out.println("Library Member: " + memberId);
    }
}

class StudentMember2 extends LibraryMember2 {

    protected String course;

    public StudentMember2(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Student: " + memberId + " | Course: " + course
        );
    }

    public String getCourse() {
        return course;
    }
}

class HonorsStudentMember2 extends StudentMember2 {

    public HonorsStudentMember2(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit, course);
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Honors Student: " + memberId +
            " | Course: " + course
        );
    }
}

class FacultyMember2 extends LibraryMember2 {

    public FacultyMember2(String memberId, int borrowLimit) {
        super(memberId, borrowLimit);
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Faculty: " + memberId
        );
    }
}

public class Problem2 {

    public static String classifyGeneration(
            LibraryMember2 member) {

        if (member instanceof HonorsStudentMember2) {
            return "Honors Student";
        }

        if (member instanceof StudentMember2) {
            return "Student";
        }

        if (member instanceof FacultyMember2) {
            return "Faculty";
        }

        return "Library Member";
    }

    public static int getTotalBooksBorrowed(
            LibraryMember2[] members) {

        int total = 0;

        for (LibraryMember2 member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    public static void main(String[] args) {

        StudentMember2 student =
                new StudentMember2("STU1", 3, "CSE");

        HonorsStudentMember2 honors =
                new HonorsStudentMember2("HON1", 4, "AIML");

        FacultyMember2 faculty =
                new FacultyMember2("FAC1", 5);

        student.borrowBook();
        student.borrowBook();

        honors.borrowBook();
        honors.borrowBook();
        honors.borrowBook();

        faculty.borrowBook();

        System.out.println(
            classifyGeneration(student)
        );

        System.out.println(
            classifyGeneration(honors)
        );

        System.out.println(
            classifyGeneration(faculty)
        );

        LibraryMember2[] members = {
            student,
            honors,
            faculty
        };

        System.out.println(
            "Total books borrowed: " +
            getTotalBooksBorrowed(members)
        );
    }
}