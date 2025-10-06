// Student.java - Student user class
import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private String learningStyle;
    private double overallProgress;
    private int quizAttempts;
    private List<Integer> enrolledCourseIds;

    public Student(int userId, String name, String email, String password, String learningStyle) {
        super(userId, name, email, password);
        this.learningStyle = learningStyle;
        this.overallProgress = 0.0;
        this.quizAttempts = 0;
        this.enrolledCourseIds = new ArrayList<>();
    }

    public void takeQuiz(Quiz quiz) {
        System.out.println("\n" + name + " is taking the quiz: " + quiz.getSubject());
        quiz.evaluateQuiz();
        quizAttempts++;
        updateProgress();
    }

    private void updateProgress() {
        // Simple progress calculation based on quiz attempts
        overallProgress = Math.min(100.0, quizAttempts * 5.0);
    }

    public double getOverallProgress() {
        return overallProgress;
    }

    public void enrollInCourse(int courseId) {
        if (!enrolledCourseIds.contains(courseId)) {
            enrolledCourseIds.add(courseId);
            System.out.println(name + " enrolled in course ID: " + courseId);
        }
    }

    // Getters and setters
    public String getLearningStyle() { return learningStyle; }
    public int getQuizAttempts() { return quizAttempts; }
    public List<Integer> getEnrolledCourseIds() { return enrolledCourseIds; }
    public void setOverallProgress(double progress) { this.overallProgress = progress; }

    // Method for CSV file handling - sets password hash directly without re-hashing
    public void setPasswordHashDirectly(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String getUserType() {
        return "Student";
    }
}