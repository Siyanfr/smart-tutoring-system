// SmartTutoringSystem.java - Main application entry point
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SmartTutoringSystem {
    private static List<User> allUsers = new ArrayList<>();
    private static QuizManager quizManager = new QuizManager();
    private static LessonManager lessonManager = new LessonManager();
    private static ProgressTracker progressTracker = new ProgressTracker();
    private static RecommendationEngine recommendationEngine = new RecommendationEngine(progressTracker);

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   SMART TUTORING SYSTEM v1.0         ║");
        System.out.println("║   Holy Angel University               ║");
        System.out.println("╚═══════════════════════════════════════╝\n");

        // Initialize sample data
        initializeSampleData();

        // Load registered students from CSV file
        loadRegisteredStudents();

        // Start login system
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== LOGIN MENU ===");
            System.out.println("1. Login");
            System.out.println("2. Register as Student");
            System.out.println("3. View Sample Credentials");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        handleLogin(scanner);
                        break;
                    case 2:
                        handleRegistration(scanner);
                        break;
                    case 3:
                        displaySampleCredentials();
                        break;
                    case 4:
                        System.out.println("Thank you for using Smart Tutoring System!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        scanner.close();
    }

    private static void initializeSampleData() {
        // Create sample students
        Student student1 = new Student(1, "John Doe", "john@student.edu", "pass123", "Visual");
        Student student2 = new Student(2, "Jane Smith", "jane@student.edu", "pass123", "Auditory");
        Student student3 = new Student(3, "Mike Johnson", "mike@student.edu", "pass123", "Kinesthetic");

        student1.enrollInCourse(101);
        student1.enrollInCourse(102);
        student2.enrollInCourse(101);

        // Create sample tutor
        Tutor tutor1 = new Tutor(10, "Dr. Maria Santos", "maria@tutor.edu", "tutor123");
        tutor1.assignStudent(student1);
        tutor1.assignStudent(student2);

        // Create sample admin
        Admin admin1 = new Admin(20, "Admin User", "admin@system.edu", "admin123");

        // Add users to system
        allUsers.add(student1);
        allUsers.add(student2);
        allUsers.add(student3);
        allUsers.add(tutor1);
        allUsers.add(admin1);

        // Initialize admin's user list
        for (User user : allUsers) {
            admin1.addUser(user);
        }

        // Create sample lessons
        Lesson lesson1 = new Lesson(1, "Introduction to Java", "Programming",
                "Java is a high-level, object-oriented programming language. It follows the principle of WORA (Write Once, Run Anywhere).",
                "Easy");
        Lesson lesson2 = new Lesson(2, "Object-Oriented Programming Basics", "Programming",
                "OOP is a programming paradigm based on objects that contain data and code. Key concepts include encapsulation, inheritance, and polymorphism.",
                "Medium");
        Lesson lesson3 = new Lesson(3, "Data Structures Introduction", "Computer Science",
                "Data structures are ways of organizing and storing data. Common structures include arrays, linked lists, stacks, and queues.",
                "Medium");
        Lesson lesson4 = new Lesson(4, "Advanced Algorithms", "Computer Science",
                "Advanced algorithms optimize problem-solving. Topics include dynamic programming, greedy algorithms, and graph algorithms.",
                "Hard");

        lessonManager.addLesson(lesson1);
        lessonManager.addLesson(lesson2);
        lessonManager.addLesson(lesson3);
        lessonManager.addLesson(lesson4);

        // Create sample quizzes
        // Easy Java Quiz
        MultipleChoiceQuiz javaQuiz = new MultipleChoiceQuiz(1, "Java Basics", "Easy");
        javaQuiz.addQuestion(new MultipleChoiceQuestion(
                "What does JVM stand for?",
                10,
                Arrays.asList("Java Virtual Machine", "Java Variable Method", "Java Visual Manager", "Java Version Model"),
                "Java Virtual Machine"
        ));
        javaQuiz.addQuestion(new MultipleChoiceQuestion(
                "Which keyword is used to create a class in Java?",
                10,
                Arrays.asList("class", "Class", "new", "create"),
                "class"
        ));
        quizManager.addQuiz(javaQuiz);
        tutor1.createQuiz(javaQuiz);

        // Medium OOP Quiz
        MultipleChoiceQuiz oopQuiz = new MultipleChoiceQuiz(2, "OOP Concepts", "Medium");
        oopQuiz.addQuestion(new MultipleChoiceQuestion(
                "Which OOP principle hides internal details?",
                15,
                Arrays.asList("Encapsulation", "Inheritance", "Polymorphism", "Abstraction"),
                "Encapsulation"
        ));
        oopQuiz.addQuestion(new MultipleChoiceQuestion(
                "What allows a class to inherit properties from another class?",
                15,
                Arrays.asList("Inheritance", "Interface", "Abstract", "Final"),
                "Inheritance"
        ));
        quizManager.addQuiz(oopQuiz);
        tutor1.createQuiz(oopQuiz);

        // True/False Quiz
        TrueFalseQuiz tfQuiz = new TrueFalseQuiz(3, "Programming Facts", "Easy");
        tfQuiz.addQuestion(new TrueFalseQuestion(
                "Java is a platform-independent language.",
                10,
                true
        ));
        tfQuiz.addQuestion(new TrueFalseQuestion(
                "A class can extend multiple classes in Java.",
                10,
                false
        ));
        tfQuiz.addQuestion(new TrueFalseQuestion(
                "Encapsulation helps protect data from unauthorized access.",
                10,
                true
        ));
        quizManager.addQuiz(tfQuiz);

        // Hard Data Structures Quiz
        MultipleChoiceQuiz dsQuiz = new MultipleChoiceQuiz(4, "Data Structures", "Hard");
        dsQuiz.addQuestion(new MultipleChoiceQuestion(
                "What is the time complexity of binary search?",
                20,
                Arrays.asList("O(n)", "O(log n)", "O(n^2)", "O(1)"),
                "O(log n)"
        ));
        dsQuiz.addQuestion(new MultipleChoiceQuestion(
                "Which data structure uses LIFO principle?",
                20,
                Arrays.asList("Queue", "Stack", "Array", "Tree"),
                "Stack"
        ));
        quizManager.addQuiz(dsQuiz);

        System.out.println("\n✓ Sample data initialized successfully!");
        System.out.println("  - " + allUsers.size() + " users created");
        System.out.println("  - " + lessonManager.getLessons().size() + " lessons loaded");
        System.out.println("  - " + quizManager.getQuizzes().size() + " quizzes available");
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("\nEmail: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User authenticatedUser = null;
        for (User user : allUsers) {
            if (user.getEmail().equalsIgnoreCase(email) && user.login(password)) {
                authenticatedUser = user;
                break;
            }
        }

        if (authenticatedUser != null) {
            System.out.println("\n✓ Login successful! Welcome, " + authenticatedUser.getName());

            // Route to appropriate menu based on user type
            if (authenticatedUser instanceof Student) {
                StudentMenuManager studentMenu = new StudentMenuManager(
                        authenticatedUser, quizManager, progressTracker,
                        lessonManager, recommendationEngine
                );
                studentMenu.displayMenu();
            } else if (authenticatedUser instanceof Tutor) {
                TutorMenuManager tutorMenu = new TutorMenuManager(
                        authenticatedUser, quizManager, lessonManager
                );
                tutorMenu.displayMenu();
            } else if (authenticatedUser instanceof Admin) {
                AdminMenuManager adminMenu = new AdminMenuManager(authenticatedUser);
                adminMenu.displayMenu();
            }
        } else {
            System.out.println("\n✗ Invalid credentials. Please try again.");
        }
    }

    private static void displaySampleCredentials() {
        System.out.println("\n╔═════════════════════════════════════════════════════════╗");
        System.out.println("║              SAMPLE LOGIN CREDENTIALS                   ║");
        System.out.println("╠═════════════════════════════════════════════════════════╣");
        System.out.println("║ STUDENTS:                                               ║");
        System.out.println("║   Email: john@student.edu    | Password: pass123        ║");
        System.out.println("║   Email: jane@student.edu    | Password: pass123        ║");
        System.out.println("║   Email: mike@student.edu    | Password: pass123        ║");
        System.out.println("║                                                         ║");
        System.out.println("║ TUTOR:                                                  ║");
        System.out.println("║   Email: maria@tutor.edu     | Password: tutor123       ║");
        System.out.println("║                                                         ║");
        System.out.println("║ ADMIN:                                                  ║");
        System.out.println("║   Email: admin@system.edu    | Password: admin123       ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
    }

    /**
     * Loads registered students from CSV file and adds them to the system
     */
    private static void loadRegisteredStudents() {
        List<Student> registeredStudents = StudentRegistration.loadStudentsFromCSV();

        for (Student student : registeredStudents) {
            // Check if student is not already in allUsers (avoid duplicates)
            boolean exists = false;
            for (User user : allUsers) {
                if (user.getEmail().equalsIgnoreCase(student.getEmail())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                allUsers.add(student);
            }
        }
    }

    /**
     * Handles student registration process
     * @param scanner Scanner for user input
     */
    private static void handleRegistration(Scanner scanner) {
        Student newStudent = StudentRegistration.showRegistrationForm(scanner);

        if (newStudent != null) {
            // Add the new student to the system
            allUsers.add(newStudent);

            // Optionally, add to admin's system users list if admin exists
            for (User user : allUsers) {
                if (user instanceof Admin) {
                    ((Admin) user).addUser(newStudent);
                    break;
                }
            }

            System.out.println("\n✓ You can now login with your credentials!");
        } else {
            System.out.println("\n✗ Registration failed. Please try again.");
        }
    }
}