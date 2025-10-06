// ProgressTracker.java - Tracks and reports student progress
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProgressTracker {
    private List<Progress> progressRecords;

    public ProgressTracker() {
        this.progressRecords = new ArrayList<>();
    }

    public void recordProgress(Progress progress) {
        progressRecords.add(progress);
        System.out.println("Progress recorded successfully.");
    }

    public void generateReport(Student student) {
        System.out.println("\n========================================");
        System.out.println("     PROGRESS REPORT FOR " + student.getName().toUpperCase());
        System.out.println("========================================");
        System.out.println("Learning Style: " + student.getLearningStyle());
        System.out.println("Overall Progress: " + String.format("%.2f", student.getOverallProgress()) + "%");
        System.out.println("Total Quiz Attempts: " + student.getQuizAttempts());
        System.out.println("Enrolled Courses: " + student.getEnrolledCourseIds().size());

        // Find progress records for this student
        System.out.println("\n--- Quiz Performance ---");
        for (Progress progress : progressRecords) {
            if (!progress.getCompletedQuizzes().isEmpty()) {
                for (Map.Entry<Quiz, Integer> entry : progress.getQuizScores().entrySet()) {
                    Quiz quiz = entry.getKey();
                    Integer score = entry.getValue();
                    System.out.println("  • " + quiz.getSubject() + " (" + quiz.getDifficultyLevel() +
                            "): " + score + " points");
                }
            }
        }

        System.out.println("\n--- Completed Lessons ---");
        for (Progress progress : progressRecords) {
            for (Lesson lesson : progress.getCompletedLessons()) {
                System.out.println("  • " + lesson.getTitle() + " (" + lesson.getSubject() + ")");
            }
        }

        System.out.println("========================================\n");
    }

    // Getter
    public List<Progress> getProgressRecords() { return progressRecords; }
}