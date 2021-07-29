package fpt.studentsapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import fpt.studentsapp.model.Student;
import fpt.studentsapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student) {
        this.studentRepository.save(student);
    }

    public Student findStudentById(UUID id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudentById(UUID id) {
        this.studentRepository.deleteById(id);
    }

}
