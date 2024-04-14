import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSwimmingLesson {
    private static final String[] GRADE_LEVELS = {"1", "2", "3", "4", "5"};
    private static final String[] DAYS = {"Monday", "Wednesday", "Friday", "Saturday"};
    private static final String[] COACHES = {"CoachA", "CoachB", "CoachC", "CoachD", "CoachE"};

    private static final int LESSON_VACANCY_LIMIT = 4;

    private List<String[]> bookedLessons;
    private List<String[]> attendedLessons;
    private List<String[]> learners;
    private List<String[]> lessonReviews;

    private String[][][][] timetable;
    private int[][][] vacancies;

    public List<String[]> getBookedLessons() {
        return bookedLessons;
    }
    public List<String[]> getAttendedLessons() {
        return attendedLessons;
    }
    public List<String[]> getLessonReviews() {return lessonReviews;
    }

    public BookSwimmingLesson() {
        bookedLessons = new ArrayList<>();
        attendedLessons = new ArrayList<>();
        learners = new ArrayList<>();
        lessonReviews = new ArrayList<>();
        initializeTimetable();
        initializeVacancies();
        initializeLearners();
    }

    private void initializeTimetable() {
        timetable = new String[4][DAYS.length][][];
        for (int week = 0; week < 4; week++) {
            for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                timetable[week][dayIndex] = new String[5][];
                for (int lessonIndex = 0; lessonIndex < 5; lessonIndex++) {
                    String coach = COACHES[lessonIndex];
                    String time = "4-5pm";
                    String date = "2024-04-0" + (week * 7 + dayIndex + 1);
                    String lessonName = "Grade" + (dayIndex + 1) + "_Lesson" + lessonIndex;
                    timetable[week][dayIndex][lessonIndex] = new String[]{date, lessonName, coach, time};
                }
            }
        }
    }

    private void initializeVacancies() {
        vacancies = new int[4][DAYS.length][5];
        for (int week = 0; week < 4; week++) {
            for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                for (int lessonIndex = 0; lessonIndex < 5; lessonIndex++) {
                    vacancies[week][dayIndex][lessonIndex] = LESSON_VACANCY_LIMIT;
                }
            }
        }
    }

    private void initializeLearners() {
        for (int i = 0; i < 10; i++) {
            String name = "Learner" + (i + 1);
            String gender = (i % 2 == 0) ? "Male" : "Female";
            int age = 6 + (i % 4);
            String emergencyContact = "123456789" + i;
            String grade = GRADE_LEVELS[i % GRADE_LEVELS.length];
            String[] learnerData = {name, gender, Integer.toString(age), emergencyContact, grade};
            learners.add(learnerData);
            for (int week = 0; week < 4; week++) {
                for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                    for (int lessonIndex = 0; lessonIndex < 5; lessonIndex++) {
                        if (Math.random() < 0.3) {
                            String[] lessonData = timetable[week][dayIndex][lessonIndex];
                            bookLesson(name, lessonData[0], lessonData[1], lessonData[2], lessonData[3], week, dayIndex, lessonIndex);
                        }
                    }
                }
            }
        }
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
            for (int week = 0; week < 4; week++) {
                System.out.println("Week " + (week + 1) + ":");
                for (String[] lesson : timetable[week][dayIndex]) {
                    int vacancies = getVacancies(week, dayIndex, lesson[1]);
                    System.out.println("Date: " + lesson[0] + ", Lesson: " + lesson[1] + ", Coach: " + lesson[2] + ", Time: " + lesson[3] + ", Vacancies: " + vacancies);
                }
            }
            bookLessonByDay(day);
        } else {
            System.out.println("Invalid day.");
        }
    }

    public void displayTimetableByGrade(String grade) {
        System.out.println("Timetable for Grade " + grade + ":");
        int currentGrade = Integer.parseInt(grade);
        for (int week = 0; week < 4; week++) {
            for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                for (String[] lesson : timetable[week][dayIndex]) {
                    int lessonGrade = Integer.parseInt(lesson[1].substring(5, 6));
                    if (lessonGrade == currentGrade || lessonGrade == currentGrade + 1) {
                        int vacancies = getVacancies(week, dayIndex, lesson[1]);
                        System.out.println("Date: " + lesson[0] + ", Lesson: " + lesson[1] + ", Coach: " + lesson[2] + ", Time: " + lesson[3] + ", Vacancies: " + vacancies);
                    }
                }
            }
        }
        bookLessonByGrade(grade);
    }



    public void displayTimetableByCoach(String coach) {
        System.out.println("Timetable for Coach " + coach + ":");
        for (int week = 0; week < 4; week++) {
            for (int dayIndex = 0; dayIndex < DAYS.length; dayIndex++) {
                for (String[] lesson : timetable[week][dayIndex]) {
                    if (lesson[2].equals(coach)) {
                        int vacancies = getVacancies(week, dayIndex, lesson[1]);
                        System.out.println("Date: " + lesson[0] + ", Lesson: " + lesson[1] + ", Grade: " + getGrade(lesson[1]) + ", Time: " + lesson[3] + ", Vacancies: " + vacancies);
                    }
                }
            }
        }
        bookLessonByCoach(coach);
    }

    public void addReview(String day, String grade, String lessonNumber, String review, int rating) {
        String[] reviewData = {day, grade, lessonNumber, review, String.valueOf(rating)};
        lessonReviews.add(reviewData);
    }


    private void bookLesson(String name, String date, String lesson, String coach, String time, int week, int dayIndex, int lessonIndex) {
        String grade = lesson.substring(5, 6);
        String[] lessonData = {date, lesson, grade, name, coach, time}; // Create array of strings
        bookedLessons.add(lessonData); // Add array of strings to bookedLessons
        vacancies[week][dayIndex][lessonIndex]--;
    }

    private void bookLessonByDay(String day) {
        Scanner scanner = new Scanner(System.in);
        int dayIndex = getDayIndex(day);
        if (dayIndex == -1) {
            System.out.println("Invalid day.");
            return;
        }

        System.out.print("Enter the week number: ");
        int week = scanner.nextInt();
        week--; // Adjusting week number to array index

        if (week < 0 || week >= 4) {
            System.out.println("Invalid week number.");
            return;
        }

        System.out.println("Timetable for " + day + " (Week " + (week + 1) + "):");
        System.out.println("-------------------------------------------");
        for (String[] lesson : timetable[week][dayIndex]) {
            int vacancies = getVacancies(week, dayIndex, lesson[1]);
            System.out.println("Date: " + lesson[0] + ", Lesson: " + lesson[1] + ", Coach: " + lesson[2] + ", Time: " + lesson[3] + ", Vacancies: " + vacancies);
        }

        System.out.print("Enter the lesson number to book: ");
        int lessonNumber = scanner.nextInt();
        if (lessonNumber < 1 || lessonNumber > 5) {
            System.out.println("Invalid lesson number.");
            return;
        }

        System.out.print("Enter the grade: ");
        String grade = scanner.next();
        if (!isValidGrade(grade)) {
            System.out.println("Invalid grade.");
            return;
        }

        System.out.print("Enter learner's name: ");
        String name = scanner.next();

        System.out.print("Enter learner's gender: ");
        String gender = scanner.next();

        System.out.print("Enter learner's age: ");
        int age = scanner.nextInt();
        if (!isValidAge(age)) {
            System.out.println("Invalid age.");
            return;
        }

        System.out.print("Enter learner's emergency contact phone number: ");
        String emergencyContact = scanner.next();

        if (vacancies[week][dayIndex][lessonNumber - 1] <= 0) {
            System.out.println("No vacancies available for this lesson.");
            return;
        }

        String[] lessonData = timetable[week][dayIndex][lessonNumber - 1];
        bookLesson(name, lessonData[0], lessonData[1], lessonData[2], lessonData[3], week, dayIndex, lessonNumber - 1);

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

    private int getWeekFromDate(String day) {
        // Mapping days to week numbers
        switch (day.toLowerCase()) {
            case "monday":
                return 1;
            case "wednesday":
                return 2;
            case "friday":
                return 3;
            case "saturday":
                return 4;
            default:
                System.out.println("Invalid day.");
                return -1;
        }
    }

    private String getGrade(String lesson) {
        return lesson.substring(5, 6);
    }

    private int getVacancies(int week, int dayIndex, String lesson) {
        String lessonNumber = lesson.substring(lesson.indexOf("_Lesson") + 7);
        int vacancies = this.vacancies[week][dayIndex][Integer.parseInt(lessonNumber)];
        return Math.max(0, vacancies);
    }
}
