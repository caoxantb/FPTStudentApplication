package fpt.studentsapp.controller;

import fpt.studentsapp.model.Course;
import fpt.studentsapp.model.Student;
import fpt.studentsapp.service.CourseService;
import fpt.studentsapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public String findAll(Model model) {
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "list_students";
    }

    @GetMapping("/add")
    public String addNewStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add_student";
    }

    @PostMapping("/saveAdd")
    public String saveNewStudent(@ModelAttribute("student") Student student) {
        this.studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String viewStudent(@PathVariable("id") UUID id, Model model) {
        Student student = studentService.findStudentById(id);
        Set<Course> coursesAttended = student.getCourses();
        List<Course> courses = courseService.findAllCourses();
        List<Course> coursesNotAttended = new ArrayList<>();
        for (Course c: courses)
        if (!coursesAttended.contains(c)) {
            coursesNotAttended.add(c);
        }
        model.addAttribute("student", student);
        model.addAttribute("coursesAttended", coursesAttended);
        model.addAttribute("coursesNotAttended", coursesNotAttended);
        return "view_student";
    }

    @GetMapping("/{id}/update")
    public String updateStudent(@PathVariable("id") UUID id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        return "update_student";
    }

    @PostMapping("/{id}/saveUpdate")
    public String saveUpdatedStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students/" + student.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/{sid}/addCourse")
    public String addCourse(@PathVariable("sid") UUID sid, @RequestParam("cid") String cid) {
        Student student = studentService.findStudentById(sid);
        Course course = courseService.findCourseById(cid);
        student.addCourse(course);
        studentService.saveStudent(student);
        course.addStudent(student);
        courseService.saveCourse(course);
        return "redirect:/students/" + student.getId();
    }

    @GetMapping("/{sid}/removeCourse")
    public String removeCourse(@PathVariable("sid") UUID sid, @RequestParam("cid") String cid) {
        Student student = studentService.findStudentById(sid);
        Course course = courseService.findCourseById(cid);
        student.removeCourse(course);
        studentService.saveStudent(student);
        course.removeStudent(student);
        courseService.saveCourse(course);
        return "redirect:/students/" + student.getId();
    }
}
