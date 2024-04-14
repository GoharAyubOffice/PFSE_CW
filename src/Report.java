import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
    public void generateLearnerReport(List<String[]> bookedLessons, List<String[]> attendedLessons) {
        Map<String, String> learnerStatusMap = new HashMap<>();

        // Populate learner status map with booked lessons
        for (String[] lesson : bookedLessons) {
            learnerStatusMap.put(lesson[3], "Booked but not attended");
        }

        // Update learner status map with attended lessons
        for (String[] lesson : attendedLessons) {
            learnerStatusMap.put(lesson[3], "Attended");
        }

        // Print learner report
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                       Learner Report                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        for (Map.Entry<String, String> entry : learnerStatusMap.entrySet()) {
            System.out.printf("║ Learner: %-20s Status: %-20s ║\n", entry.getKey(), entry.getValue());
        }
        System.out.println("╚══════════════════════════════════════════════════════════╝");
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

