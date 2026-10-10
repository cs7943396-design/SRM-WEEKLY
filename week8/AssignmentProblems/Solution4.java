import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[Email → " + student.getName()
                + "] " + notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[SMS → " + student.getName()
                + "] " + notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[App → " + student.getName()
                + "] " + notice.getTitle());
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> channels;

    Student(String name, String department,
            List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.channels = new ArrayList<>(channels);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {
    private String title;
    private Set<String> departments;

    Notice(String title, Set<String> departments) {
        this.title = title;
        this.departments = new HashSet<>(departments);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getDepartments() {
        return departments;
    }

    public boolean isValid() {
        return title != null
                && !title.trim().isEmpty()
                && !departments.isEmpty();
    }
}

class NoticeBoard {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println(
                    "Cannot post notice: At least one target department is required."
            );
            return;
        }

        System.out.print("Notice '" + notice.getTitle()
                + "' posted to ");

        int count = 0;

        for (String department : notice.getDepartments()) {
            if (count > 0) {
                System.out.print(", ");
            }

            System.out.print(department);
            count++;
        }

        System.out.println(".");

        for (Student student : students) {
            if (notice.getDepartments()
                    .contains(student.getDepartment())) {

                for (NotificationChannel channel
                        : student.getChannels()) {
                    channel.send(student, notice);
                }
            }
        }
    }
}

public class Solution4 {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student(
                "Asha", "CSE",
                Arrays.asList(
                        new EmailChannel(),
                        new AppChannel()
                )
        );

        Student ravi = new Student(
                "Ravi", "ECE",
                Arrays.asList(new SmsChannel())
        );

        board.addStudent(asha);
        board.addStudent(ravi);

        Notice n1 = new Notice(
                "Lab Closed Tomorrow",
                new HashSet<>(Arrays.asList("CSE"))
        );

        Notice n2 = new Notice(
                "Fee Deadline Extended",
                new HashSet<>(Arrays.asList("CSE", "ECE"))
        );

        Notice n3 = new Notice(
                "Sports Day",
                new HashSet<>()
        );

        board.postNotice(n1);
        board.postNotice(n2);
        board.postNotice(n3);
    }
}