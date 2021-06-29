package uz.ecopay.create_table.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.ecopay.create_table.entity.Student;
import uz.ecopay.create_table.repository.StudentRepository;

import java.util.Optional;

@Controller
public class SimpleController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    public String goHome() {
        return "index";
    }

    @GetMapping("/addOrEdit")
    public String addOrEdit(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            return "Page2";
        }
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            model.addAttribute("id",student.getId());
            model.addAttribute("firstname",student.getFirstname());
            model.addAttribute("lastname",student.getLastname());
            model.addAttribute("age",student.getAge());
            model.addAttribute("phone",student.getPhoneNumber());
            return "Page2";
        } else return "error";
    }
}
