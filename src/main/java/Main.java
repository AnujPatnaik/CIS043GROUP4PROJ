    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.*;

    public class Main {

        private static Student student;
        private static DefaultListModel<Course> courseListModel;
        private static JList<Course> courseJList;
        private static JTextArea scheduleTextArea;

        private static ArrayList<Course> defaultCourses = new ArrayList<Course>();
    

        public static void main(String[] args) {
            
            student = new Student("Joe Bob","01/01/01",18);

            defaultCourses.add(new Course("Calculus 1"));
            defaultCourses.add(new Course("Calculus 2"));
            defaultCourses.add(new Course("Calculus 3"));
            defaultCourses.add(new Course("Physics 4A"));
            defaultCourses.add(new Course("Physics 4B"));
            defaultCourses.add(new Course("Physics 4C"));
            defaultCourses.add(new Course("Physics 4D"));
            defaultCourses.add(new Course("Intro to Programming"));
            defaultCourses.add(new Course("Intermediate Programming"));
            defaultCourses.add(new Course("Advanced Programming"));
            defaultCourses.add(new Course("General Chemistry 1"));
            defaultCourses.add(new Course("General Chemistry 2"));
            defaultCourses.add(new Course("Organic Chemistry"));
            defaultCourses.add(new Course("Biochemistry"));

            student.enrollCourse(defaultCourses.get(0));
            student.enrollCourse(defaultCourses.get(1));
            student.finishAllCourses();

            JFrame frame = new JFrame("Course Signup App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout());

            courseListModel = new DefaultListModel<Course>();
            populateCourses();

            courseJList = new JList<Course>(courseListModel);
            courseJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane courseScrollPane = new JScrollPane(courseJList);
            courseScrollPane.setPreferredSize(new Dimension(200, 300));

            scheduleTextArea = new JTextArea();
            scheduleTextArea.setEditable(false);
            JScrollPane scheduleScrollPane = new JScrollPane(scheduleTextArea);
            scheduleScrollPane.setPreferredSize(new Dimension(350, 300));

            JButton enrollButton = new JButton("Enroll");
            enrollButton.addActionListener(new EnrollButtonListener());

            JPanel panel = new JPanel();
            panel.add(enrollButton);

            frame.add(courseScrollPane, BorderLayout.WEST);
            frame.add(scheduleScrollPane, BorderLayout.CENTER);
            frame.add(panel, BorderLayout.SOUTH);

            frame.setVisible(true);
        }

        private static void populateCourses() {
            ArrayList <Course> calc3Prereqs = new ArrayList<Course>();
            calc3Prereqs.add(defaultCourses.get(1));
            ArrayList <Course> phys4APrereqs = new ArrayList<Course>();
            phys4APrereqs.add(defaultCourses.get(0));
            ArrayList <Course> phys4CPrereqs = new ArrayList<Course>();
            phys4CPrereqs.add(defaultCourses.get(2));
            phys4CPrereqs.add(defaultCourses.get(4));
            
            courseListModel.addElement(new Course(4, new Time("09:00", "10:30"), "Calculus 1"));
            courseListModel.addElement(new Course(5, calc3Prereqs, new Time("10:00", "10:30"), "Calculus 3")); 
            courseListModel.addElement(new Course(5, phys4APrereqs, new Time("13:00", "14:30"), "Physics 4A"));
            courseListModel.addElement(new Course(5, phys4CPrereqs, new Time("14:00", "15:30"), "Physics 4C"));
            courseListModel.addElement(new Course(4, new Time("10:00", "11:30"), "Intro to Programming"));
            courseListModel.addElement(new Course(4, new Time("11:00", "12:30"), "General Chemistry 1"));

        }

        private static class EnrollButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = courseJList.getSelectedValue();
                if (selectedCourse != null) {
                    if (student.enrollCourse(selectedCourse)) {
                        updateSchedule();
                        System.out.println(selectedCourse);
                    }
                }
            }
        }

        private static void updateSchedule() {
            StringBuilder schedule = new StringBuilder();
            for (Course course : student.getEnrolledCourses()) {
                if (!course.isComplete()) {
                    schedule.append(course.getClassName())
                            .append(" - ")
                            .append(course.getTime().getStartTime())
                            .append(" to ")
                            .append(course.getTime().getEndTime())
                            .append("\n");
                }
            }
            scheduleTextArea.setText(schedule.toString());
        }
    }
