import java.util.*;

public class Marksgrid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNext()) {
            String name = sc.next();

            String line = sc.nextLine().trim();
            line = line.replace("[", "").replace("]", "");

            String[] values = line.split(",");

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(values[i].trim());
            }

            names.add(name);
            marksList.add(marks);
        }

        int n = names.size();
        int[][] marks = new int[n][3];

        int[] subjectTotal = new int[3];

        int highest = -1;
        String topper = "";

        System.out.print("Totals ");

        for (int i = 0; i < n; i++) {
            marks[i] = marksList.get(i);

            int total = 0;

            for (int j = 0; j < 3; j++) {
                total += marks[i][j];
                subjectTotal[j] += marks[i][j];
            }

            System.out.print(names.get(i) + " " + total);

            if (i < n - 1) System.out.print(", ");

            if (total > highest) {
                highest = total;
                topper = names.get(i);
            }
        }

        System.out.println();

        System.out.printf(
                "averages %.2f, %.2f, %.2f%n",
                subjectTotal[0] / (double) n,
                subjectTotal[1] / (double) n,
                subjectTotal[2] / (double) n
        );

        System.out.println("topper " + topper +
                " (" + highest + ")");

        sc.close();
    }
}
