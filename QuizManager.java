// QuizManager.java - Manages all quizzes in the system
import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private List<Quiz> quizzes;

    public QuizManager() {
        this.quizzes = new ArrayList<>();
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
        System.out.println("Quiz added: " + quiz.getSubject() + " (" + quiz.getDifficultyLevel() + ")");
    }

    public Quiz getQuiz(String subject, String difficulty) throws QuizNotFoundException {
        for (Quiz quiz : quizzes) {
            if (quiz.getSubject().equalsIgnoreCase(subject) &&
                    quiz.getDifficultyLevel().equalsIgnoreCase(difficulty)) {
                return quiz;
            }
        }
        throw new QuizNotFoundException("Quiz not found: " + subject + " - " + difficulty);
    }

    public Quiz generatePersonalizedQuiz(Student student) {
        // Simple personalization based on progress
        String difficulty;
        if (student.getOverallProgress() < 30) {
            difficulty = "Easy";
        } else if (student.getOverallProgress() < 70) {
            difficulty = "Medium";
        } else {
            difficulty = "Hard";
        }

        // Find a quiz matching the difficulty
        for (Quiz quiz : quizzes) {
            if (quiz.getDifficultyLevel().equalsIgnoreCase(difficulty)) {
                System.out.println("Personalized quiz selected based on progress: " + quiz.getSubject());
                return quiz;
            }
        }

        // Return first available quiz if no match
        return quizzes.isEmpty() ? null : quizzes.get(0);
    }

    public void listAllQuizzes() {
        System.out.println("\n=== Available Quizzes ===");
        for (Quiz quiz : quizzes) {
            System.out.println("- " + quiz.getSubject() + " (" + quiz.getDifficultyLevel() + ") - " +
                    quiz.getQuestions().size() + " questions");
        }
    }

    // Getter
    public List<Quiz> getQuizzes() { return quizzes; }
}