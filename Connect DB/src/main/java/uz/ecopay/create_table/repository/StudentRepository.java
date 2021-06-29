package uz.ecopay.create_table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ecopay.create_table.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
