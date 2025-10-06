// StudentRegistration.java - Handles student registration with CSV file persistence
import java.io.*;
import java.util.*;

public class StudentRegistration {
    private static final String CSV_FILE = "students.csv";
    private static final String CSV_HEADER = "userId,name,email,password,learningStyle,enrolledCourses";

    /**
     * Registers a new student and saves to CSV file
     * @param name Student's full name
     * @param email Student's email address
     * @param password Student's password (will be hashed)
     * @param learningStyle Student's preferred learning style (Visual/Auditory/Kinesthetic)
     * @return The newly created Student object, or null if registration fails
     */
    public static Student registerStudent(String name, String email, String password, String learningStyle) {
        try {
            // Check if email already exists
            if (isEmailRegistered(email)) {
                System.out.println("✗ Error: Email already registered!");
                return null;
            }

            // Generate new unique user ID
            int newUserId = generateUniqueUserId();

            // Create new student object
            Student newStudent = new Student(newUserId, name, email, password, learningStyle);

            // Save to CSV file
            if (saveStudentToCSV(newStudent)) {
                System.out.println("\n✓ Registration successful!");
                System.out.println("  User ID: " + newUserId);
                System.out.println("  Name: " + name);
                System.out.println("  Email: " + email);
                return newStudent;
            } else {
                System.out.println("✗ Error: Failed to save student data.");
                return null;
            }

        } catch (Exception e) {
            System.out.println("✗ Registration error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Loads all students from CSV file
     * @return List of Student objects loaded from file
     */
    public static List<Student> loadStudentsFromCSV() {
        List<Student> students = new ArrayList<>();
        File file = new File(CSV_FILE);

        // Create file with header if it doesn't exist
        if (!file.exists()) {
            createCSVFile();
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                // Skip header line
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                // Parse CSV line
                String[] data = line.split(",");
                if (data.length >= 5) {
                    int userId = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String email = data[2].trim();
                    String passwordHash = data[3].trim();
                    String learningStyle = data[4].trim();

                    // Create student with hashed password directly (already hashed in CSV)
                    Student student = new Student(userId, name, email, "dummy", learningStyle);
                    // Manually set the password hash to avoid re-hashing
                    student.setPasswordHashDirectly(passwordHash);

                    // Load enrolled courses if present
                    if (data.length > 5 && !data[5].trim().isEmpty()) {
                        String[] courseIds = data[5].trim().split(";");
                        for (String courseId : courseIds) {
                            if (!courseId.isEmpty()) {
                                student.enrollInCourse(Integer.parseInt(courseId));
                            }
                        }
                    }

                    students.add(student);
                }
            }

            System.out.println("✓ Loaded " + students.size() + " student(s) from CSV file.");

        } catch (IOException e) {
            System.out.println("⚠ Warning: Could not load students from CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("⚠ Warning: CSV file format error: " + e.getMessage());
        }

        return students;
    }

    /**
     * Saves a student to the CSV file
     * @param student The student to save
     * @return true if save was successful, false otherwise
     */
    private static boolean saveStudentToCSV(Student student) {
        try (FileWriter fw = new FileWriter(CSV_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Format: userId,name,email,passwordHash,learningStyle,enrolledCourses
            StringBuilder enrolledCourses = new StringBuilder();
            for (int i = 0; i < student.getEnrolledCourseIds().size(); i++) {
                enrolledCourses.append(student.getEnrolledCourseIds().get(i));
                if (i < student.getEnrolledCourseIds().size() - 1) {
                    enrolledCourses.append(";");
                }
            }

            pw.println(student.getUserId() + "," +
                    student.getName() + "," +
                    student.getEmail() + "," +
                    student.getPasswordHash() + "," +
                    student.getLearningStyle() + "," +
                    enrolledCourses.toString());

            return true;

        } catch (IOException e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
            return false;
        }
    }

    /**
     * Updates the entire CSV file with current student data
     * Useful when student information changes (e.g., course enrollment)
     * @param students List of all students to save
     */
    public static void updateCSVFile(List<Student> students) {
        try (FileWriter fw = new FileWriter(CSV_FILE, false);
             PrintWriter pw = new PrintWriter(fw)) {

            // Write header
            pw.println(CSV_HEADER);

            // Write all student records
            for (Student student : students) {
                StringBuilder enrolledCourses = new StringBuilder();
                for (int i = 0; i < student.getEnrolledCourseIds().size(); i++) {
                    enrolledCourses.append(student.getEnrolledCourseIds().get(i));
                    if (i < student.getEnrolledCourseIds().size() - 1) {
                        enrolledCourses.append(";");
                    }
                }

                pw.println(student.getUserId() + "," +
                        student.getName() + "," +
                        student.getEmail() + "," +
                        student.getPasswordHash() + "," +
                        student.getLearningStyle() + "," +
                        enrolledCourses.toString());
            }

            System.out.println("✓ CSV file updated successfully.");

        } catch (IOException e) {
            System.out.println("Error updating CSV: " + e.getMessage());
        }
    }

    /**
     * Checks if an email is already registered
     * @param email Email to check
     * @return true if email exists, false otherwise
     */
    private static boolean isEmailRegistered(String email) {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 3 && data[2].trim().equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error checking email: " + e.getMessage());
        }

        return false;
    }

    /**
     * Generates a unique user ID by finding the highest existing ID and adding 1
     * @return A new unique user ID
     */
    private static int generateUniqueUserId() {
        int maxId = 1000; // Start from 1000 for registered students
        File file = new File(CSV_FILE);

        if (!file.exists()) {
            return maxId;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 1) {
                    int userId = Integer.parseInt(data[0].trim());
                    if (userId > maxId) {
                        maxId = userId;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error generating ID: " + e.getMessage());
        }

        return maxId + 1;
    }

    /**
     * Creates the CSV file with header if it doesn't exist
     */
    private static void createCSVFile() {
        try (FileWriter fw = new FileWriter(CSV_FILE);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(CSV_HEADER);
            System.out.println("✓ Created new students.csv file.");
        } catch (IOException e) {
            System.out.println("Error creating CSV file: " + e.getMessage());
        }
    }

    /**
     * Displays the registration form and collects user input
     * @param scanner Scanner for user input
     * @return Newly registered Student object, or null if registration fails
     */
    public static Student showRegistrationForm(Scanner scanner) {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     STUDENT REGISTRATION FORM         ║");
        System.out.println("╚═══════════════════════════════════════╝");

        try {
            // Collect student information
            System.out.print("\nFull Name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("✗ Name cannot be empty.");
                return null;
            }

            System.out.print("Email Address: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty() || !email.contains("@")) {
                System.out.println("✗ Invalid email address.");
                return null;
            }

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            if (password.length() < 6) {
                System.out.println("✗ Password must be at least 6 characters.");
                return null;
            }

            System.out.print("Confirm Password: ");
            String confirmPassword = scanner.nextLine().trim();
            if (!password.equals(confirmPassword)) {
                System.out.println("✗ Passwords do not match.");
                return null;
            }

            System.out.println("\nLearning Style Options:");
            System.out.println("1. Visual (learn best through images, diagrams, charts)");
            System.out.println("2. Auditory (learn best through listening and discussion)");
            System.out.println("3. Kinesthetic (learn best through hands-on practice)");
            System.out.print("Select your learning style (1-3): ");

            int styleChoice = Integer.parseInt(scanner.nextLine().trim());
            String learningStyle;
            switch (styleChoice) {
                case 1:
                    learningStyle = "Visual";
                    break;
                case 2:
                    learningStyle = "Auditory";
                    break;
                case 3:
                    learningStyle = "Kinesthetic";
                    break;
                default:
                    System.out.println("✗ Invalid choice. Defaulting to Visual.");
                    learningStyle = "Visual";
            }

            // Register the student
            return registerStudent(name, email, password, learningStyle);

        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input format.");
            return null;
        } catch (Exception e) {
            System.out.println("✗ Registration error: " + e.getMessage());
            return null;
        }
    }
}