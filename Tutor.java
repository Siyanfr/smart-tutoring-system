// Tutor.java - Tutor user class
import java.util.ArrayList;
import java.util.List;

public class Tutor extends User implements RecommendationProvider {
    private List<Student> assignedStudents;
    private List<Quiz> createdQuizzes;

    public Tutor(int userId, String name, String email, String password) {
        super(userId, name, email, password);
        this.assignedStudents = new ArrayList<>();
        this.createdQuizzes = new ArrayList<>();
    }

    public void createQuiz(Quiz quiz) {
        createdQuizzes.add(quiz);
        System.out.println("Tutor " + name + " created quiz: " + quiz.getSubject());
    }

    public void reviewStudent(Student student) {
        System.out.println("\n=== Student Review ===");
        System.out.println("Student: " + student.getName());
        System.out.println("Learning Style: " + student.getLearningStyle());
        System.out.println("Overall Progress: " + student.getOverallProgress() + "%");
        System.out.println("Quiz Attempts: " + student.getQuizAttempts());
        System.out.println("Enrolled Courses: " + student.getEnrolledCourseIds().size());
    }

    public void assignStudent(Student student) {
        if (!assignedStudents.contains(student)) {
            assignedStudents.add(student);
            System.out.println("Student " + student.getName() + " assigned to tutor " + name);
        }
    }

    @Override
    public void generateRecommendation(Student student) {
        System.out.println("\n=== Recommendations for " + student.getName() + " ===");

        if (student.getOverallProgress() < 30) {
            System.out.println("- Focus on foundational topics");
            System.out.println("- Take more beginner quizzes");
        } else if (student.getOverallProgress() < 70) {
            System.out.println("- Good progress! Continue with intermediate topics");
            System.out.println("- Try mixed difficulty quizzes");
        } else {
            System.out.println("- Excellent progress! Challenge yourself with advanced topics");
            System.out.println("- Focus on specialized areas");
        }

        if (student.getQuizAttempts() < 5) {
            System.out.println("- Take more quizzes to improve assessment");
        }
    }

    // Getters
    public List<Student> getAssignedStudents() { return assignedStudents; }
    public List<Quiz> getCreatedQuizzes() { return createdQuizzes; }

    @Override
    public String getUserType() {
        return "Tutor";
    }
}