package com.achini.dataaccess.util;

import com.achini.models.*;
import com.achini.models.types.ClassType;
import com.achini.models.types.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chanaka Rathnayaka
 */
public class DBUtils {

    public static Tutor getOnlyTutor(ResultSet resultSet) throws SQLException {
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

    public static Tutor getTutor(ResultSet resultSet) throws SQLException {
        Tutor tutor = new Tutor();
        tutor.setName(resultSet.getString("name"));
        tutor.setEmail(resultSet.getString("email"));
        tutor.setTutorId(resultSet.getInt("tutorId"));
        tutor.setHighestQualification(resultSet.getString("qualification"));
        Fee fee = new Fee(resultSet.getDouble("amount"), ClassType.valueOf(resultSet.getString("classType")));
        fee.setFeeId(resultSet.getInt("feeId"));
        Subject subject = new Subject();
        subject.setSubjectId(resultSet.getInt("subjectId"));
        subject.setGrade(resultSet.getInt("grade"));
        subject.setName(resultSet.getString("subjectName"));
        subject.setFee(fee);
        Set<Subject> subjects = new HashSet<>(1);
        subjects.add(subject);
        tutor.setSubjects(subjects);
        return tutor;
    }
}
