import java.util.Scanner;

public class SwimmingSystemManager {
    private static final int LESSON_CAPACITY = 4;
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String[] GRADES = {"Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5"};
    private static final String[] COACHES = {"Helen", "John", "Alice", "Mike"};

    // Assume this is a timetable with sample data
    private static final String[][][] TIMETABLE = {
            {{"Grade 1", "Helen"}, {"Grade 2", "John"}, {"Grade 3", "Alice"}, {"Grade 4", "Mike"}},
            {{"Grade 2", "Helen"}, {"Grade 3", "John"}, {"Grade 4", "Alice"}, {"Grade 5", "Mike"}},
            {{"Grade 3", "Helen"}, {"Grade 4", "John"}, {"Grade 5", "Alice"}, {"Grade 1", "Mike"}},
            {{"Grade 4", "Helen"}, {"Grade 5", "John"}, {"Grade 1", "Alice"}, {"Grade 2", "Mike"}},
            {{"Grade 5", "Helen"}, {"Grade 1", "John"}, {"Grade 2", "Alice"}, {"Grade 3", "Mike"}},
            {{"Grade 1", "Helen"}, {"Grade 2", "John"}, {"Grade 3", "Alice"}, {"Grade 4", "Mike"}},
            {{"Grade 2", "Helen"}, {"Grade 3", "John"}, {"Grade 4", "Alice"}, {"Grade 5", "Mike"}}
    };

    public static void bookLesson(Scanner scanner) {
        System.out.println("Book a swimming lesson...");
        System.out.println("How would you like to view the timetable?");
        System.out.println("1. By specifying the day");
        System.out.println("2. By specifying the grade level");
        System.out.println("3. By specifying the coach's name");
        System.out.print("Enter your choice: ");
        int viewChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (viewChoice) {
            case 1:
                viewTimetableByDay(scanner);
                break;
            case 2:
                viewTimetableByGrade(scanner);
                break;
            case 3:
                viewTimetableByCoach(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void viewTimetableByDay(Scanner scanner) {
        System.out.println("Select a day:");
        for (int i = 0; i < DAYS.length; i++) {
            System.out.println((i + 1) + ". " + DAYS[i]);
        }
        System.out.print("Enter the day number: ");
        int dayChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (dayChoice < 1 || dayChoice > DAYS.length) {
            System.out.println("Invalid day choice.");
            return;
        }

        String selectedDay = DAYS[dayChoice - 1];
        System.out.println("Timetable for " + selectedDay + ":");
        displayTimetable(selectedDay);
        bookLessonFromTimetable(scanner, selectedDay);
    }

    private static void viewTimetableByGrade(Scanner scanner) {
        System.out.println("Select a grade level:");
        for (int i = 0; i < GRADES.length; i++) {
            System.out.println((i + 1) + ". " + GRADES[i]);
        }
        System.out.print("Enter the grade number: ");
        int gradeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (gradeChoice < 1 || gradeChoice > GRADES.length) {
            System.out.println("Invalid grade choice.");
            return;
        }

        String selectedGrade = GRADES[gradeChoice - 1];
        System.out.println("Timetable for " + selectedGrade + " lessons:");
        displayTimetable(selectedGrade);
        bookLessonFromTimetable(scanner, selectedGrade);
    }

    private static void viewTimetableByCoach(Scanner scanner) {
        System.out.println("Select a coach:");
        for (int i = 0; i < COACHES.length; i++) {
            System.out.println((i + 1) + ". " + COACHES[i]);
        }
        System.out.print("Enter the coach number: ");
        int coachChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (coachChoice < 1 || coachChoice > COACHES.length) {
            System.out.println("Invalid coach choice.");
            return;
        }

        String selectedCoach = COACHES[coachChoice - 1];
        System.out.println("Timetable for lessons taught by " + selectedCoach + ":");
        displayTimetable(selectedCoach);
        bookLessonFromTimetable(scanner, selectedCoach);
    }

    private static void displayTimetable(String filter) {
        for (int i = 0; i < TIMETABLE.length; i++) {
            for (int j = 0; j < TIMETABLE[i].length; j++) {
                if (TIMETABLE[i][j][1].equals(filter)) {
                    System.out.println(TIMETABLE[i][j][0] + " lesson on " + DAYS[i] + " with " + TIMETABLE[i][j][1]);
                }
            }
        }
    }

    private static void bookLessonFromTimetable(Scanner scanner, String filter) {
        System.out.println("Enter the day number and lesson grade to book (e.g., 1 2): ");
        int day = scanner.nextInt();
        int grade = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (day < 1 || day > DAYS.length || grade < 1 || grade > GRADES.length) {
            System.out.println("Invalid day or grade.");
            return;
        }

        // Check if the lesson is available and book if it is
        int dayIndex = day - 1;
        int gradeIndex = grade - 1;
        for (String[] lesson : TIMETABLE[dayIndex]) {
            if (lesson[0].equals(GRADES[gradeIndex])) {
                // Check if lesson is full
                int bookedCount = countBookedLessons(TIMETABLE[dayIndex]);
                if (bookedCount >= LESSON_CAPACITY) {
                    System.out.println("Sorry, the lesson is full. Please choose another lesson.");
                    return;
                }

                System.out.println("Booking successful for " + lesson[0] + " lesson on " + DAYS[dayIndex] + " with " + lesson[1]);
                return;
            }
        }
        System.out.println("No lesson found for the specified grade on the selected day.");
    }
    private static int countBookedLessons(String[][] lessons) {
        int count = 0;
        for (String[] lesson : lessons) {
            count++;
        }
        return count;
    }
}