
package Users;

import System.Course;
import java.util.Scanner;

public class Teacher extends user {

    static Scanner sc = new Scanner(System.in);
    private Course assignedCourses[] = new Course[5];
    private int assignedCourseCount = 0;

    public Teacher(int userId, String name, String email, String password) {
        super(userId, name, email, password, "Teacher");
    }

    public void viewCourses() {
        if (assignedCourseCount == 0) {
            System.out.println("No courses assigned.");
        } else {
            System.out.println("Assigned Courses:");
            for (int i = 0; i < assignedCourseCount; i++) {
                System.out.println((i + 1) + ". " + assignedCourses[i].getCourseName());
            }
        }
    }

    public Course choicecCourse() {
        if (assignedCourseCount == 0) {
            System.out.println("No courses assigned.");
            return null;
        }
        while (true) {
            System.out.println("Enter your Choice to choose the course: \nEnter");
            
            for (int i = 0; i < assignedCourseCount; i++) {
                System.out.println((i + 1) + " for " + assignedCourses[i].getCourseName());
            }
            try {
                int courseChoice = Integer.parseInt(sc.nextLine()) - 1;
                if (courseChoice < 0 || courseChoice >= assignedCourseCount) {
                    System.out.println("Invalid course choice. enter the specific choice");
                    continue;
                }
                Course c = assignedCourses[courseChoice];
                return c;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void viewStudentsInCourse() {
        Course c = choicecCourse();
        if (c == null) {
            return;
        }
        if (c.getEnrolledStudentCount() == 0) {
            System.out.println("There are no students enrolled in this course.");
            return;
        }
         Student[] enrolled = c.getEnrolledStudents();
        System.out.println("Students enrolled in " + c.getCourseName() + ":");
       
        int count = c.getEnrolledStudentCount();
        for (int i = 0; i < count; i++) {
            Student s = enrolled[i];
            System.out.println((i + 1) + ". " + s.getName() + " (ID: " + s.getUserId() + ")");
        }
    }

    public void approveEnrollment() {
        Course c = choicecCourse();
        if (c == null) {
            return;
        }
        if (c.getStudentApprovalCount() == 0) {
            System.out.println("No pending enrollments for " + c.getCourseName() + ".");
            return;
        }
         Student[] pending = c.getStudentForApprovals();
         int count = c.getStudentApprovalCount();
        for (int i = count- 1; i >= 0; i--) {
            Student s = pending[i];
            if (s == null) {
                continue;
            }
            boolean a = true;
            boolean approved = false;
            while (a) {
                System.out.println("Approve enrollment for " + s.getName() + " (ID: " + s.getUserId() + ")?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = ScanInt("Enter choice: ");
                if (choice == 1) {
                    approved = true;
                    a = false;
                } else if (choice == 2) {
                    approved = false;
                    a = false;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }
            c.processApproval(i, approved);
        }
    }

    public void assignCourse(Course c) {
        if (assignedCourseCount < 5) {
            c.assignTeacher(this);
            if (c.getTeacher() == this) {
                assignedCourses[assignedCourseCount++] = c;
                System.out.println("Course " + c.getCourseName() + " assigned successfully to " + getName() + ".");
            }
        } else {
            System.out.println("Cannot assign more than 5 courses to a teacher.");
        }
    }

    public void giveMarks() {
        Course c = choicecCourse();
        if (c == null) {
            return;
        }
        if (c.getEnrolledStudentCount() == 0) {
            System.out.println("There are no students in this course.");
            return;
        }

        Student[] enrolled = c.getEnrolledStudents();
        int count = c.getEnrolledStudentCount();
        for (int i = 0; i < count; i++) {
            Student s = enrolled[i];
            while (true) {
                System.out.println("Enter marks for " + s.getName() + " (ID: " + s.getUserId() + ")");
                String j = "Enter marks for " + s.getName() + " (ID: " + s.getUserId() + "): ";
                int marks = ScanInt(j);
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                    continue;
                }
                s.setMarks(c.getCourseId(), marks);
                String grade = calculateGrade(marks);
                s.setGrades(c.getCourseId(), grade);
                System.out.println("Marks and grade assigned for " + s.getName());
            }
        }
    }

    String calculateGrade(int marks) {
        if (marks >= 95) {
            return "A";
        } else if (marks >= 85) {
            return "A-";
        } else if (marks >= 75) {
            return "B";
        } else if (marks >= 65) {
            return "B-";
        } else if (marks >= 55) {
            return "C";
        } else if (marks >= 45) {
            return "C-";
        } else if (marks >= 35) {
            return "D";
        } else {
            return "F";
        }
    }

    public String getDetail() {
        String s = "ID: " + getUserId() + " Name: " + getName() + " Email: " + getEmail();
        return s;
    }

    @Override
    public void showMenu() {
        System.out.println("Teacher Menu:");
        System.out.println("1. View Courses");
        System.out.println("2. View Students in Course");
        System.out.println("3. Approve Enrollment");
        System.out.println("4. Give Marks");
        System.out.println("5. Logout");
    }
    static int ScanInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                int a= sc.nextInt();
                sc.nextLine();
                return a;
            } catch (Exception e) {
                System.out.println("Invalid number. Please enter a valid integer.");
                sc.nextLine();
            }
        }
    }
}