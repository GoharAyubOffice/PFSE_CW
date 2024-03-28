import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hatfield Junior Swimming School!");
        BookSwimmingLesson bookSwimmingLesson = new BookSwimmingLesson();
        Change_Cancel_Booking changeCancelBooking = new Change_Cancel_Booking(bookSwimmingLesson);

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
            scanner.nextLine();

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
                    System.out.println("Change/Cancel a booking:");
                    System.out.println("1. Cancel booking");
                    System.out.println("2. Change booking");
                    System.out.print("Enter your choice: ");
                    int changeCancelChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (changeCancelChoice) {
                        case 1:
                            System.out.print("Enter learner's name to cancel booking: ");
                            String cancelName = scanner.nextLine();
                            changeCancelBooking.cancelBooking(cancelName);
                            break;
                        case 2:
                            System.out.print("Enter learner's name to change booking: ");
                            String changeName = scanner.nextLine();
                            changeCancelBooking.changeBooking(changeName);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
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
