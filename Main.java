import System.*;
import Users.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Admin systemAdmin = new Admin(1, "Admin", "admin@iiitr.ac.in", "admin123");

    public static void main(String[] args) {
        EducationSystem.addUser(systemAdmin);
        boolean running = true;

        System.out.println("         Welcome to the SMART EDUCATION SYSTEM");
        System.out.println("---------------------------------------------------------------");

        while (running) {
            System.out.println("\nPlease select mode of Interaction:");
            System.out.println("1. Sign In");
            System.out.println("2. Teacher Sign up");
            System.out.println("3. Student Sign up");
            System.out.println("4. Exit");
            int choice = ScanInt("Enter choice: ");
            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    teacherSignUp();
                    break;
                case 3:
                    studentSignUp();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
        System.out.println("Thank you for using the SMART EDUCATION SYSTEM.");
    }

    static void signIn() {
        while (true) {
            System.out.println("Enter your mail");
            String email = sc.nextLine(); 
            System.out.print("Enter your Password: ");
            String password = sc.nextLine();
            user a = EducationSystem.login(email, password);
            if (a != null) {
                System.out.println("Login successful! Welcome, " + a.getName() + " (" + a.getRole() + ")");
                dashboard(a);
                return;
            }
            System.out.println("Invalid email or password.");
            System.out.println("1. Try Again");
            System.out.println("2. Back to Main Menu");
            while(true) {
                int choice = ScanInt("Enter choice: ");
                if (choice == 1) {
                    break;
                } else if (choice == 2) {
                    return;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }
        }
    }

    static void teacherSignUp() {
        System.out.print("Enter your Name: ");
        String name = sc.nextLine();
        System.out.print("Enter your Email: ");
        String email = sc.nextLine();
        if(EducationSystem.isExistingEmail(email)) {
            System.out.println("Email already exists. Please try signing up with a different email.");
            return;
        }
        System.out.print("Enter your Password: ");
        String password = sc.nextLine();
        Teacher t = new Teacher(EducationSystem.getNextUserId(), name, email, password);
        EducationSystem.addUser(t);
        System.out.println("Teacher signup Successfully. now you can signin");
    }

    static void studentSignUp() {
        System.out.print("Enter your Name: ");
        String name = sc.nextLine();
        System.out.print("Enter your Email: ");
        String email = sc.nextLine();
        if(EducationSystem.isExistingEmail(email)) {
            System.out.println("Email already exists. Please try signing up with a different email.");
            return;
        }
        System.out.print("Enter your Password: ");
        String password = sc.nextLine();
        Student s = new Student(EducationSystem.getNextUserId(), name, email, password);
        EducationSystem.addUser(s);
        System.out.println("Student signup Successfully. now you can signin");
    }

   
    static void dashboard(user a) {
        while (true) {
            a.showMenu();
            int choice = ScanInt("Enter choice: ");
            if (a.getRole().equals("Admin")) { 
                if (Menu((Admin) a, choice)) { 
                    return;
                }
            } else if (a.getRole().equals("Teacher")) {
                if (Menu((Teacher) a, choice)) {
                    return;
                }
            } else if (a.getRole().equals("Student")) {
                if (Menu((Student) a, choice)) {
                    return;
                }
            } else {
                System.out.println("Unknown role. Logging out.");
                return;
            }
        }
    }

    static boolean Menu(Admin admin, int choice) {
        switch (choice) {
            case 1:
                Admin.createCourse();
                break;
            case 2:
                Admin.viewAllCourses();
                break;
            case 3:
                Admin.viewAllTeachers();
                break;
            case 4:
                Admin.viewAllStudents();
                break;
            case 5:
                Admin.assignCourseToTeacher();
                break;
            case 6:
                return true;
            case 7:
                Admin.modifyCourse();
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }

    static boolean Menu(Teacher teacher, int choice) {
        switch (choice) {
            case 1:
                teacher.viewCourses();
                break;
            case 2:
                teacher.viewStudentsInCourse();
                break;
            case 3:
                teacher.approveEnrollment();
                break;
            case 4:
                teacher.giveMarks();
                break;
            case 5:
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }

    static boolean Menu(Student student, int choice) {
        switch (choice) {
            case 1:
                student.viewCourses();
                break;
            case 2:
                requestEnrollment(student);
                break;
            case 3:
                student.viewMarks();
                break;
            case 4:
                student.viewGrades();
                break;
            case 5:
                student.viewSGPA();
                break;
            case 6:
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }

    static void requestEnrollment(Student student) {
        if(EducationSystem.getcourseCount()==0) {
            System.out.println("no course available to enroll right now");
            return;
        }
        EducationSystem.displayAllCourses();
        int courseId = ScanInt("Enter Course ID to request enrollment: ");
        Course course = EducationSystem.findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        if (course.getTeacher() == null) {
            System.out.println("Course is not assigned to any teacher yet. Please try again later.");
            return;
        }
        course.addStudentForApproval(student);
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
