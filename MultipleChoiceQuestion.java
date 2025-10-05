// MultipleChoiceQuestion.java - Multiple choice question implementation
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;
    private String correctOption;

    public MultipleChoiceQuestion(String questionText, int points, List<String> options, String correctOption) {
        super(questionText, points);
        this.options = options;
        this.correctOption = correctOption;
    }

    @Override
    public boolean checkAnswer(Object answer) {
        if (answer instanceof String) {
            return correctOption.equalsIgnoreCase((String) answer);
        }
        return false;
    }

    // Getters
    public List<String> getOptions() { return options; }
    public String getCorrectOption() { return correctOption; }
}