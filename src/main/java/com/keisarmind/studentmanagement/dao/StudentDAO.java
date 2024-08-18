package com.keisarmind.studentmanagement.dao;

import com.keisarmind.studentmanagement.model.Student;
import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();

    Student findStudentById(int id);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int id);
}
