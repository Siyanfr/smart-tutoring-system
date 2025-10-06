// TutorMenuManager.java - Tutor menu interface
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TutorMenuManager extends MenuManager {
    private QuizManager quizManager;
    private LessonManager lessonManager;

    public TutorMenuManager(User currentUser, QuizManager quizManager, LessonManager lessonManager) {
        super(currentUser);
        this.quizManager = quizManager;
        this.lessonManager = lessonManager;
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║       TUTOR DASHBOARD              ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("Welcome, " + currentUser.getName() + "!");
            System.out.println("\n1. Create Quiz");
            System.out.println("2. Upload Lesson");
            System.out.println("3. Review Student");
            System.out.println("4. Generate Student Recommendations");
            System.out.println("5. View My Quizzes");
            System.out.println("6. View Assigned Students");
            System.out.println("7. Logout");
            System.out.print("\nSelect an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        createQuiz(scanner);
                        break;
                    case 2:
                        uploadLesson(scanner);
                        break;
                    case 3:
                        reviewStudent(scanner);
                        break;
                    case 4:
                        generateRecommendations(scanner);
                        break;
                    case 5:
                        viewMyQuizzes();
                        break;
                    case 6:
                        viewAssignedStudents();
                        break;
                    case 7:
                        currentUser.logout();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void createQuiz(Scanner scanner) {
        System.out.println("\n=== Create New Quiz ===");
        System.out.print("Enter quiz subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter difficulty (Easy/Medium/Hard): ");
        String difficulty = scanner.nextLine();
        System.out.print("Quiz type (1=Multiple Choice, 2=True/False): ");
        int type = Integer.parseInt(scanner.nextLine());

        Quiz newQuiz;
        int quizId = quizManager.getQuizzes().size() + 1;

        if (type == 1) {
            newQuiz = new MultipleChoiceQuiz(quizId, subject, difficulty);
            System.out.print("How many questions? ");
            int numQuestions = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < numQuestions; i++) {
                System.out.println("\nQuestion " + (i + 1) + ":");
                System.out.print("Question text: ");
                String questionText = scanner.nextLine();
                System.out.print("Points: ");
                int points = Integer.parseInt(scanner.nextLine());
                System.out.print("Number of options: ");
                int numOptions = Integer.parseInt(scanner.nextLine());

                List<String> options = new ArrayList<>();
                for (int j = 0; j < numOptions; j++) {
                    System.out.print("Option " + (j + 1) + ": ");
                    options.add(scanner.nextLine());
                }

                System.out.print("Correct option: ");
                String correctOption = scanner.nextLine();

                newQuiz.addQuestion(new MultipleChoiceQuestion(questionText, points, options, correctOption));
            }
        } else {
            newQuiz = new TrueFalseQuiz(quizId, subject, difficulty);
            System.out.print("How many questions? ");
            int numQuestions = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < numQuestions; i++) {
                System.out.println("\nQuestion " + (i + 1) + ":");
                System.out.print("Question text: ");
                String questionText = scanner.nextLine();
                System.out.print("Points: ");
                int points = Integer.parseInt(scanner.nextLine());
                System.out.print("Correct answer (true/false): ");
                boolean correctAnswer = Boolean.parseBoolean(scanner.nextLine());

                newQuiz.addQuestion(new TrueFalseQuestion(questionText, points, correctAnswer));
            }
        }

        ((Tutor) currentUser).createQuiz(newQuiz);
        quizManager.addQuiz(newQuiz);
    }

    private void uploadLesson(Scanner scanner) {
        System.out.println("\n=== Upload New Lesson ===");
        System.out.print("Lesson title: ");
        String title = scanner.nextLine();
        System.out.print("Subject: ");
        String subject = scanner.nextLine();
        System.out.print("Difficulty (Easy/Medium/Hard): ");
        String difficulty = scanner.nextLine();
        System.out.print("Lesson content: ");
        String content = scanner.nextLine();

        int lessonId = lessonManager.getLessons().size() + 1;
        Lesson newLesson = new Lesson(lessonId, title, subject, content, difficulty);
        lessonManager.addLesson(newLesson);
    }

    private void reviewStudent(Scanner scanner) {
        Tutor tutor = (Tutor) currentUser;
        if (tutor.getAssignedStudents().isEmpty()) {
            System.out.println("No students assigned yet.");
            return;
        }

        System.out.println("\n=== Assigned Students ===");
        for (int i = 0; i < tutor.getAssignedStudents().size(); i++) {
            System.out.println((i + 1) + ". " + tutor.getAssignedStudents().get(i).getName());
        }

        System.out.print("Select student number to review: ");
        int studentNum = Integer.parseInt(scanner.nextLine());

        if (studentNum > 0 && studentNum <= tutor.getAssignedStudents().size()) {
            Student student = tutor.getAssignedStudents().get(studentNum - 1);
            tutor.reviewStudent(student);
        }
    }

    private void generateRecommendations(Scanner scanner) {
        Tutor tutor = (Tutor) currentUser;
        if (tutor.getAssignedStudents().isEmpty()) {
            System.out.println("No students assigned yet.");
            return;
        }

        System.out.println("\n=== Assigned Students ===");
        for (int i = 0; i < tutor.getAssignedStudents().size(); i++) {
            System.out.println((i + 1) + ". " + tutor.getAssignedStudents().get(i).getName());
        }

        System.out.print("Select student number: ");
        int studentNum = Integer.parseInt(scanner.nextLine());

        if (studentNum > 0 && studentNum <= tutor.getAssignedStudents().size()) {
            Student student = tutor.getAssignedStudents().get(studentNum - 1);
            tutor.generateRecommendation(student);
        }
    }

    private void viewMyQuizzes() {
        Tutor tutor = (Tutor) currentUser;
        System.out.println("\n=== My Created Quizzes ===");
        if (tutor.getCreatedQuizzes().isEmpty()) {
            System.out.println("No quizzes created yet.");
        } else {
            for (Quiz quiz : tutor.getCreatedQuizzes()) {
                System.out.println("- " + quiz.getSubject() + " (" + quiz.getDifficultyLevel() +
                        ") - " + quiz.getQuestions().size() + " questions");
            }
        }
    }

    private void viewAssignedStudents() {
        Tutor tutor = (Tutor) currentUser;
        System.out.println("\n=== Assigned Students ===");
        if (tutor.getAssignedStudents().isEmpty()) {
            System.out.println("No students assigned yet.");
        } else {
            for (Student student : tutor.getAssignedStudents()) {
                System.out.println("- " + student.getName() + " (Progress: " +
                        String.format("%.1f", student.getOverallProgress()) + "%)");
            }
        }
    }
}