// TrueFalseQuestion.java - True/False question implementation
public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, int points, boolean correctAnswer) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(Object answer) {
        if (answer instanceof Boolean) {
            return correctAnswer == (Boolean) answer;
        }
        return false;
    }

    // Getter
    public boolean getCorrectAnswer() { return correctAnswer; }
}