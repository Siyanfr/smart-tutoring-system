// LessonManager.java - Manages all lessons in the system
import java.util.ArrayList;
import java.util.List;

public class LessonManager {
    private List<Lesson> lessons;

    public LessonManager() {
        this.lessons = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        System.out.println("Lesson added: " + lesson.getTitle());
    }

    public void browseLessons() {
        System.out.println("\n=== Available Lessons ===");
        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            System.out.println((i + 1) + ". " + lesson.getTitle() +
                    " (" + lesson.getSubject() + " - " + lesson.getDifficultyLevel() + ")");
        }
    }

    public Lesson getLesson(int index) {
        if (index >= 0 && index < lessons.size()) {
            return lessons.get(index);
        }
        return null;
    }

    // Getter
    public List<Lesson> getLessons() { return lessons; }
}