package Users;

import System.Course;
import System.EducationSystem;
import java.util.Scanner;

public class Admin extends user {

     static Scanner sc = new Scanner(System.in);

    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password, "Admin");
    }
    public static void modifyCourse(){
       if (EducationSystem.getcourseCount() == 0 ) {
            System.out.println("No course available first create a course");
            return;
        }
        EducationSystem.displayAllCourses();
        int courseId = ScanInt("Enter Course ID to assign: ");
        Course course = EducationSystem.findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        while(true){
        int newCredits = ScanInt("Enter new course credit");
        if (newCredits<=0){
            System.out.println("Enter appropriate Credits");
            continue;
        }
        course.setCredits(newCredits);
        return;
    }
    }
    public static void createCourse() {

        int courseId = ScanInt("Enter Course ID: ");
        System.out.println("Enter Course Name: ");
        String courseName = sc.nextLine();
        int credits = ScanInt("Enter Course Credits: ");
        Course c = EducationSystem.findCourseById(courseId);
        if (c != null) {
            System.out.println("Course with ID " + courseId + " already exists.");
            return;
        }
        Course newCourse = new Course(courseId, courseName, credits);
        EducationSystem.addCourse(newCourse);
        

    }
    public static void assignCourseToTeacher() {
        if (EducationSystem.getcourseCount() == 0 ) {
            System.out.println("No course available first create a course");
            return;
        }
        EducationSystem.displayAllCourses();
        int courseId = ScanInt("Enter Course ID to assign: ");
        Course course = EducationSystem.findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        if (course.getTeacher() != null) {
            System.out.println("Course is already assigned to a teacher.");
            return;
        }
        if (EducationSystem.getTeacherCount() == 0 ) {
            System.out.println("No Teacher signup till now");
            return;
        }
        EducationSystem.displayAllTeachers();
        int teacherId = ScanInt("Enter Teacher ID to assign: ");
        user found = EducationSystem.findTeacherById(teacherId);
        if (found == null) {
            System.out.println("Teacher not found.");
            return;
        }
      ((Teacher) found).assignCourse(course);
    }

    public static void viewAllCourses() {
        EducationSystem.displayAllCourses();
    }

    public static void viewAllTeachers() {
        EducationSystem.displayAllTeachers();
    }

    public static void viewAllStudents() {
        EducationSystem.displayAllStudents();
    }
      public  String getDetail() {
        return "ID: " + getUserId() + " Name: " + getName() + " Email: " + getEmail() + " Role: " + getRole();
    }
    @Override
    public void showMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Create Course");
        System.out.println("2. View All Courses");
        System.out.println("3. View all Teachers");
        System.out.println("4. View all Students");
        System.out.println("5. Assign Course to Teacher");
        System.out.println("6. Logout");
        System.out.println("7.Change credits for a course");
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
