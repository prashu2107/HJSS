public class Learner {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int currentGradeLevel;

    public Learner(String name, String gender, int age, String emergencyContact, int currentGradeLevel) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.currentGradeLevel = currentGradeLevel;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Gender: " + gender + ", Age: " + age + ", Emergency Contact: " + emergencyContact + ", Grade Level: " + currentGradeLevel;
    }
}
