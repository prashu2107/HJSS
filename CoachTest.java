public class CoachTest {
    public static void main(String[] args) {
        // Create some coach instances
        Coach coach1 = new Coach("Coach A", "coachA@gmail.com", "Certified Swimming Instructor");
        Coach coach2 = new Coach("Coach B", "coachB@gmail.com", "Advanced Lifesaving Techniques");

        // Print coach information
        System.out.println("Coach 1:");
        System.out.println(coach1);
        System.out.println();

        System.out.println("Coach 2:");
        System.out.println(coach2);
    }
}