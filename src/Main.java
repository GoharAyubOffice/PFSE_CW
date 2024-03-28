import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hatfield Junior Swimming School!");
        BookSwimmingLesson bookSwimmingLesson = new BookSwimmingLesson();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book a swimming lesson");
            System.out.println("2. Change/Cancel a booking");
            System.out.println("3. Attend a swimming lesson");
            System.out.println("4. Monthly learner report");
            System.out.println("5. Monthly coach report");
            System.out.println("6. Register a new learner");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    bookSwimmingLesson.viewTimetableCategories(1);
                    System.out.print("Enter your choice: ");
                    int timetableChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (timetableChoice) {
                        case 1:
                            System.out.print("Enter the day: ");
                            String day = scanner.nextLine();
                            bookSwimmingLesson.displayTimetableByDay(day);
                            break;
                        case 2:
                            System.out.print("Enter the grade: ");
                            String grade = scanner.nextLine();
                            bookSwimmingLesson.displayTimetableByGrade(grade);
                            break;
                        case 3:
                            System.out.print("Enter the coach's name: ");
                            String coach = scanner.nextLine();
                            bookSwimmingLesson.displayTimetableByCoach(coach);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Attend a swimming lesson:");
                    System.out.print("Enter your name: ");
                    String learnerName = scanner.nextLine();
                    System.out.print("Enter the day of the lesson you want to attend: ");
                    String day = scanner.nextLine();
                    System.out.print("Enter the grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("Enter the lesson number: ");
                    String lessonNumber = scanner.nextLine();
                    System.out.print("Write a review of the lesson: ");
                    String review = scanner.nextLine();
                    System.out.print("Enter a rating for the lesson (1 to 5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    bookSwimmingLesson.addReview(day, grade, lessonNumber, review, rating);
                    System.out.println("Thank you for attending the lesson!");
                    break;

                case 7:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
