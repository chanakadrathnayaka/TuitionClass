package com.achini.service;

import com.achini.dataaccess.StudentDataAccess;
import com.achini.dataaccess.StudentEnrollDataAccess;
import com.achini.dataaccess.UserDataAccess;
import com.achini.models.Student;
import com.achini.models.User;

/**
 * @author Chanaka Rathnayaka
 */
public class StudentManager {

    private StudentDataAccess studentDataAccess;
    private UserDataAccess userDataAccess;
    private StudentEnrollDataAccess studentEnrollDataAccess;

    public StudentManager() {
        studentDataAccess = new StudentDataAccess();
        userDataAccess = new UserDataAccess();
        studentEnrollDataAccess = new StudentEnrollDataAccess();
    }

    public void registerStudent(Student student) {
        int studentId = studentDataAccess.insertStudent(student);
        student.setStudentId(studentId);
        studentEnrollDataAccess.insertStudentEnroll(student);
    }

    public Student getStudent(User user) {
        Student student = studentDataAccess.getStudent(user.getUserId());
        if (student == null) {
            student = new Student();
        }
        student.setUsername(user.getUsername());
        student.setUserId(user.getUserId());
        student.setBirthDate(user.getBirthDate());
        student.setEmail(user.getEmail());
        student.setEnrolledDate(user.getEnrolledDate());
        student.setName(user.getName());
        return student;
    }

    public User saveStudent(Student student) {
        studentDataAccess.updateStudent(student);
        studentEnrollDataAccess.insertStudentEnroll(student);
        return userDataAccess.updateUser(student);
    }
}