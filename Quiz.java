// Quiz.java - Base class for quizzes
import java.util.ArrayList;
import java.util.List;

public abstract class Quiz {
    protected int quizID;
    protected String subject;
    protected String difficultyLevel;
    protected List<Question> questions;

    public Quiz(int quizID, String subject, String difficultyLevel) {
        this.quizID = quizID;
        this.subject = subject;
        this.difficultyLevel = difficultyLevel;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public abstract int evaluateQuiz();

    // Getters
    public int getQuizID() { return quizID; }
    public String getSubject() { return subject; }
    public String getDifficultyLevel() { return difficultyLevel; }
    public List<Question> getQuestions() { return questions; }
}