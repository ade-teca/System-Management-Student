package com.keisarmind.studentmanagement;

import com.keisarmind.studentmanagement.dao.StudentDAOImpl;
import com.keisarmind.studentmanagement.model.Student;
import com.keisarmind.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService(new StudentDAOImpl());
        Scanner scanner = new Scanner(System.in);
        int choose;

        do {
            System.out.println("\nChoose an operation:");
            System.out.println("1 - Get all the students");
            System.out.println("2 - Find student based on ID");
            System.out.println("3 - Save new student");
            System.out.println("4 - Update student data");
            System.out.println("5 - Delete a student based on ID");
            System.out.println("0 - Exit");
            System.out.print("Your choice: ");
            choose = scanner.nextInt();

            switch (choose) {
                case 1:
                    List<Student> students = studentService.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        students.forEach(student ->
                                System.out.println(student.getId() + ": " + student.getName()));
                    }
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    Student student = studentService.findStudentById(id);
                    if (student != null) {
                        System.out.println("Student found: " + student.getName());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student name: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();
                    Student newStudent = new Student();
                    newStudent.setName(name);
                    System.out.print("Enter student email: ");
                    scanner.nextLine(); // Consume newline
                    String email = scanner.nextLine();
                    newStudent.setEmail(email);
                    try {
                        studentService.saveStudent(newStudent);
                        System.out.println("Student saved successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Student updateStudent = studentService.findStudentById(updateId);
                    if (updateStudent != null) {
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        updateStudent.setName(newName);
                        studentService.updateStudent(updateStudent);
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    studentService.deleteStudentById(deleteId);
                    System.out.println("Student deleted successfully.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choose != 0);

        scanner.close();
    }
}
