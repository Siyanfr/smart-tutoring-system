// StudentMenuManager.java - Student menu interface
import java.util.Scanner;

public class StudentMenuManager extends MenuManager {
    private QuizManager quizManager;
    private ProgressTracker progressTracker;
    private LessonManager lessonManager;
    private RecommendationEngine recommendationEngine;
    private Progress studentProgress;

    public StudentMenuManager(User currentUser, QuizManager quizManager,
                              ProgressTracker progressTracker, LessonManager lessonManager,
                              RecommendationEngine recommendationEngine) {
        super(currentUser);
        this.quizManager = quizManager;
        this.progressTracker = progressTracker;
        this.lessonManager = lessonManager;
        this.recommendationEngine = recommendationEngine;
        this.studentProgress = new Progress();
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║      STUDENT DASHBOARD             ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("Welcome, " + currentUser.getName() + "!");
            System.out.println("\n1. Browse Lessons");
            System.out.println("2. Study a Lesson");
            System.out.println("3. Take a Quiz");
            System.out.println("4. View Progress Report");
            System.out.println("5. Get Recommendations");
            System.out.println("6. Enroll in Course");
            System.out.println("7. Logout");
            System.out.print("\nSelect an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        browseLessons();
                        break;
                    case 2:
                        studyLesson(scanner);
                        break;
                    case 3:
                        takeQuiz(scanner);
                        break;
                    case 4:
                        viewProgress();
                        break;
                    case 5:
                        getRecommendations();
                        break;
                    case 6:
                        enrollInCourse(scanner);
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

    private void browseLessons() {
        lessonManager.browseLessons();
    }

    private void studyLesson(Scanner scanner) {
        lessonManager.browseLessons();
        System.out.print("\nEnter lesson number to study (0 to cancel): ");
        try {
            int lessonNum = Integer.parseInt(scanner.nextLine());
            if (lessonNum > 0) {
                Lesson lesson = lessonManager.getLesson(lessonNum - 1);
                if (lesson != null) {
                    lesson.displayLesson();
                    studentProgress.addCompletedLesson(lesson);
                    System.out.println("✓ Lesson marked as completed!");
                } else {
                    System.out.println("Invalid lesson number.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void takeQuiz(Scanner scanner) {
        quizManager.listAllQuizzes();
        System.out.println("\nOptions:");
        System.out.println("1. Select specific quiz");
        System.out.println("2. Get personalized quiz recommendation");
        System.out.print("Choose: ");

        try {
            int option = Integer.parseInt(scanner.nextLine());
            Quiz selectedQuiz = null;

            if (option == 1) {
                System.out.print("Enter quiz subject: ");
                String subject = scanner.nextLine();
                System.out.print("Enter difficulty (Easy/Medium/Hard): ");
                String difficulty = scanner.nextLine();

                try {
                    selectedQuiz = quizManager.getQuiz(subject, difficulty);
                } catch (QuizNotFoundException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            } else if (option == 2) {
                selectedQuiz = quizManager.generatePersonalizedQuiz((Student) currentUser);
            }

            if (selectedQuiz != null) {
                System.out.println("\n" + currentUser.getName() + " is taking the quiz: " + selectedQuiz.getSubject());
                int score = selectedQuiz.evaluateQuiz(); // prints quiz once and calculates score
                studentProgress.addCompletedQuiz(selectedQuiz, score);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void viewProgress() {
        progressTracker.recordProgress(studentProgress);
        progressTracker.generateReport((Student) currentUser);
    }

    private void getRecommendations() {
        recommendationEngine.generateRecommendations((Student) currentUser);
    }

    private void enrollInCourse(Scanner scanner) {
        System.out.print("Enter course ID to enroll: ");
        try {
            int courseId = Integer.parseInt(scanner.nextLine());
            ((Student) currentUser).enrollInCourse(courseId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid course ID.");
        }
    }
}
