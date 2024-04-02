import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Report {
    public void generateMonthlyLearnerReport(List<String[]> bookedLessons, String month) {
        List<String[]> monthlyLessons = new ArrayList<>();
        for (String[] lesson : bookedLessons) {
            String lessonDate = lesson[0];
            LocalDate date = LocalDate.parse(lessonDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int lessonMonth = date.getMonthValue();
            if (Integer.toString(lessonMonth).equals(month)) {
                monthlyLessons.add(lesson);
            }
        }

        System.out.println("Monthly learner report for month " + month + ":");
        for (String[] lesson : monthlyLessons) {
            String learner = lesson[3];
            System.out.println("Learner: " + learner);
            // Print other details or perform other actions as needed
        }
    }
}
