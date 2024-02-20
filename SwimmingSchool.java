import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class SwimmingSchool {
    private Map<String, Lesson> lessonsByTime;
    private Map<Integer, List<Lesson>> lessonsByGrade;
    private Map<String, List<Lesson>> lessonsByCoach;
    private Map<String, List<Lesson>> lessonsByDay;
    private List<Learner> learners;
    private List<Coach> coaches;
    private List<Review> reviews;

    public SwimmingSchool() {
        this.lessonsByTime = new HashMap<>();
        this.lessonsByGrade = new HashMap<>();
        this.lessonsByCoach = new HashMap<>();
        this.learners = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public void addLesson(String coachName, String className, LessonTime lessonTime, int gradeLevel, int capacity) {
        // Create a new lesson
        Lesson lesson = new Lesson(coachName, className, lessonTime, gradeLevel, capacity, lessonsByCoach);

        // Add the lesson to the lessonsByTime map
        lessonsByTime.put(lessonTime.toString(), lesson);

        // Add the lesson to the lessonsByGrade map
        lessonsByGrade.computeIfAbsent(gradeLevel, k -> new ArrayList<>()).add(lesson);

        // Add the lesson to the lessonsByCoach map under the coach's name
        lessonsByCoach.computeIfAbsent(coachName, k -> new ArrayList<>()).add(lesson);
    }

    public void addLearner(String name, String gender, int age, String emergencyContact, int currentGradeLevel) {
        Learner learner = new Learner(name, gender, age, emergencyContact, currentGradeLevel);
        learners.add(learner);
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void addReview(String learnerName, int rating, String comments) {
        Review review = new Review(learnerName, rating, comments);
        reviews.add(review);
    }

    public List<Lesson> getLessonsByDay(String day) {
        List<Lesson> lessons = new ArrayList<>();
        for (Lesson lesson : lessonsByTime.values()) {
            if (lesson.getLessonTime().getDay().equalsIgnoreCase(day)) {
                lessons.add(lesson);
            }
        }
        return lessons;
    }

    public List<Lesson> getLessonsByGrade(int gradeLevel) {
        return lessonsByGrade.getOrDefault(gradeLevel, new ArrayList<>());
    }

    public List<Lesson> getLessonsByCoach(String coachName) {
        return lessonsByCoach.getOrDefault(coachName, new ArrayList<>());
    }

    public void addCoach(String name, String contactInfo, String qualifications) {
        Coach coach = new Coach(name, contactInfo, qualifications);
        coaches.add(coach);
    }


    public void bookLesson(String learnerName, LessonTime lessonTime) {
        // Find the lesson corresponding to the given lessonTime
        Lesson lesson = lessonsByTime.get(lessonTime.toString());
        if (lesson != null) {
            lesson.bookLesson(learnerName);
        } else {
            System.out.println("Lesson not found for the given time.");
        }
    }

    public void cancelBooking(String learnerName, LessonTime lessonTime) {
        // Find the lesson corresponding to the given lessonTime
        Lesson lesson = lessonsByTime.get(lessonTime.toString());
        if (lesson != null) {
            lesson.cancelBooking(learnerName);
        } else {
            System.out.println("Lesson not found for the given time.");
        }
    }

    public double getAverageRatingForCoach(String coachName) {
        int totalRating = 0;
        int numberOfReviews = 0;
        for (Review review : reviews) {
            if (review.getLearnerName().equalsIgnoreCase(coachName)) {
                totalRating += review.getRating();
                numberOfReviews++;
            }
        }
        return numberOfReviews > 0 ? (double) totalRating / numberOfReviews : 0;
    }

    public Map<String, Integer> generateLessonAttendanceReport() {
        Map<String, Integer> attendanceStatistics = new HashMap<>();
        for (Lesson lesson : lessonsByTime.values()) {
            int booked = lesson.getBookedLearners().size();
            attendanceStatistics.put(lesson.getLessonTime().toString(), booked);
        }
        return attendanceStatistics;
    }

    public void printAttendanceReport() {
        Map<String, Integer> attendanceStatistics = generateLessonAttendanceReport();
        System.out.println("Attendance Report:");
        for (Map.Entry<String, Integer> entry : attendanceStatistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " learners");
        }
    }

    public void printCoachRatings() {
        System.out.println("Coach Ratings:");
        for (Coach coach : coaches) {
            double averageRating = getAverageRatingForCoach(coach.getName());
            System.out.println(coach.getName() + ": " + averageRating);
        }
    }

    public void viewTimetable(Scanner scanner) {
        System.out.println("View timetable by:");
        System.out.println("1. Day");
        System.out.println("2. Grade Level");
        System.out.println("3. Coach's Name"); // Added option to view by coach's name
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter day (Monday/Wednesday/Friday/Saturday): ");
                String day = scanner.next();
                List<Lesson> lessons = getLessonsByDay(day);
                for (Lesson lesson : lessons) {
                    System.out.println("Coach: " + lesson.getCoachName() +
                                       ", Class: " + lesson.getClassName() +
                                       ", Time: " + lesson.getLessonTime() +
                                       ", Grade Level: " + lesson.getGradeLevel());
                }
                break;
            case 2:
                System.out.print("Enter grade level (1-5): ");
                int gradeLevel = scanner.nextInt();
                List<Lesson> gradeLessons = getLessonsByGrade(gradeLevel);
                for (Lesson lesson : gradeLessons) {
                    System.out.println("Coach: " + lesson.getCoachName() +
                                       ", Class: " + lesson.getClassName() +
                                       ", Time: " + lesson.getLessonTime() +
                                       ", Grade Level: " + lesson.getGradeLevel());
                }
                break;
            case 3:
                System.out.print("Enter coach's name: ");
                String coachName = scanner.next();
                List<Lesson> coachLessons = viewTimetableByCoach(coachName); // Modified to use the viewTimetableByCoach method
                if (!coachLessons.isEmpty()) {
                    System.out.println("Lessons for Coach " + coachName + ":");
                    for (Lesson lesson : coachLessons) {
                        System.out.println("Coach: " + lesson.getCoachName() + ", Class: " + lesson.getClassName() + ", Time: " + lesson.getLessonTime() + ", Grade Level: " + lesson.getGradeLevel()); // Modified to include class name
                    }
                } else {
                    System.out.println("No lessons found for coach: " + coachName);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }


    public List<Lesson> viewTimetableByDay(String day) {
        return lessonsByDay.getOrDefault(day.toUpperCase(), new ArrayList<>());
    }

    // View timetable by grade level
    public List<Lesson> viewTimetableByGradeLevel(int gradeLevel) {
        return lessonsByGrade.getOrDefault(gradeLevel, new ArrayList<>());
    }

    // View timetable by coach's name
    public List<Lesson> viewTimetableByCoach(String coachName) {
        return lessonsByCoach.getOrDefault(coachName, new ArrayList<>());
    }

    public void printLearnerInformation() {
        System.out.println("Learner Information:");
        for (Learner learner : learners) {
            System.out.println(learner);
        }
    }

    public void printCoachInformation() {
        System.out.println("Coach Information:");
        for (Coach coach : coaches) {
            System.out.println(coach);
        }
    }
    
    public static void main(String[] args) {
        SwimmingSchool swimmingSchool = new SwimmingSchool();

        // Adding coaches
        swimmingSchool.addCoach("Coach A", "coachA@gmail.com", "Certified Swimming Instructor");
        swimmingSchool.addCoach("Coach B", "coachB@gmail.com", "Advanced Lifesaving Techniques");
        swimmingSchool.addCoach("Coach C", "coachC@gmail.com", "Water Safety Expert");

        // Adding lessons for each day
        swimmingSchool.addLessonsForMonday();
        swimmingSchool.addLessonsForWednesday();
        swimmingSchool.addLessonsForFriday();
        swimmingSchool.addLessonsForSaturday();

        // Adding some learners
        swimmingSchool.addLearner("Alice", "Female", 7, "123456789", 1);
        swimmingSchool.addLearner("Bob", "Male", 8, "987654321", 2);
        swimmingSchool.addLearner("Charlie", "Male", 9, "456789123", 3);

        // Main menu
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to Hatfield Junior Swimming School");
            System.out.println("1. View timetable");
            System.out.println("2. Book lesson");
            System.out.println("3. Cancel booking");
            System.out.println("4. Write review");
            System.out.println("5. Print learner information");
            System.out.println("6. Print coach information");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
            	case 1:
            		swimmingSchool.viewTimetable(scanner);
            		break;
                case 2:
                    swimmingSchool.bookLesson(scanner);
                    break;
                case 3:
                    swimmingSchool.cancelBooking(scanner);
                    break;
                case 4:
                    swimmingSchool.writeReview(scanner);
                    break;
                case 5:
                    swimmingSchool.printLearnerInformation();
                    break;
                case 6:
                    swimmingSchool.printCoachInformation();
                    break;
                case 0:
                    System.out.println("Exiting program. Thank you for using Hatfield Junior Swimming School.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }   
    public void bookLesson(Scanner scanner) throws InputMismatchException {
        try {
            System.out.print("Enter learner's name: ");
            String learnerName = scanner.next();
            System.out.print("Enter lesson day (Monday/Wednesday/Friday/Saturday): ");
            String day = scanner.next();
            System.out.print("Enter lesson time slot (e.g., 4PM): ");
            String timeSlot = scanner.next().toUpperCase();
            LessonTime lessonTime = LessonTime.valueOf(day.toUpperCase() + "_" + timeSlot);
            bookLesson(learnerName, lessonTime);
        } catch (NoSuchElementException e) {
            System.out.println("Input error: Please enter all required information.");
        }
    }

    public void cancelBooking(Scanner scanner) throws InputMismatchException {
        try {
            System.out.print("Enter learner's name: ");
            String learnerName = scanner.next();
            System.out.print("Enter lesson day (Monday/Wednesday/Friday/Saturday): ");
            String day = scanner.next();
            System.out.print("Enter lesson time slot (e.g., 4PM): ");
            String timeSlot = scanner.next().toUpperCase();
            LessonTime lessonTime = LessonTime.valueOf(day.toUpperCase() + "_" + timeSlot);
            cancelBooking(learnerName, lessonTime);
        } catch (NoSuchElementException e) {
            System.out.println("Input error: Please enter all required information.");
        }
    }

    public void writeReview(Scanner scanner) throws InputMismatchException {
        try {
            System.out.print("Enter learner's name: ");
            String learnerName = scanner.next();
            System.out.print("Enter rating (1-5): ");
            int rating = scanner.nextInt();
            System.out.print("Enter comments: ");
            String comments = scanner.next();
            addReview(learnerName, rating, comments);
        } catch (NoSuchElementException e) {
            System.out.println("Input error: Please enter all required information.");
        }
    }



    private void addLessonsForMonday() {
        addLesson("Coach A", "Swimming Monday Lesson 1", LessonTime.MONDAY_4PM, 1, 4);
        addLesson("Coach B", "Swimming Monday Lesson 2", LessonTime.MONDAY_5PM, 2, 4);
        addLesson("Coach C", "Swimming Monday Lesson 3", LessonTime.MONDAY_6PM, 3, 4);
    }

    private void addLessonsForWednesday() {
        addLesson("Coach A", "Swimming Wednesday Lesson 1", LessonTime.WEDNESDAY_4PM, 2, 4);
        addLesson("Coach B", "Swimming Wednesday Lesson 2", LessonTime.WEDNESDAY_5PM, 3, 4);
        addLesson("Coach C", "Swimming Wednesday Lesson 3", LessonTime.WEDNESDAY_6PM, 4, 4);
    }

    private void addLessonsForFriday() {
        addLesson("Coach A", "Swimming Friday Lesson 1", LessonTime.FRIDAY_4PM, 3, 4);
        addLesson("Coach B", "Swimming Friday Lesson 2", LessonTime.FRIDAY_5PM, 4, 4);
        addLesson("Coach C", "Swimming Friday Lesson 3", LessonTime.FRIDAY_6PM, 5, 4);
    }

    private void addLessonsForSaturday() {
        addLesson("Coach A", "Swimming Saturday Lesson 1", LessonTime.SATURDAY_2PM, 1, 4);
        addLesson("Coach B", "Swimming Saturday Lesson 2", LessonTime.SATURDAY_3PM, 2, 4);
    }
}