import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Course {
    private int units;
    private List<Course> prerequisites;
    private Time time;
    private String className;
    private boolean complete = false;

    public Course(int units, Time time, String className) {
        this.units = units;
        this.prerequisites = new ArrayList<Course>();
        this.time = time;
        this.className = className;
    }

    public Course(int units, List<Course> prerequisites, Time time, String className) {
        this.units = units;
        this.prerequisites = prerequisites;
        this.time = time;
        this.className = className;
    }

    public Course(String className) {
        this.className = className;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isComplete() {
        return complete;
    }

    public void finishCourse() {
        complete = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return Objects.equals(className, course.className);
    }

    @Override
    public String toString() {
        return className;
    }
    
}
