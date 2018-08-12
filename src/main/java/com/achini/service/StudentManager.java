package com.achini.service;

import com.achini.dataaccess.StudentDataAccess;
import com.achini.models.Student;

import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */
public class StudentManager {

    private StudentDataAccess studentDataAccess;

    public StudentManager() {
        studentDataAccess = new StudentDataAccess();
    }

    public boolean registerStudent(Student student) {
        return studentDataAccess.insertStudent(student);
    }

    public List<Student> getAllStudent() {
        return studentDataAccess.getAllStudent();
    }
}