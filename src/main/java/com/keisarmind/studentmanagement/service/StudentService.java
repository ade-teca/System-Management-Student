package com.keisarmind.studentmanagement.service;

import com.keisarmind.studentmanagement.dao.StudentDAO;
import com.keisarmind.studentmanagement.model.Student;

import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student findStudentById(int id) {
        return studentDAO.findStudentById(id);
    }

    public void saveStudent(Student student) {
        // Exemplo de lógica de negócio:
        // Validar se o nome do estudante não está vazio
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do estudante não deve estar vazio");
        }
        studentDAO.saveStudent(student);
    }

    public void updateStudent(Student student) {
        // Verifica se o estudante existe antes de atualizar
        Student existingStudent = studentDAO.findStudentById(student.getId());
        if (existingStudent == null) {
            throw new IllegalArgumentException("Estudante não encontrado");
        }
        studentDAO.updateStudent(student);
    }

    public void deleteStudentById(int id) {
        // Pode haver regras de negócio antes de deletar
        studentDAO.deleteStudentById(id);
    }
}

