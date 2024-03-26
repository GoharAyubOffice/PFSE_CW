import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Swimming Lesson Manager!");
        System.out.println("Please select an option:");
        System.out.println("1. Book a swimming lesson");
        System.out.println("2. Change/Cancel a booking");
        System.out.println("3. Attend a swimming lesson");
        System.out.println("4. Monthly learner report");
        System.out.println("5. Monthly coach report");
        System.out.println("6. Register a new learner");
        System.out.println("7. Exit");

        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    SwimmingSystemManager.bookLesson(scanner);
                    break;
                case 2:
                    ChangeCancelBooking.changeOrCancelBooking(scanner);
                    break;
                case 3:
//                    AttendLesson.attendLesson();
                    break;
                case 4:
//                    MonthlyReport.monthlyLearnerReport();
                    break;
                case 5:
//                    MonthlyReport.monthlyCoachReport();
                    break;
                case 6:
//                    RegisterLearner.registerNewLearner();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
