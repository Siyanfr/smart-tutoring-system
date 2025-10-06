// MultipleChoiceQuiz.java - Multiple choice quiz implementation
import java.util.Scanner;
import java.util.*;

public class MultipleChoiceQuiz extends Quiz {

    public MultipleChoiceQuiz(int quizID, String subject, String difficultyLevel) {
        super(quizID, subject, difficultyLevel);
    }

    @Override
    public int evaluateQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int totalPoints = 0;

        System.out.println("\n=== " + subject + " Quiz (Multiple Choice) ===");
        System.out.println("Difficulty: " + difficultyLevel);
        System.out.println("---------------------------------------------");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            totalPoints += q.getPoints();

            if (q instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) q;
                System.out.println("\nQuestion " + (i + 1) + ": " + mcq.getQuestionText());

                List<String> options = mcq.getOptions();
                for (int j = 0; j < options.size(); j++) {
                    System.out.println((char)('A' + j) + ") " + options.get(j));
                }

                System.out.print("Your answer: ");
                String answer = scanner.nextLine().trim().toUpperCase();

                // Convert letter to option text
                if (answer.length() == 1 && answer.charAt(0) >= 'A' && answer.charAt(0) < 'A' + options.size()) {
                    int index = answer.charAt(0) - 'A';
                    String selectedOption = options.get(index);

                    if (mcq.checkAnswer(selectedOption)) {
                        score += q.getPoints();
                        System.out.println("✓ Correct!");
                    } else {
                        System.out.println("✗ Incorrect. Correct answer: " + mcq.getCorrectOption());
                    }
                } else {
                    System.out.println("✗ Invalid answer format.");
                }
            }
        }

        System.out.println("\n---------------------------------------------");
        System.out.println("Quiz completed! Score: " + score + "/" + totalPoints);
        System.out.println("Percentage: " + (totalPoints > 0 ? (score * 100 / totalPoints) : 0) + "%");

        return score;
    }
}