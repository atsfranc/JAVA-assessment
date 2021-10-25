package com.generation.model;
import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;

import java.util.List;

public interface Evaluation
{
    double getAverage();

    List<Course> getApprovedCourses();
        int grade = 0;


}
