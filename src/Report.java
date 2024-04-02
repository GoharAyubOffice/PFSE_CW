import java.util.List;
import java.util.Scanner;
public class Report {
    private BookSwimmingLesson bookSwimmingLesson;

    // Constructor
    public Report(BookSwimmingLesson bookSwimmingLesson) {
        this.bookSwimmingLesson = bookSwimmingLesson;
    }

    // Method to generate monthly learner report
    public void generateMonthlyLearnerReport(Scanner scanner) {
        System.out.println("Enter the month number (e.g., 03 for March): ");
        int month = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Iterate through all learners
        for (String[] learner : bookSwimmingLesson.getLearners()) {
            String learnerName = learner[0];
            System.out.println("Learner: " + learnerName);

            // Get booked lessons for the learner
            List<String[]> bookedLessons = bookSwimmingLesson.getBookedLessonsByLearner(learnerName);

            // Output booked lessons for the month
            System.out.println("Booked Lessons:");
            int bookedCount = 0;
            for (String[] lesson : bookedLessons) {
                // Check if lesson is in the specified month
                // Implement date comparison logic here based on your data structure
                // For example, if lesson date is stored as "MM/DD/YYYY"
                String lessonDate = lesson[0];
                int lessonMonth = Integer.parseInt(lessonDate.split("/")[0]);
                if (lessonMonth == month) {
                    System.out.println("Lesson: " + lesson[1] + ", Grade: " + lesson[2] + ", Coach: " + lesson[3]);
                    bookedCount++;
                }
            }
            System.out.println("Total Booked Lessons: " + bookedCount);
            System.out.println(); // Add newline for readability
        }
    }
}
