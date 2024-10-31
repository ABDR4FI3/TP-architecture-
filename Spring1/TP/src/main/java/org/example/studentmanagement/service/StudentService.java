package org.example.studentmanagement.service;

import org.example.studentmanagement.entities.Student;
import org.example.studentmanagement.repositories.StudentRepository;
import org.springframework.beans. factory. annotation. Autowired;
import org.springframework.stereotype.Service;

import java.util. Collection;
import java. util.List;
import java.util. Optional;
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public boolean delete(Long id) {
        Optional<Student> studentOptional =
                Optional.ofNullable(studentRepository.findById(id)).get();
        if (studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return true;
        } else {
            return false;
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public Collection<?> findNbrStudentByYear() {
        return studentRepository.findNbrStudentByYear();
    }
}
