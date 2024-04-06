import java.util.Scanner;

public class AttendLesson {
    private BookSwimmingLesson swimmingLesson;

    public AttendLesson(BookSwimmingLesson swimmingLesson) {
        this.swimmingLesson = swimmingLesson;
    }

    public void attendLesson() {
        Scanner scanner = new Scanner(System.in);

        // Display booked lessons
        System.out.println("Booked Lessons:");
        for (String[] lesson : swimmingLesson.getBookedLessons()) {
            System.out.println("Day: " + lesson[0] + ", Grade: " + lesson[2] + ", Lesson: " + lesson[1]);
        }

        // Select lesson to attend
        System.out.print("Enter the day of the lesson you want to attend: ");
        String day = scanner.nextLine();
        System.out.print("Enter the grade of the lesson: ");
        String grade = scanner.nextLine();
        System.out.print("Enter the lesson number: ");
        String lessonNumber = scanner.nextLine();

        // Attend the selected lesson
        if (swimmingLesson.isLessonBooked(day, grade, lessonNumber)) {
            System.out.println("You have attended the lesson.");
            // Ask for review and rating
            System.out.print("Write a review of the lesson: ");
            String review = scanner.nextLine();
            System.out.print("Rate the lesson from 1 to 5 (1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied): ");
            int rating = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Thanks for giving Review :)");
            // Store the review and rating
            swimmingLesson.addReview(day, grade, lessonNumber, review, rating);
        } else {
            System.out.println("No lesson found for the given details.");
        }
    }
}
