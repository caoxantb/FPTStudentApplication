package fpt.studentsapp.service;

import fpt.studentsapp.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourses();
    void saveCourse(Course course);
    Course findCourseById(String id);
    void deleteCourseById(String id);
}
