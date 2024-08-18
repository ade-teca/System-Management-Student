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
            System.out.println("\nEscolha uma operação:");
            System.out.println("1 - Carregar todos estudantes");
            System.out.println("2 - Encontrar o estudando através do ID");
            System.out.println("3 - Adicionar um novo estudante");
            System.out.println("4 - atualizar dados do estudante");
            System.out.println("5 - Eliminar o estudante através do ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma das opções acima: ");
            choose = scanner.nextInt();

            switch (choose) {
                case 1:
                    List<Student> students = studentService.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("Estudante não encontrado.");
                    } else {
                        students.forEach(student ->
                                System.out.println(student.getId() + ": " + student.getName()));
                    }
                    break;
                case 2:
                    System.out.print("Insira o ID: ");
                    int id = scanner.nextInt();
                    Student student = studentService.findStudentById(id);
                    if (student != null) {
                        System.out.println("Estudante encontrado: " + student.getName());
                    } else {
                        System.out.println("Estudante não esncontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Insira o nome do estudante: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();
                    Student newStudent = new Student();
                    newStudent.setName(name);
                    System.out.print("Insira o e-mail do estudante: ");
                    scanner.nextLine(); // Consume newline
                    String email = scanner.nextLine();
                    newStudent.setEmail(email);
                    try {
                        studentService.saveStudent(newStudent);
                        System.out.println("Estudante adicionado com sucesso.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Insira o ID para atualizar os dados: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Student updateStudent = studentService.findStudentById(updateId);
                    if (updateStudent != null) {
                        System.out.print("Insira o novo nome: ");
                        String newName = scanner.nextLine();
                        updateStudent.setName(newName);
                        studentService.updateStudent(updateStudent);
                        System.out.println("Dados atualizado com sucesso.");
                    } else {
                        System.out.println("Estudante não encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Insira o ID do estudante: ");
                    int deleteId = scanner.nextInt();
                    studentService.deleteStudentById(deleteId);
                    System.out.println("Estudante eliminado com sucesso.");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tenta novamente");
            }
        } while (choose != 0);

        scanner.close();
    }
}
