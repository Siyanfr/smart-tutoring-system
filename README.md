# Smart Tutoring System - OOP Project

## Project Overview
A console-based Smart Tutoring System built with Java that demonstrates object-oriented programming principles including inheritance, polymorphism, encapsulation, and abstraction.

## Features

### Registration System (NEW):
- **Student self-registration** with validation
- **CSV file persistence** for all registered students
- **Duplicate email prevention**
- **Automatic unique ID generation**
- **Password confirmation** during registration
- **Learning style selection** (Visual, Auditory, Kinesthetic)

### For Students:
- Browse and study lessons
- Take multiple-choice and true/false quizzes
- Track progress and view performance reports
- Get personalized learning recommendations
- Enroll in courses

### For Tutors:
- Create and manage quizzes
- Upload new lessons
- Review student performance
- Generate personalized recommendations for students
- View assigned students

### For Admins:
- Add and remove users from the system
- View all system users
- Monitor system-wide data and statistics

## File Structure

```
SmartTutoringSystem/
├── User.java                      (Base user class)
├── RecommendationProvider.java    (Interface for recommendations)
├── Student.java                   (Student user class)
├── Tutor.java                     (Tutor user class)
├── Admin.java                     (Administrator class)
├── Question.java                  (Abstract question class)
├── MultipleChoiceQuestion.java    (MC question implementation)
├── TrueFalseQuestion.java         (T/F question implementation)
├── Quiz.java                      (Abstract quiz class)
├── MultipleChoiceQuiz.java        (MC quiz implementation)
├── TrueFalseQuiz.java             (T/F quiz implementation)
├── Lesson.java                    (Lesson class)
├── Progress.java                  (Progress tracking class)
├── QuizManager.java               (Quiz management)
├── QuizNotFoundException.java     (Custom exception)
├── LessonManager.java             (Lesson management)
├── ProgressTracker.java           (Progress tracking system)
├── RecommendationEngine.java      (AI recommendation system)
├── MenuManager.java               (Abstract menu class)
├── StudentMenuManager.java        (Student menu interface)
├── TutorMenuManager.java          (Tutor menu interface)
├── AdminMenuManager.java          (Admin menu interface)
└── SmartTutoringSystem.java      (Main application)
```

## How to Compile and Run

### Step 1: Compile all Java files
```bash
javac *.java
```

### Step 2: Run the main application
```bash
java SmartTutoringSystem
```

## Sample Login Credentials

### Students:
- **Email:** john@student.edu | **Password:** pass123
- **Email:** jane@student.edu | **Password:** pass123
- **Email:** mike@student.edu | **Password:** pass123

### Tutor:
- **Email:** maria@tutor.edu | **Password:** tutor123

### Admin:
- **Email:** admin@system.edu | **Password:** admin123

## Usage Guide

### As a Student:
1. Login with student credentials
2. Browse available lessons
3. Study lessons (automatically marked as completed)
4. Take quizzes (manually or get personalized recommendations)
5. View your progress report
6. Get learning recommendations based on your performance

### As a Tutor:
1. Login with tutor credentials
2. Create new quizzes (multiple choice or true/false)
3. Upload new lessons to the system
4. Review assigned students' performance
5. Generate personalized recommendations for students
6. View all created quizzes and assigned students

### As an Admin:
1. Login with admin credentials
2. Add new users (students, tutors, or admins)
3. Remove existing users
4. View all system users
5. Monitor system statistics

## OOP Concepts Demonstrated

### 1. **Inheritance**
- `Student`, `Tutor`, and `Admin` extend `User`
- `MultipleChoiceQuiz` and `TrueFalseQuiz` extend `Quiz`
- `MultipleChoiceQuestion` and `TrueFalseQuestion` extend `Question`
- `StudentMenuManager`, `TutorMenuManager`, and `AdminMenuManager` extend `MenuManager`

### 2. **Polymorphism**
- Method overriding in `evaluateQuiz()` and `checkAnswer()`
- Method overriding in `displayMenu()` for different user types
- Interface implementation in `Tutor` implementing `RecommendationProvider`

### 3. **Encapsulation**
- Private/protected instance variables with public getters
- Password hashing for security
- Controlled access to user data

### 4. **Abstraction**
- Abstract classes: `User`, `Question`, `Quiz`, `MenuManager`
- Interface: `RecommendationProvider`
- Abstract methods implemented by subclasses

### 5. **Exception Handling**
- Custom `QuizNotFoundException` for quiz retrieval errors
- Try-catch blocks for input validation

## Sample Data Included

### Lessons:
1. Introduction to Java (Easy)
2. Object-Oriented Programming Basics (Medium)
3. Data Structures Introduction (Medium)
4. Advanced Algorithms (Hard)

### Quizzes:
1. Java Basics (Easy) - Multiple Choice
2. OOP Concepts (Medium) - Multiple Choice
3. Programming Facts (Easy) - True/False
4. Data Structures (Hard) - Multiple Choice

## Key Classes and Their Responsibilities

| Class | Responsibility |
|-------|----------------|
| `User` | Base authentication and user information |
| `Student` | Student-specific functionality and progress tracking |
| `Tutor` | Quiz creation and student management |
| `Admin` | System administration and user management |
| `QuizManager` | Centralized quiz storage and retrieval |
| `LessonManager` | Lesson storage and browsing |
| `ProgressTracker` | Track and report student performance |
| `RecommendationEngine` | Generate personalized learning recommendations |

## Future Enhancements (Out of Scope)
- File handling for persistent data storage (CSV/JSON)
- Advanced AI/ML recommendation algorithms
- Real-time collaboration features
- Video tutoring integration
- Email verification and 2FA
- Database integration
- GUI implementation

## Project Team
- Angeles, Jaz Kiervy D.
- Carosus, Cean A.
- Oliveros, Michael Francis Noel M.
- Panganiban, Allen David C.

**Institution:** Holy Angel University - School of Computing  
**Date:** October 2025

## Notes
- This is a prototype implementation with sample data
- Passwords are hashed using simple hashCode() (use BCrypt in production)
- All data is stored in memory and will reset when the program closes
- Input validation is basic; handle edge cases as needed

## License
Educational project for OOP demonstration purposes.
