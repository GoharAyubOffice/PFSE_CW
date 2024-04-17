import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

public class Report {
    public void generateLearnerReport(List<String[]> bookedLessons, List<String[]> attendedLessons, List<String[]> learners){
        StringBuilder learnerReport = new StringBuilder();
        learnerReport.append("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n");
        learnerReport.append("║                       Learner Report                                                                          ║\n");
        learnerReport.append("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n");

        // Define arrays for grades, coaches, and lesson statuses
        String[] GRADES = {"1", "2", "3", "4"};
        String[] COACHES = {"CoachA", "CoachB", "CoachC", "CoachD"};
        String[] LESSON_STATUSES = {"Booked", "Attended", "Canceled"};

        // Generate learner names
        String[] learnerNames = generateLearnerNames(15);

        // Initialize a map to track lesson status for each learner
        Map<String, Map<String, String>> learnerLessons = new HashMap<>();

        // Populate the map with random lesson statuses for each learner
        Random random = new Random();
        for (String learnerName : learnerNames) {
            Map<String, String> lessonStatuses = new HashMap<>();
            for (int week = 0; week < 4; week++) {
                for (int dayIndex = 0; dayIndex < 4; dayIndex++) {
                    for (int lessonIndex = 0; lessonIndex < 3; lessonIndex++) {
                        String grade = GRADES[random.nextInt(GRADES.length)];
                        String coach = COACHES[random.nextInt(COACHES.length)];
                        String status = LESSON_STATUSES[random.nextInt(3)];
                        String lesson = String.format("Grade%s_Lesson%s", grade, lessonIndex);
                        String key = String.format("Week%s_%s_%s", week + 1, dayIndex + 1, lesson);
                        lessonStatuses.put(key, status);
                    }
                }
            }
            learnerLessons.put(learnerName, lessonStatuses);
        }

        // Append learner information to the report
        for (String learnerName : learnerNames) {
            learnerReport.append(String.format("║ Learner: %-20s", learnerName));
            for (int week = 0; week < 4; week++) {
                for (int dayIndex = 0; dayIndex < 4; dayIndex++) {
                    for (int lessonIndex = 0; lessonIndex < 3; lessonIndex++) {
                        String grade = GRADES[random.nextInt(GRADES.length)];
                        String lesson = String.format("Grade%s_Lesson%s", grade, lessonIndex);
                        String key = String.format("Week%s_%s_%s", week + 1, dayIndex + 1, lesson);
                        String status = learnerLessons.get(learnerName).get(key);
                        learnerReport.append(String.format(" %s: %-10s", lesson, status));
                    }
                }
            }
            learnerReport.append(" ║\n");
        }

        // Append closing lines of the report
        learnerReport.append("╚══════════════════════════════════════════════════════════════════════════╝\n");

        System.out.println(learnerReport.toString());
    }

    public void generateCoachReport() {
        StringBuilder coachReport = new StringBuilder();
        coachReport.append("╔═════════════════════════ Coach Report ═════════════════════════════════════╗\n");

        // Define array for coaches
        String[] COACHES = {"CoachA", "CoachB", "CoachC", "CoachD"};

        // Generate random ratings and reviews for each coach
        Random random = new Random();
        for (String coach : COACHES) {
            int rating = random.nextInt(5) + 1; // Random rating between 1 and 5
            String review = generateRandomReview();
            coachReport.append(String.format("║ Coach: %-10s, Average Rating: %d, Review: %s\n", coach, rating, review));
        }
        coachReport.append("╚══════════════════════════════════════════════════════════════════════════╝\n");

        System.out.println(coachReport.toString());
    }

    // Helper method to generate random learner names
    private String[] generateLearnerNames(int count) {
        String[] names = new String[count];
        for (int i = 0; i < count; i++) {
            names[i] = "Learner" + (i + 1);
        }
        return names;
    }

    // Helper method to generate a random review
    private String generateRandomReview() {
        String[] reviews = {
                "Great coach, very patient and helpful.",
                "The lessons were well-organized and informative.",
                "Could improve on providing individual attention to learners.",
                "Overall a positive experience, would recommend.",
                "Not satisfied with the coaching, needs improvement."
        };
        Random random = new Random();
        return reviews[random.nextInt(reviews.length)];
    }
}



