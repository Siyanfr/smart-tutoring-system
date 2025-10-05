// TrueFalseQuiz.java - True/False quiz implementation
import java.util.Scanner;

public class TrueFalseQuiz extends Quiz {

    public TrueFalseQuiz(int quizID, String subject, String difficultyLevel) {
        super(quizID, subject, difficultyLevel);
    }

    @Override
    public int evaluateQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int totalPoints = 0;

        System.out.println("\n=== " + subject + " Quiz (True/False) ===");
        System.out.println("Difficulty: " + difficultyLevel);
        System.out.println("---------------------------------------------");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            totalPoints += q.getPoints();

            if (q instanceof TrueFalseQuestion) {
                TrueFalseQuestion tfq = (TrueFalseQuestion) q;
                System.out.println("\nQuestion " + (i + 1) + ": " + tfq.getQuestionText());
                System.out.print("Your answer (True/False): ");

                String answer = scanner.nextLine().trim().toLowerCase();
                boolean userAnswer;

                if (answer.equals("true") || answer.equals("t")) {
                    userAnswer = true;
                } else if (answer.equals("false") || answer.equals("f")) {
                    userAnswer = false;
                } else {
                    System.out.println("✗ Invalid answer format.");
                    continue;
                }

                if (tfq.checkAnswer(userAnswer)) {
                    score += q.getPoints();
                    System.out.println("✓ Correct!");
                } else {
                    System.out.println("✗ Incorrect. Correct answer: " + tfq.getCorrectAnswer());
                }
            }
        }

        System.out.println("\n---------------------------------------------");
        System.out.println("Quiz completed! Score: " + score + "/" + totalPoints);
        System.out.println("Percentage: " + (totalPoints > 0 ? (score * 100 / totalPoints) : 0) + "%");

        return score;
    }
}