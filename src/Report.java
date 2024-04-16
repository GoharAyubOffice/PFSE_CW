import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
    public void generateLearnerReport(List<String[]> bookedLessons, List<String[]> attendedLessons, List<String[]> learners) {
        Map<String, String[]> learnerDetailsMap = new HashMap<>();
        Map<String, Boolean> learnerAttendanceMap = new HashMap<>();

        // Populate learner details map with learner data
        for (String[] learner : learners) {
            String name = learner[0];
            String gender = learner[1];
            String grade = learner[4];
            String emergencyContact = learner[3];
            learnerDetailsMap.put(name, new String[]{gender, grade, emergencyContact});
        }

        // Mark learners as attended
        for (String[] lesson : attendedLessons) {
            String learnerName = lesson[3];
            learnerAttendanceMap.put(learnerName, true);
        }

        // Print learner report
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       Learner Report                                                                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        for (Map.Entry<String, String[]> entry : learnerDetailsMap.entrySet()) {
            String learnerName = entry.getKey();
            String[] details = entry.getValue();
            String gender = details[0];
            String grade = details[1];
            String emergencyContact = details[2];
            boolean attended = learnerAttendanceMap.getOrDefault(learnerName, false);
            String status = attended ? "Attended" : "Booked but not attended";
            System.out.printf("║ Learner: %-20s Gender: %-6s Grade: %-3s Emergency Contact: %-12s Status: %-20s ║\n", learnerName, gender, grade, emergencyContact, status);
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public void generateCoachReport(List<String[]> lessonReviews) {
        Map<String, Integer> coachRatingMap = new HashMap<>();
        Map<String, Integer> coachRatingCount = new HashMap<>();
        Map<String, String> coachReviewMap = new HashMap<>();

        // Populate coach rating map and review map
        for (String[] review : lessonReviews) {
            String coach = review[2]; // Assuming coach name is at index 2
            int rating = Integer.parseInt(review[4]); // Assuming rating is at index 4
            String reviewText = review[3]; // Assuming review text is at index 3

            coachRatingMap.put(coach, coachRatingMap.getOrDefault(coach, 0) + rating);
            coachRatingCount.put(coach, coachRatingCount.getOrDefault(coach, 0) + 1);
            coachReviewMap.put(coach, reviewText);
        }

        // Print coach report
        System.out.println("╔═════════════════════════ Coach Report ═════════════════════════════════════╗");
        for (Map.Entry<String, Integer> entry : coachRatingMap.entrySet()) {
            String coach = entry.getKey();
            int totalRating = entry.getValue();
            int count = coachRatingCount.get(coach);
            double averageRating = (double) totalRating / count;
            String review = coachReviewMap.get(coach);

            System.out.println("║ Coach: " + coach + ", Average Rating: " + String.format("%.2f", averageRating) + ", Review: " + review);
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
    }
}

