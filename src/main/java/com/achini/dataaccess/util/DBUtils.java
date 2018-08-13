package com.achini.dataaccess.util;

import com.achini.models.*;
import com.achini.models.types.ClassType;
import com.achini.models.types.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Chanaka Rathnayaka
 */
public class DBUtils {

    public static Tutor getTutor(ResultSet resultSet) throws SQLException {
        Tutor tutor = new Tutor();
        tutor.setTutorId(resultSet.getInt("tutorId"));
        tutor.setHighestQualification(resultSet.getString("qualification"));
        return tutor;
    }

    public static Student getStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getInt("studentId"));
        student.setGrade(resultSet.getInt("grade"));
        student.setUserId(resultSet.getInt("userId"));
        student.setSchool(resultSet.getString("school"));
        return student;
    }

    public static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("userId"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password").toCharArray());
        user.setUserType(UserType.valueOf(resultSet.getString("userType")));
        user.setEnrolledDate(resultSet.getDate("enrolledDate"));
        user.setBirthDate(resultSet.getDate("birthDate"));
        return user;
    }

    public static Subject getSubject(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        Fee fee = new Fee(resultSet.getInt("amount"), ClassType.valueOf(resultSet.getString("classType")));
        subject.setSubjectId(resultSet.getInt("subjectId"));
        subject.setGrade(resultSet.getInt("grade"));
        subject.setFee(fee);

        return subject;
    }
}
