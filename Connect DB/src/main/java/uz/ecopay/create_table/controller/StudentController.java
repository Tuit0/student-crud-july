package uz.ecopay.create_table.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.ecopay.create_table.payload.ApiResponse;
import uz.ecopay.create_table.payload.StudentDto;
import uz.ecopay.create_table.service.StudentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
//  Add student             ////
//  Edit student            ////
//  Delete student          ////
//  Get student             ////
//  Get all students        ////
    private final StudentService service;

    @GetMapping("/getStudent")
    @ResponseBody
    public HttpEntity<?> getStudent(@RequestParam Long id) {
        ApiResponse apiResponse = service.getStudent(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @GetMapping("/getAllStudents")
    @ResponseBody
    public HttpEntity<?> getAllStudents() {
        ApiResponse apiResponse = service.getAllStudents();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/addOrEditStudent")
    public String addOrEditStudent(       @RequestParam(required = false) Long id,
                                          @RequestParam String firstname,
                                          @RequestParam String lastname,
                                          @RequestParam int age,
                                          @RequestParam String phone) {
        StudentDto studentDto = new StudentDto(id,firstname,lastname,age,phone);
        service.addOrEditStudent(studentDto);
        return "index";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "index";
    }
}
