package fpt.studentsapp.controller;

import fpt.studentsapp.model.Course;
import fpt.studentsapp.model.Student;
import fpt.studentsapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public String findAll(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "list_courses";
    }

    @GetMapping("/add")
    public String addNewCourse(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "add_course";
    }

    @PostMapping("/saveAdd")
    public String saveNewCourse(@ModelAttribute("course") Course course) {
        this.courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/{id}")
    public String viewCourse(@PathVariable("id") String id, Model model) {
        Course course = courseService.findCourseById(id);
        Set<Student> students = course.getStudents();
        model.addAttribute("students", students);
        model.addAttribute("course", course);
        return "view_course";
    }

    @GetMapping("/{id}/update")
    public String updateCourse(@PathVariable("id") String id, Model model) {
        Course course = courseService.findCourseById(id);
        model.addAttribute("course", course);
        return "update_course";
    }

    @PostMapping("/{id}/saveUpdate")
    public String saveUpdatedCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses/" + course.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteCourseById(@PathVariable("id") String id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

}
