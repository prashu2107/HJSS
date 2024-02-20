import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lesson {
    private String coachName;
    private String className;
    private LessonTime lessonTime;
    private int gradeLevel;
    private int capacity;
    private List<String> bookedLearners;

    public Lesson(String coachName, String className, LessonTime lessonTime, int gradeLevel, int capacity, Map<String, List<Lesson>> lessonsByCoach) {
        this.coachName = coachName;
        this.className = className;
        this.lessonTime = lessonTime;
        this.gradeLevel = gradeLevel;
        this.capacity = capacity;
        this.bookedLearners = new ArrayList<>();
        
    }

    // Getters and setters for other fields

    public void bookLesson(String learnerName) {
        if (bookedLearners.size() < capacity) {
            bookedLearners.add(learnerName);
            System.out.println("Lesson booked for " + learnerName + " in class " + className); // Updated to include class name
        } else {
            System.out.println("Lesson is full, cannot book for " + learnerName);
        }
    }

    public void cancelBooking(String learnerName) {
        if (bookedLearners.remove(learnerName)) {
            System.out.println("Booking canceled for " + learnerName);
        } else {
            System.out.println(learnerName + " did not have a booking for this lesson");
        }
    }
    
    public String getCoachName() {
        return coachName;
    }
    
    public String getClassName() {
        return className;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public List<String> getBookedLearners() {
        return bookedLearners;
    }

    public LessonTime getLessonTime() {
        return lessonTime;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    public String toString() {
        return "Coach: " + coachName + ", Class: " + className + ", Time: " + lessonTime + ", Grade Level: " + gradeLevel;
    }
}
