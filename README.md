# Smart Education Management System

A Java-based console application designed to streamline academic management by providing role-based access for Administrators, Teachers, and Students. The system enables course management, enrollment approvals, marks management, grade calculation, and SGPA computation.

## Features

### Admin Module

* Create and manage courses
* Modify course credits
* View all courses, teachers, and students
* Assign courses to teachers

### Teacher Module

* View assigned courses
* View enrolled students
* Approve or reject enrollment requests
* Assign marks to students
* Automatic grade generation based on marks

### Student Module

* Student registration and login
* Course enrollment requests
* View enrolled courses
* View marks and grades
* Calculate and view SGPA

### System Features

* Secure role-based authentication
* Course assignment management
* Enrollment approval workflow
* Automatic grade calculation
* SGPA calculation using course credits
* Object-Oriented Design

## Technologies Used

* Java
* Object-Oriented Programming (OOP)
* Arrays and Collections Concepts
* Console-Based User Interface

## Project Structure

```text
Smart-Education-Management-System/
│
├── Main.java
│
├── System/
│   ├── Course.java
│   └── EducationSystem.java
│
├── Users/
│   ├── user.java
│   ├── Admin.java
│   ├── Teacher.java
│   └── Student.java
│
└── README.md
```

## Workflow

1. Users register as Student or Teacher.
2. Admin creates courses and assigns teachers.
3. Students request enrollment in courses.
4. Teachers approve or reject requests.
5. Teachers assign marks to enrolled students.
6. Grades are generated automatically.
7. Students can view grades, marks, and SGPA.

## Grade Mapping

| Marks    | Grade |
| -------- | ----- |
| 95-100   | A     |
| 85-94    | A-    |
| 75-84    | B     |
| 65-74    | B-    |
| 55-64    | C     |
| 45-54    | C-    |
| 35-44    | D     |
| Below 35 | F     |

## Installation & Execution

Clone the repository:

```bash
git clone https://github.com/your-username/OOPS-Project.git
```

Compile:

```bash
javac Main.java
```

Run:

```bash
java Main
```

## Learning Outcomes

* Object-Oriented Programming
* Inheritance and Polymorphism
* Abstraction and Encapsulation
* Role-Based System Design
* Academic Management Workflows
* Java Application Development

## Future Enhancements

* Database Integration (MySQL/PostgreSQL)
* GUI using JavaFX or Swing
* Attendance Management
* Assignment Submission Portal
* Email Notifications
* Web-Based Deployment

## Authors

* Abhishek Agrawal
* Harsh Kumar Singh
* Dipendra Vikram Singh
* Tejus Yadav

## License

This project is developed for educational and learning purposes.
