import java.util.Scanner;

public class SwimmingSchoolTest {
    public static void main(String[] args) {
        SwimmingSchool swimmingSchool = new SwimmingSchool();

        // Adding coaches
        swimmingSchool.addCoach("Coach A", "coachA@gmail.com", "Certified Swimming Instructor");
        swimmingSchool.addCoach("Coach B", "coachB@gmail.com", "Advanced Lifesaving Techniques");
        swimmingSchool.addCoach("Coach C", "coachC@gmail.com", "Water Safety Expert");
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
}
