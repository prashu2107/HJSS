public class LearnerTest {
    public static void main(String[] args) {
        // Create some learner instances
        Learner learner1 = new Learner("Alice", "Female", 7, "123456789", 1);
        Learner learner2 = new Learner("Bob", "Male", 8, "987654321", 2);

        // Print learner information
        System.out.println("Learner 1:");
        System.out.println(learner1);
        System.out.println();

        System.out.println("Learner 2:");
        System.out.println(learner2);
    }
}