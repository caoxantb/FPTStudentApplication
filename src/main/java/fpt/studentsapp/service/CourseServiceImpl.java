package fpt.studentsapp.service;

import fpt.studentsapp.model.Course;
import fpt.studentsapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public void saveCourse(Course course) {
        this.courseRepository.save(course);
    }

    public Course findCourseById(String id) {
        return courseRepository.findById(id).get();
    }

    public void deleteCourseById(String id) {
        this.courseRepository.deleteById(id);
    }

}
