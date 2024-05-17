import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int unitsEnrolled;
    private List<Course> enrolledCourses;
    private static final int maxUnits = 20;
    private String dob;
    private int age;

    public Student(String name, String dob, int age) {
        this.name = name;
        this.unitsEnrolled = 0;
        this.enrolledCourses = new ArrayList<Course>();
        this.dob = dob;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDob() {
        return dob;
    }

    public int getUnitsEnrolled() {
        return unitsEnrolled;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public boolean enrollCourse(Course course) {
        if (unitsEnrolled + course.getUnits() <= maxUnits) {
            // Check prerequisites
            if (course.getPrerequisites() != null) {
                for (Course prerequisite : course.getPrerequisites()) {
                    if (!enrolledCourses.contains(prerequisite) || !prerequisite.isComplete()) {
                        System.out.println("Cannot enroll in course " + course + ". Prerequisite " + prerequisite
                                + " not satisfied.");
                        return false;
                    }
                }
            }

            if (course.getTime() != null) {
                for (Course enrolledCourse : enrolledCourses) {
                    if ((!enrolledCourse.isComplete()) && isTimeOverlap(enrolledCourse.getTime(), course.getTime())) {
                        System.out
                                .println("Cannot enroll in course " + course + ". Time overlap with enrolled course.");
                        return false;
                    }
                }
            }

            unitsEnrolled += course.getUnits();
            enrolledCourses.add(course);
            return true;
        } else {
            System.out.println("Cannot enroll in course " + course + ". Exceeds maximum units.");
            return false;
        }
    }

    public void dropCourse(Course course) {
        unitsEnrolled -= course.getUnits();
        enrolledCourses.remove(course);
    }

    private boolean isTimeOverlap(Time time1, Time time2) {

        String[] startTime1 = time1.getStartTime().split(":");
        String[] endTime1 = time1.getEndTime().split(":");
        String[] startTime2 = time2.getStartTime().split(":");
        String[] endTime2 = time2.getEndTime().split(":");

        int startHour1 = Integer.parseInt(startTime1[0]);
        int startMinute1 = Integer.parseInt(startTime1[1]);
        int endHour1 = Integer.parseInt(endTime1[0]);
        int endMinute1 = Integer.parseInt(endTime1[1]);

        int startHour2 = Integer.parseInt(startTime2[0]);
        int startMinute2 = Integer.parseInt(startTime2[1]);
        int endHour2 = Integer.parseInt(endTime2[0]);
        int endMinute2 = Integer.parseInt(endTime2[1]);

        if ((startHour1 < endHour2 || (startHour1 == endHour2 && startMinute1 < endMinute2))
                && (startHour2 < endHour1 || (startHour2 == endHour1 && startMinute2 < endMinute1))) {
            return true;
        }
        return false;
    }

    public String printSchedule() {
        StringBuilder schedule = new StringBuilder();
        for (Course course : enrolledCourses) {
            if (!course.isComplete()) {
                schedule.append(course.getClassName())
                        .append(" - ")
                        .append(course.getClass().getSimpleName())
                        .append(" at ")
                        .append(course.getTime().getStartTime())
                        .append(" - ")
                        .append(course.getTime().getEndTime())
                        .append("\n");
            }
        }
        return schedule.toString();
    }

    public void finishAllCourses() {
        for (Course course : enrolledCourses) {
            course.finishCourse();
        }
        unitsEnrolled = 0;
    }

}
