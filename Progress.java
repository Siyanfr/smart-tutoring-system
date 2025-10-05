// Progress.java - Tracks student progress
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Progress {
    private List<Quiz> completedQuizzes;
    private Map<Quiz, Integer> quizScores;
    private List<Lesson> completedLessons;

    public Progress() {
        this.completedQuizzes = new ArrayList<>();
        this.quizScores = new HashMap<>();
        this.completedLessons = new ArrayList<>();
    }

    public void addCompletedQuiz(Quiz quiz, int score) {
        if (!completedQuizzes.contains(quiz)) {
            completedQuizzes.add(quiz);
        }
        quizScores.put(quiz, score);
    }

    public void addCompletedLesson(Lesson lesson) {
        if (!completedLessons.contains(lesson)) {
            completedLessons.add(lesson);
        }
    }

    public Integer getLatestScore(Quiz quiz) {
        return quizScores.get(quiz);
    }

    public List<Lesson> getCompletedLessons() {
        return new ArrayList<>(completedLessons);
    }

    // Getters
    public List<Quiz> getCompletedQuizzes() { return completedQuizzes; }
    public Map<Quiz, Integer> getQuizScores() { return quizScores; }
}