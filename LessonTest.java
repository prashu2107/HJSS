import java.util.HashMap;

public class LessonTest {
    public static void main(String[] args) {
        // Create a sample lesson
        LessonTime lessonTime = LessonTime.MONDAY_4PM;
        Lesson lesson = new Lesson("Coach A", "Swimming1", lessonTime, 1, 4, new HashMap<>());

        // Book some learners for the lesson
        lesson.bookLesson("Alice");
        lesson.bookLesson("Bob");

        // Cancel booking for one learner
        lesson.cancelBooking("Alice");

        // Print lesson details
        System.out.println("Lesson Details:");
        System.out.println(lesson);
        System.out.println("Booked Learners: " + lesson.getBookedLearners());
    }
}
