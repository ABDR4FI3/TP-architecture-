package org.example.studentmanagement;

import org.example.studentmanagement.controller.StudentController;
import org.example.studentmanagement.entities.Student;
import org.example.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentControllerTest {
    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    @Test
    void testSave() {
        Student student  = Student.builder()
                .id(1L)
                .nom("nom")
                .prenom("prenom")
                .date(new Date())
                .build();

        when(studentService.save(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.save(student);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("nom" , response.getBody().getNom());
    }

    @Test
    void testDeleteStudent () {
        when(studentService.delete(1L)). thenReturn(true);
        ResponseEntity<Void> response = studentController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    @Test
    void testFindAllStudents () {
        Student student1 = new Student();
        Student student2 = new Student();

        when(studentService.findAll()). thenReturn(Arrays.asList(student1, student2));
        ResponseEntity<List<Student>> response = studentController.findAll();

        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    void testCountStudents() {
        when(studentService.countStudents()).thenReturn(10L);

        ResponseEntity<Long> response = studentController.countStudent();
        assertEquals(10L, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
