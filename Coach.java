public class Coach {
    private String name;
    private String contactInfo;
    private String qualifications;

    public Coach(String name, String contactInfo, String qualifications) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.qualifications = qualifications;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Coach Name: " + name + ", Contact Info: " + contactInfo + ", Qualifications: " + qualifications;
    }
}