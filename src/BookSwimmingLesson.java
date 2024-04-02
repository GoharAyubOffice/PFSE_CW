import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSwimmingLesson {
    private static final String[] GRADE_LEVELS = {"1", "2", "3", "4", "5"};
    private static final String[] DAYS = {"Monday", "Wednesday", "Friday", "Saturday"};
    private static final String[] COACHES = {"CoachA", "CoachB", "CoachC", "CoachD", "CoachE"};


    private List<String[]> bookedLessons; // Stores the booked lessons' data
    private List<String[]> learners; // Stores the learners' data
    private List<String[]> lessonReviews; // Stores reviews for attended lessons

    public List<String[]> getLearners() {
        return learners;
    }

    public List<String[]> getBookedLessonsByLearner(String learnerName) {
        List<String[]> lessons = new ArrayList<>();
        for (String[] lesson : bookedLessons) {
            if (lesson[3].equalsIgnoreCase(learnerName)) {
                lessons.add(lesson);
            }
        }
        return lessons;
    }

    private String[] getLearnerDataByName(String name) {
        for (String[] learner : learners) {
            if (learner[0].equalsIgnoreCase(name)) {
                return learner;
            }
        }
        // If learner not found, return an empty array or null
        return null;
    }

    private String[][][][] timetable = {
            // Week 1
            {
                    // Monday
                    {
                            {"2024-03-04", "Grade1_Lesson1", "CoachA", "4-5pm"},
                            {"2024-03-04", "Grade2_Lesson1", "CoachB", "5-6pm"},
                            {"2024-03-04", "Grade3_Lesson1", "CoachC", "6-7pm"},
                            {"2024-03-04", "Grade4_Lesson1", "CoachD", "4-5pm"},
                            {"2024-03-04", "Grade5_Lesson1", "CoachE", "5-6pm"}
                    },
                    // Wednesday
                    {
                            {"2024-03-06", "Grade1_Lesson2", "CoachA", "4-5pm"},
                            {"2024-03-06", "Grade2_Lesson2", "CoachB", "5-6pm"},
                            {"2024-03-06", "Grade3_Lesson2", "CoachC", "6-7pm"},
                            {"2024-03-06", "Grade4_Lesson2", "CoachD", "4-5pm"},
                            {"2024-03-06", "Grade5_Lesson2", "CoachE", "5-6pm"}
                    },
                    // Friday
                    {
                            {"2024-03-08", "Grade1_Lesson3", "CoachA", "4-5pm"},
                            {"2024-03-08", "Grade2_Lesson3", "CoachB", "5-6pm"},
                            {"2024-03-08", "Grade3_Lesson3", "CoachC", "6-7pm"},
                            {"2024-03-08", "Grade4_Lesson3", "CoachD", "4-5pm"},
                            {"2024-03-08", "Grade5_Lesson3", "CoachE", "5-6pm"}
                    },
                    // Saturday
                    {
                            {"2024-03-09", "Grade1_Lesson4", "CoachA", "2-3pm"},
                            {"2024-03-09", "Grade2_Lesson4", "CoachB", "3-4pm"},
                            {"2024-03-09", "Grade3_Lesson4", "CoachC", "2-3pm"},
                            {"2024-03-09", "Grade4_Lesson4", "CoachD", "3-4pm"},
                            {"2024-03-09", "Grade5_Lesson4", "CoachE", "2-3pm"}
                    }
            }
    };

    public BookSwimmingLesson() {
        bookedLessons = new ArrayList<>();
        learners = new ArrayList<>();
        lessonReviews = new ArrayList<>();
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
            for (String[][][] week : timetable) {
                for (String[] lesson : week[dayIndex]) {
                    System.out.println("Lesson: " + lesson[0] + ", Coach: " + lesson[1] + ", Time: " + lesson[2]);
                }
            }
            bookLesson(day);
        } else {
            System.out.println("Invalid day.");
        }
    }


    public void displayTimetableByGrade(String grade) {
        System.out.println("Timetable for Grade " + grade + ":");
        int currentGrade = Integer.parseInt(grade);
        for (String[][][] week : timetable) {
            for (String[][] day : week) {
                for (String[] lesson : day) {
                    int lessonGrade = Integer.parseInt(lesson[0].substring(5, 6));
                    if (lessonGrade == currentGrade || lessonGrade == currentGrade + 1) {
                        System.out.println("Day: " + day + ", Lesson: " + lesson[0] + ", Coach: " + lesson[1] + ", Time: " + lesson[2]);
                    }
                }
            }
        }
        bookLessonByGrade(grade);
    }

    public void displayTimetableByCoach(String coach) {
        System.out.println("Timetable for Coach " + coach + ":");
        for (String[][][] week : timetable) {
            for (String[][] day : week) {
                for (String[] lesson : day) {
                    if (lesson[1].equals(coach)) {
                        System.out.println("Day: " + day + ", Lesson: " + lesson[0] + ", Grade: " + getGrade(lesson[0]) + ", Time: " + lesson[2]);
                    }
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

    public void incrementGrade(String learnerName) {
        for (String[] learner : learners) {
            if (learner[0].equalsIgnoreCase(learnerName)) {
                int currentGrade = Integer.parseInt(learner[4]);
                if (currentGrade < 5) {
                    currentGrade++;
                    learner[4] = String.valueOf(currentGrade);
                    System.out.println("Grade level incremented for learner " + learnerName + ". New grade: " + currentGrade);
                } else {
                    System.out.println("The learner " + learnerName + " has reached the maximum grade level.");
                }
                break;
            }
        }
    }

    public boolean isLessonBooked(String day, String grade, String lessonNumber) {
        for (String[] lesson : bookedLessons) {
            if (lesson[0].equalsIgnoreCase(day) && lesson[2].equalsIgnoreCase(grade) && lesson[1].equalsIgnoreCase(lessonNumber)) {
                return true;
            }
        }
        return false;
    }

    public void addReview(String day, String grade, String lessonNumber, String review, int rating) {
        String[] reviewData = {day, grade, lessonNumber, review, String.valueOf(rating)};
        lessonReviews.add(reviewData);
    }

    public List<String[]> getLessonReviews() {
        return lessonReviews;
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
