package fpt.studentsapp.service;

import fpt.studentsapp.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> findAllStudents();
    void saveStudent(Student student);
    Student findStudentById(UUID id);
    void deleteStudentById(UUID id);
}
