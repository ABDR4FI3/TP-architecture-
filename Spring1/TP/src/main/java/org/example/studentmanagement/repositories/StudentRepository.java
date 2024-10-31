package org.example.studentmanagement.repositories;

import org.example.studentmanagement.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findById(Long id);
    @Query("select YEAR(s.date) ,count(s) from Student s GROUP BY YEAR(s.date)")
    Collection<Objects[]> findNbrStudentByYear();

}
