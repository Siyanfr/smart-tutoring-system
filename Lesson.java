// Lesson.java - Lesson class for course content
public class Lesson {
    private int lessonId;
    private String title;
    private String subject;
    private String content;
    private String difficultyLevel;

    public Lesson(int lessonId, String title, String subject, String content, String difficultyLevel) {
        this.lessonId = lessonId;
        this.title = title;
        this.subject = subject;
        this.content = content;
        this.difficultyLevel = difficultyLevel;
    }

    public void displayLesson() {
        System.out.println("\n=== Lesson: " + title + " ===");
        System.out.println("Subject: " + subject);
        System.out.println("Difficulty: " + difficultyLevel);
        System.out.println("\nContent:");
        System.out.println(content);
        System.out.println("\n=====================================");
    }

    // Getters
    public int getLessonId() { return lessonId; }
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public String getContent() { return content; }
    public String getDifficultyLevel() { return difficultyLevel; }
}