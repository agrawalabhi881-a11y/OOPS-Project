package Users;

import System.*;

public class Student extends user {
    
    private Course enrolledCourses[] = new Course[10];
    private int enrolledCourseCount;
    private int marks[] = new int[10];
    private String grades[] = new String[10];

    public Student(int userId, String name, String email, String password) {
        super(userId, name, email, password, "Student");
        this.enrolledCourseCount = 0;
        for (int i = 0; i < marks.length; i++) {
            marks[i] = -1;
            grades[i] = null;
        }
    }

    public void viewCourses() {
        if (enrolledCourseCount == 0) {
            System.out.println("No courses enrolled.");
        } else {
            System.out.println("Enrolled Courses:");
            for (int i = 0; i < enrolledCourseCount; i++) {
                System.out.println(enrolledCourses[i].getCourseName());
            }
        }
    }

    public void viewMarks() {
        if (enrolledCourseCount == 0) {
            System.out.println("No courses enrolled.");
        } else {
            System.out.println("Marks for Enrolled Courses:");
            for (int i = 0; i < enrolledCourseCount; i++) {
                if (marks[i] == -1) {
                    System.out.println(enrolledCourses[i].getCourseName() + ": Marks not available");
                } else {
                    System.out.println(enrolledCourses[i].getCourseName() + ": " + marks[i]);
                }
            }
        }
    }

    public void viewGrades() {
        if (enrolledCourseCount == 0) {
            System.out.println("No courses enrolled.");
        } else {
            System.out.println("Grades for Enrolled Courses:");
            for (int i = 0; i < enrolledCourseCount; i++) {
                if (grades[i] == null) {
                    System.out.println(enrolledCourses[i].getCourseName() + ": Grades not available");
                } else {
                    System.out.println(enrolledCourses[i].getCourseName() + ": " + grades[i]);
                }
            }
        }
    }

    public void viewSGPA() {
        if (enrolledCourseCount == 0) {
            System.out.println("No courses enrolled.");
        } else {
            double totalPoints = 0;
            int totalCredits = 0;
            for (int i = 0; i < enrolledCourseCount; i++) {
                if (grades[i] != null) {
                    int gradePoints = getGradePoints(grades[i]);
                    int courseCredits = enrolledCourses[i].getCredit();
                    totalPoints += gradePoints * courseCredits;
                    totalCredits += courseCredits;
                }
            }
            if (totalCredits > 0) {
                double sgpa = totalPoints / totalCredits;
                System.out.printf("SGPA: %.2f\n", sgpa);
            } else {
                System.out.println("SGPA not available. No graded courses.");
            }
        }
    }

    public boolean isEnrolled(int courseId) {
        for (int i = 0; i < enrolledCourseCount; i++) {
            if (enrolledCourses[i].getCourseId() == courseId) {
                return true;
            }
        }
        return false;
    }

    public void addCourse(Course course) {
         if (isEnrolled(course.getCourseId())) {
            System.out.println("Already enrolled in this course.");
            return;
        }
        if (enrolledCourseCount >= enrolledCourses.length) {
            System.out.println("Cannot enroll in more than 10 courses.");
            return;
        }
        enrolledCourses[enrolledCourseCount++] = course;
    }

    void setMarks(int courseId, int value) {
        if (value < 0 || value > 100) {
            return;
        }
        for (int i = 0; i < enrolledCourseCount; i++) {
            if (enrolledCourses[i].getCourseId() == courseId) {
                marks[i] = value;
                return;
            }
        }
    }

    void setGrades(int courseId, String grade) {
        for (int i = 0; i < enrolledCourseCount; i++) {
            if (enrolledCourses[i].getCourseId() == courseId) {
                grades[i] = grade;
                return;
            }
        }
    }

    public String getDetail() {
        
        String s= "ID: " + getUserId() + " Name: " + getName() + " Email: " + getEmail() ;
        return s;
    }

    private int getGradePoints(String grade) {
        switch (grade) {
            case "A":
                return 10;
            case "A-":
                return 9;
            case "B":
                return 8;
            case "B-":
                return 7;
            case "C":
                return 6;
            case "C-":
                return 5;
            case "D":
                return 4;
            case "F":
                return 0;
            default:
                return 0;
        }
    }

    @Override
    public void showMenu() {
        System.out.println("Student Menu:");
        System.out.println("1. View Courses");
        System.out.println("2. Enroll in Course");
        System.out.println("3. View Marks");
        System.out.println("4. View Grades");
        System.out.println("5. View SGPA");
        System.out.println("6. Exit");
    }
}
