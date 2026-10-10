import java.util.*;

abstract class Question {
    protected String text;
    protected int marks;

    Question(String text, int marks) {
        this.text = text;
        this.marks = marks;
    }

    public String getText() {
        return text;
    }

    public int getMarks() {
        return marks;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    MultipleChoiceQuestion(String text, int marks,
                           String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer != null
                && correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class TrueFalseQuestion extends Question {
    private String correctAnswer;

    TrueFalseQuestion(String text, int marks,
                      String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer != null
                && correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    ShortAnswerQuestion(String text, int marks,
                        String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer != null
                && correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Examination {
    String name;
    List<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

class Attempt {
    private Student student;
    private Examination exam;
    private Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;

        System.out.println(exam.name + " started by "
                + student.name + ".");
    }

    public void answer(Question question, String answer) {
        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        if (!exam.questions.contains(question)) {
            System.out.println("Question does not belong to this exam.");
            return;
        }

        answers.put(question, answer);

        System.out.println("Answer recorded for "
                + question.getText() + ".");
    }

    public void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;

        System.out.println(exam.name + " submitted by "
                + student.name + ".");

        int totalScore = 0;
        int totalMarks = 0;

        for (Question q : exam.questions) {
            String answer = answers.get(q);
            boolean correct = q.evaluate(answer);

            int score = correct ? q.getMarks() : 0;

            totalScore += score;
            totalMarks += q.getMarks();

            System.out.println(q.getText() + ": "
                    + (correct ? "Correct" : "Incorrect")
                    + " (" + score + " points)");
        }

        System.out.println("Total score: "
                + totalScore + "/" + totalMarks + ".");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        Question q1 = new MultipleChoiceQuestion(
                "Question 1", 5, "C");

        Question q2 = new TrueFalseQuestion(
                "Question 2", 5, "False");

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt = new Attempt(student, exam);

        attempt.answer(q1, "C");
        attempt.answer(q2, "True");

        attempt.submit();

        attempt.answer(q1, "A");
    }
}