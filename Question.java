// Question.java - Abstract base class for questions
public abstract class Question {
    protected String questionText;
    protected int points;

    public Question(String questionText, int points) {
        this.questionText = questionText;
        this.points = points;
    }

    public abstract boolean checkAnswer(Object answer);

    // Getters
    public String getQuestionText() { return questionText; }
    public int getPoints() { return points; }
}