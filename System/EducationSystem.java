package System;

import Users.*;

public class EducationSystem {
    private static user users[] = new user[500];
    public static Course courses[] = new Course[100];

    private static int userCount = 0 ;
    private static int courseCount = 0 ;
    private static int teachercount = 0 ;
    private static int studentcount = 0 ;

    public static user login(String email, String password) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        return null;
    }
    public static boolean isExistingEmail(String email) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public static user findStudentById(int id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getRole().equals("Student") && users[i].getUserId() == id) {
                return users[i];
            }
        }
        return null;
    }

    public static user findTeacherById(int id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getRole().equals("Teacher") && users[i].getUserId() == id) {
                return users[i];
            }
        }
        return null;
    }

    public static Course findCourseById(int id) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseId() == id) {
                return courses[i];
            }
        }
        return null;
    }

    public static void addUser(user a) {
        if (userCount < users.length) {
            users[userCount] = a;
            userCount++;
            if (a.getRole().equals("Teacher")) {
                teachercount++;
            } else if (a.getRole().equals("Student")) {
                studentcount++;
            }
        } else {
            System.out.println("There are more than 500 users.");
        }
    }

    public static void addCourse(Course c) {
        if (courseCount < courses.length) {
            courses[courseCount] = c;
            courseCount++;
            System.out.println("Course created successfully!");
        } else {
            System.out.println("There are more than 100 courses.");
        }
    }

    public static void displayAllCourses() {
        if (courseCount == 0) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("All courses:");
        for (int i = 0; i < courseCount; i++) {
            System.out.println((i + 1) + ". " + courses[i].getCourseName() + " (ID: " + courses[i].getCourseId() + ")");
        }
    }

    public static void displayAllTeachers() {
        if (teachercount == 0) {
            System.out.println("No teachers available.");
            return;
        }
        System.out.println("All teachers:");
        for (int i = 0; i < userCount; i++) {
            if (users[i].getRole().equals("Teacher")) {
                System.out.println(users[i].getName() + " (ID " + users[i].getUserId() + ")");
            }
        }
    }

    public static void displayAllStudents() {
        if (studentcount == 0) {
            System.out.println("No students available.");
            return;
        }
        System.out.println("All students:");
        for (int i = 0; i < userCount; i++) {
            if (users[i].getRole().equals("Student")) {
                System.out.println(users[i].getName() + " (ID " + users[i].getUserId() + ")");
            }
        }
    }

    public static int getNextUserId() {
        return userCount + 1;
    }
    public static int getcourseCount() {
        return courseCount;
    }
    public static int getTeacherCount(){
        return teachercount;
    }
}
