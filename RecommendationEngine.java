// RecommendationEngine.java - Generates learning recommendations
public class RecommendationEngine {
    private ProgressTracker progressTracker;

    public RecommendationEngine(ProgressTracker progressTracker) {
        this.progressTracker = progressTracker;
    }

    public void generateRecommendations(Student student) {
        System.out.println("\n=== Smart Learning Recommendations ===");
        System.out.println("Student: " + student.getName());

        double progress = student.getOverallProgress();
        int attempts = student.getQuizAttempts();

        // Progress-based recommendations
        if (progress < 20) {
            System.out.println("\n📚 Getting Started:");
            System.out.println("  • Start with foundational lessons");
            System.out.println("  • Take easy quizzes to build confidence");
            System.out.println("  • Study at least 15 minutes daily");
        } else if (progress < 50) {
            System.out.println("\n📈 Building Momentum:");
            System.out.println("  • Continue with intermediate topics");
            System.out.println("  • Review areas where you scored below 70%");
            System.out.println("  • Try medium difficulty quizzes");
        } else if (progress < 80) {
            System.out.println("\n🎯 Advanced Learning:");
            System.out.println("  • Challenge yourself with harder topics");
            System.out.println("  • Focus on specialized subjects");
            System.out.println("  • Attempt complex problem-solving quizzes");
        } else {
            System.out.println("\n⭐ Expert Level:");
            System.out.println("  • Excellent progress! Maintain your momentum");
            System.out.println("  • Help other students as a peer tutor");
            System.out.println("  • Explore advanced elective courses");
        }

        // Activity-based recommendations
        if (attempts < 3) {
            System.out.println("\n💡 Tip: Take more quizzes to better assess your knowledge!");
        } else if (attempts > 10) {
            System.out.println("\n💡 Great job staying active! Keep up the practice!");
        }

        // Learning style recommendations
        System.out.println("\nBased on your learning style (" + student.getLearningStyle() + "):");
        switch (student.getLearningStyle().toLowerCase()) {
            case "visual":
                System.out.println("  • Look for diagram-heavy lessons");
                System.out.println("  • Use mind maps for complex topics");
                break;
            case "auditory":
                System.out.println("  • Read lessons aloud");
                System.out.println("  • Discuss topics with peers");
                break;
            case "kinesthetic":
                System.out.println("  • Practice hands-on exercises");
                System.out.println("  • Apply concepts through projects");
                break;
            default:
                System.out.println("  • Explore different study techniques");
        }

        System.out.println("=====================================\n");
    }
}