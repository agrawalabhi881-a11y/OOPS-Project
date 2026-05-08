
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean exit = true;
        Scanner scanner = new Scanner(System.in);
        while (exit){
        
        System.out.println("        Welcome to the SMART EDUCATION SYSTEM Of IIITR ");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Please select mode of Intraction:");
        System.out.println("1. Sign In");
        System.out.println("2. Teacher Sign up");
        System.out.println("3. Student Sign up");
        System.out.println("4. Exit");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();
        switch (roleChoice) {
            case 1:
                Signin(scanner);
                break;
            case 2:
               // TeacherSignup(scanner);
                break;
            case 3:
               // StudentSignup(scanner);
                break;
            case 4:
                exit = false;
                break;
            default:
                System.out.println("Invalid choice. Please restart the application.");
        }
    }
        scanner.close();
        System.out.println("Thank you for using the SMART EDUCATION SYSTEM.");
    }

   static void Signin(Scanner sc) {
        System.out.println("Please Select Your role: ");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Admin");
        int Choice = sc.nextInt();
        switch (Choice) {
            case 1:
                 StudentSignin(sc);
                break;
            case 2:
                TeacherSignin(sc);
                break;
            case 3:
                 AdminSignin(sc);
                break;
             default :
                 System.out.println("Invalid choice. Please restart the application.");  
        }
    }
    static void TeacherSignin(Scanner sc){
        sc.nextLine(); // Consume the newline
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        int a = checkTeacher(email, password);
        if(a == -1){
            System.out.println("Invalid email or password. Please try again.");
            return;
        }
        Teacher teacher = Teacher.getTeacher(a);
        // Here you would typically save the teacher's information to a database
        TeacherDeskboard(teacher);
       
    }
    static void StudentSignin(Scanner sc){
        sc.nextLine(); // Consume the newline
        String name = sc.nextLine();
        System.out.println("Enter your id: ");
        String id = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        int a = checkStudent(id, password);
        if(a == -1){
            System.out.println("Invalid id or password. Please try again.");
            return;
        }
        Student student = Student.getStudent(a);
        // Here you would typically save the student's information to a database
        StudentDeskboard(student);
       
    }
    static void AdminSignin(Scanner sc){
        sc.nextLine(); // Consume the newline left by nextInt()
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        int a = checkAdmin(email, password);
        if(a == -1){
            System.out.println("Invalid email or password. Please try again.");
            return;
        }
        Admin admin = Admin.getAdmin(a);
        // Here you would typically save the admin's information to a database
        AdminDeskboard(admin);
    }
}