package com.achini.service;

import com.achini.dataaccess.StudentDataAccess;
import com.achini.dataaccess.UserDataAccess;
import com.achini.models.Student;
import com.achini.models.Tutor;
import com.achini.models.User;

import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */
public class StudentManager {

    private StudentDataAccess studentDataAccess;
    private UserDataAccess userDataAccess;

    public StudentManager() {
        studentDataAccess = new StudentDataAccess();
        userDataAccess = new UserDataAccess();
    }

    public boolean registerStudent(Student student) {
        return studentDataAccess.insertStudent(student);
    }

    public List<Student> getAllStudent() {
        return studentDataAccess.getAllStudent();
    }

    public Student getStudent(User user) {
        Student student = studentDataAccess.getStudent(user.getUserId());
        student.setUsername(user.getUsername());
        student.setUserId(user.getUserId());
        student.setBirthDate(user.getBirthDate());
        student.setEmail(user.getEmail());
        student.setEnrolledDate(user.getEnrolledDate());
        student.setName(user.getName());
        return student;
    }

    public User saveStudent(Student student) {
        studentDataAccess.insertStudent(student);
        return userDataAccess.updateUser(student);
    }
}