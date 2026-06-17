package System;

import Users.Student;
import Users.Teacher;

public class Course {
    private int courseId;
    private String courseName;
    private int credits;
    private Teacher teacher;
    private Student Students[] = new Student[100];
    private int noOfStudents = 0;
    private Student studentForApprovals[] = new Student[100];
    private int studentApprovalCount = 0;

    public Course(int courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }
    public void setCredits(int credits){
        this.credits=credits;
        System.out.println("New Credits : "+credits);
    }

    public void addStudent(Student student) {
        if (isEnrolled(student.getUserId())) {
            System.out.println("Student is already enrolled in this course.");
            return;
        }
        if (noOfStudents >= Students.length) {
            System.out.println("Cannot add more students to this course.");
            return;
        }
        Students[noOfStudents] = student;
        noOfStudents++;
    }

    public void assignTeacher(Teacher teacher) {
        if (this.teacher != null) {
            System.out.println("Course already has an assigned teacher: " + this.teacher.getName());
            return;
        }
        this.teacher = teacher;
    }

    public void addStudentForApproval(Student student) {
        if (isEnrolled(student.getUserId())) {
            System.out.println("Student is already enrolled in this course.");
            return;
        }
        if (isPendingApproval(student.getUserId())) {
            System.out.println("Student is already waiting for approval.");
            return;
        }
        if (studentApprovalCount >= studentForApprovals.length) {
            System.out.println("Cannot add more approval requests for this course.");
            return;
        }
        studentForApprovals[studentApprovalCount++] = student;
        System.out.println("Enrollment request sent for " + student.getName() + " in " + courseName + ".");
    }

    public boolean isPendingApproval(int studentId) {
        for (int i = 0; i < studentApprovalCount; i++) {
            if (studentForApprovals[i] != null && studentForApprovals[i].getUserId() == studentId) {
                return true;
            }
        }
        return false;
    }

    public void processApproval(int approvalIndex, boolean approved) {
        if (approvalIndex < 0 || approvalIndex >= studentApprovalCount) {
            return;
        }
        Student student = studentForApprovals[approvalIndex];
        if (student == null) {
            return;
        }
        if (approved) {
            addStudent(student);
            student.addCourse(this);
            System.out.println("Approved enrollment for " + student.getName() + " in " + courseName + ".");
        } else {
            System.out.println("Rejected enrollment for " + student.getName() + " in " + courseName + ".");
        }
        for (int i = approvalIndex; i < studentApprovalCount - 1; i++) {
            studentForApprovals[i] = studentForApprovals[i + 1];
        }
        studentForApprovals[--studentApprovalCount] = null;
    }

    public boolean isEnrolled(int studentId) {
        for (int i = 0; i < noOfStudents; i++) {
            if (Students[i].getUserId() == studentId) {
                return true;
            }
        }
        return false;
    }

    public Student[] getEnrolledStudents() {
        return Students;
    }

    public int getEnrolledStudentCount() {
        return noOfStudents;
    }

    public Student[] getStudentForApprovals() {
        return studentForApprovals;
    }

    public int getStudentApprovalCount() {
        return studentApprovalCount;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getCredit() {
        return credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getCourseName() {
        return courseName;
    }
}
