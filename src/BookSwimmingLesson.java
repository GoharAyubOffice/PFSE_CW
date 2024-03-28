import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSwimmingLesson {
    private static final String[] GRADE_LEVELS = {"1", "2", "3", "4", "5"};
    private static final String[] DAYS = {"Monday", "Wednesday", "Friday", "Saturday"};
    private static final String[] COACHES = {"CoachA", "CoachB", "CoachC", "CoachD", "CoachE"};

    private List<String[]> bookedLessons; // Stores the booked lessons' data
    private List<String[]> learners; // Stores the learners' data

    private String[][][] timetable = {
            // Monday
            {
                    {"Grade1_Lesson1", "CoachA", "4-5pm"},
                    {"Grade2_Lesson1", "CoachB", "5-6pm"},
                    {"Grade3_Lesson1", "CoachC", "6-7pm"},
                    {"Grade4_Lesson1", "CoachD", "4-5pm"},
                    {"Grade5_Lesson1", "CoachE", "5-6pm"}
            },
            // Wednesday
            {
                    {"Grade1_Lesson2", "CoachA", "4-5pm"},
                    {"Grade2_Lesson2", "CoachB", "5-6pm"},
                    {"Grade3_Lesson2", "CoachC", "6-7pm"},
                    {"Grade4_Lesson2", "CoachD", "4-5pm"},
                    {"Grade5_Lesson2", "CoachE", "5-6pm"}
            },
            // Friday
            {
                    {"Grade1_Lesson3", "CoachA", "4-5pm"},
                    {"Grade2_Lesson3", "CoachB", "5-6pm"},
                    {"Grade3_Lesson3", "CoachC", "6-7pm"},
                    {"Grade4_Lesson3", "CoachD", "4-5pm"},
                    {"Grade5_Lesson3", "CoachE", "5-6pm"}
            },
            // Saturday
            {
                    {"Grade1_Lesson4", "CoachA", "2-3pm"},
                    {"Grade2_Lesson4", "CoachB", "3-4pm"},
                    {"Grade3_Lesson4", "CoachC", "2-3pm"},
                    {"Grade4_Lesson4", "CoachD", "3-4pm"},
                    {"Grade5_Lesson4", "CoachE", "2-3pm"}
            }
    };

    public BookSwimmingLesson() {
        bookedLessons = new ArrayList<>();
        learners = new ArrayList<>();
    }

    public List<String[]> getBookedLessons() {
        return bookedLessons;
    }

    public void viewTimetableCategories(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Select how to view the timetable:");
                System.out.println("1. By day");
                System.out.println("2. By grade level");
                System.out.println("3. By coach's name");
                break;
        }
    }

    public void displayTimetableByDay(String day) {
        int dayIndex = getDayIndex(day);
        if (dayIndex != -1) {
            System.out.println("Timetable for " + day + ":");
            for (String[] lesson : timetable[dayIndex]) {
                System.out.println("Lesson: " + lesson[0] + ", Coach: " + lesson[1] + ", Time: " + lesson[2]);
            }
            bookLesson(day);
        } else {
            System.out.println("Invalid day.");
        }
    }

    public void displayTimetableByGrade(String grade) {
        System.out.println("Timetable for Grade " + grade + ":");
        for (int i = 0; i < timetable.length; i++) {
            for (String[] lesson : timetable[i]) {
                if (lesson[0].startsWith("Grade" + grade)) {
                    System.out.println("Day: " + DAYS[i] + ", Lesson: " + lesson[0] + ", Coach: " + lesson[1] + ", Time: " + lesson[2]);
                }
            }
        }
        bookLessonByGrade(grade);
    }

    public void displayTimetableByCoach(String coach) {
        System.out.println("Timetable for Coach " + coach + ":");
        for (int i = 0; i < timetable.length; i++) {
            for (String[] lesson : timetable[i]) {
                if (lesson[1].equals(coach)) {
                    System.out.println("Day: " + DAYS[i] + ", Lesson: " + lesson[0] + ", Grade: " + getGrade(lesson[0]) + ", Time: " + lesson[2]);
                }
            }
        }
        bookLessonByCoach(coach);
    }

    private void bookLesson(String day) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the lesson number to book: ");
        int lessonNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (lessonNumber < 1 || lessonNumber > 5) {
            System.out.println("Invalid lesson number.");
            return;
        }

        System.out.print("Enter the grade: ");
        String grade = scanner.nextLine();
        if (!isValidGrade(grade)) {
            System.out.println("Invalid grade.");
            return;
        }

        System.out.print("Enter learner's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter learner's gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter learner's age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (!isValidAge(age)) {
            System.out.println("Invalid age.");
            return;
        }

        System.out.print("Enter learner's emergency contact phone number: ");
        String emergencyContact = scanner.nextLine();

        String[] lessonData = new String[7];
        lessonData[0] = day;
        lessonData[1] = Integer.toString(lessonNumber);
        lessonData[2] = grade;
        lessonData[3] = name;
        lessonData[4] = gender;
        lessonData[5] = Integer.toString(age);
        lessonData[6] = emergencyContact;

        bookedLessons.add(lessonData);

        String[] learnerData = {name, gender, Integer.toString(age), emergencyContact, grade};
        learners.add(learnerData);

        System.out.println("Lesson booked successfully.");
    }

    private void bookLessonByGrade(String grade) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the day to book a lesson for Grade " + grade + ":");
        String day = scanner.nextLine();
        displayTimetableByDay(day);
    }

    private void bookLessonByCoach(String coach) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the day to book a lesson with Coach " + coach + ":");
        String day = scanner.nextLine();
        displayTimetableByDay(day);
    }


    private boolean isValidGrade(String grade) {
        for (String level : GRADE_LEVELS) {
            if (level.equals(grade)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidAge(int age) {
        return age >= 4 && age <= 11;
    }

    private int getDayIndex(String day) {
        for (int i = 0; i < DAYS.length; i++) {
            if (DAYS[i].equalsIgnoreCase(day)) {
                return i;
            }
        }
        return -1;
    }

    private String getGrade(String lesson) {
        return lesson.substring(5, 6); // Extracting grade from lesson name (e.g., "Grade1_Lesson1")
    }
}
