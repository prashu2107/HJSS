public enum LessonTime {
    MONDAY_4PM("Monday"),
    MONDAY_5PM("Monday"),
    MONDAY_6PM("Monday"),
    WEDNESDAY_4PM("Wednesday"),
    WEDNESDAY_5PM("Wednesday"),
    WEDNESDAY_6PM("Wednesday"),
    FRIDAY_4PM("Friday"),
    FRIDAY_5PM("Friday"),
    FRIDAY_6PM("Friday"),
    SATURDAY_2PM("Saturday"),
    SATURDAY_3PM("Saturday");

    private String day;

    LessonTime(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
