// QuizNotFoundException.java - Custom exception for quiz errors
public class QuizNotFoundException extends Exception {
    public QuizNotFoundException(String message) {
        super(message);
    }
}