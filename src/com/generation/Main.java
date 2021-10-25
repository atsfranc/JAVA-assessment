package com.generation;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.util.*;
import java.text.ParseException;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, courseService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
            }
        }
        while ( option != 7 );
    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        student.registerApprovedCourse(course);
        student.isCourseApproved(courseId);
        System.out.println("Approved " + course + ", Approved course ID: " + courseId);

        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

        student.isAttendingCourse(courseId);
        System.out.println(student + " is attending " + courseId);

        studentService.isSubscribed(studentId);
        System.out.println(studentId + " is subscribed to " + courseId);
    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent( StudentService studentService, CourseService courseService, Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println(student.getApprovedCourses());
        System.out.println("Insert course ID");
        String courseId = scanner.next();
//        Course course = student.getApprovedCourses().get();
//        if (course == null){
//            System.out.println("Invalid Course ID");
//            return;
//        }
        System.out.println("Enter grade=");
        int grade = scanner.nextInt();

        System.out.println("For course: " + courseId + ", grade is: " + grade);
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}
