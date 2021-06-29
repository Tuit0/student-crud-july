package uz.ecopay.create_table.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.ecopay.create_table.entity.Student;
import uz.ecopay.create_table.payload.ApiResponse;
import uz.ecopay.create_table.payload.StudentDto;
import uz.ecopay.create_table.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public ApiResponse getStudent(Long id) {
        try {
            Optional<Student> optional = studentRepository.findById(id);
            return optional.map(student -> new ApiResponse("Success", true, student))
                    .orElseGet(() -> new ApiResponse("Student not found with this id", false));
        } catch (Exception e) {
            return new ApiResponse("Something went wrong!",false);
        }
    }

    public ApiResponse getAllStudents() {
        try {
            List<Student> allStudents = studentRepository.findAll();
            return new ApiResponse("Success",true,allStudents);
        } catch (Exception e) {
            return new ApiResponse("Something went wrong!",false);
        }
    }

    public ApiResponse addOrEditStudent(StudentDto studentDto) {
        try {
            Student student = new Student();
            if (studentDto.getId() != null) {
                student = studentRepository.findById(studentDto.getId())
                        .orElseThrow(()->new IllegalStateException("Student not found with id"));
            }

            student.setAge(studentDto.getAge());
            student.setFirstname(studentDto.getFirstname());
            student.setLastname(studentDto.getLastname());
            student.setPhoneNumber(studentDto.getPhoneNumber());
            studentRepository.save(student);
            return new ApiResponse(studentDto.getId()!=null?"Edited":"Saved",false);
        } catch (Exception e) {
            return new ApiResponse("Something went wrong!",false);
        }
    }

    public ApiResponse deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        } catch (Exception e) {
            return new ApiResponse("Something went wrong!",false);
        }
    }
}
