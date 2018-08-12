package com.achini.dataaccess.util;

import com.achini.models.Student;
import com.achini.models.User;
import com.achini.models.types.UserType;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Chanaka Rathnayaka
 */
public class DBUtils {

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
        user.setUserType(UserType.valueOf(resultSet.getString("userType")));
        user.setEnrolledDate(resultSet.getDate("enrolledDate"));
        user.setBirthDate(resultSet.getDate("birthDate"));
        return user;
    }

}
