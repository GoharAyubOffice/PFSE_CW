import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hatfield Junior Swimming School!");
        BookSwimmingLesson bookSwimmingLesson = new BookSwimmingLesson();
        Learner learner = new Learner();
        Report report = new Report();

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
            scanner.nextLine(); // Consume newline

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
                case 2:
                    // Change/Cancel a booking
                    Change_Cancel_Booking changeCancelBooking = new Change_Cancel_Booking(bookSwimmingLesson);
                    System.out.println("Change/Cancel a booking:");
                    System.out.print("Enter learner's name: ");
                    String learnerName = scanner.nextLine();
                    System.out.println("1. Change booking");
                    System.out.println("2. Cancel booking");
                    System.out.print("Enter your choice: ");
                    int actionChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    switch (actionChoice) {
                        case 1:
                            changeCancelBooking.changeBooking(learnerName);
                            break;
                        case 2:
                            changeCancelBooking.cancelBooking(learnerName);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                case 3:
                    // Attend a swimming lesson
                    AttendLesson attendLesson = new AttendLesson(bookSwimmingLesson);
                    attendLesson.attendLesson();
                    break;
                case 4:
                    // Generate monthly learner report
                    List<String[]> bookedLessons = bookSwimmingLesson.getBookedLessons();
                    List<String[]> attendedLessons = bookSwimmingLesson.getAttendedLessons();
                    List<String[]> learners = bookSwimmingLesson.getLearners();
                    report.generateLearnerReport(bookedLessons, attendedLessons, learners);
                    break;
                case 5:
                    // Generate monthly coach report
                    report.generateCoachReport();
                    break;
                case 6:
                    // Register a new learner
                    System.out.print("Enter learner's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter learner's age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter learner's gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter learner's emergency contact phone number: ");
                    String emergencyContact = scanner.nextLine();
                    System.out.print("Enter learner's current grade level: ");
                    int gradeLevel = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    learner.registerNewLearner(name, age, gender, emergencyContact, gradeLevel);
                    break;
                case 7:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
