package com.achini.dataaccess;

import com.achini.dataaccess.util.DBConnectionManager;
import com.achini.dataaccess.util.DBUtils;
import com.achini.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */
public class StudentDataAccess {

    private static final String STUDENT_INSERT_QUERY = "INSERT INTO " +
            "Students(grade,userId,school) VALUES (?,?,?)";

    private static final String STUDENT_UPDATE_QUERY = "UPDATE " +
            "Students set grade=?,school=? WHERE studentId=?";

    public List<Student> getAllStudent() {

        DBConnectionManager connectionManager = new DBConnectionManager();
        List<Student> students = null;
        try (
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from Students");) {

            students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = DBUtils.getStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return students;
    }

    public boolean insertStudent(Student student) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(STUDENT_INSERT_QUERY);

            statement.setInt(1, student.getGrade());
            statement.setInt(2, student.getUserId());
            statement.setString(3, student.getSchool());

            result = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(null, statement, connection);
        }
        return result == 1;

    }

    public boolean updateStudent(Student student) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(STUDENT_UPDATE_QUERY);

            statement.setInt(1, student.getGrade());
            statement.setString(2, student.getSchool());
            statement.setInt(3, student.getStudentId());

            result = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(null, statement, connection);
        }
        return result == 1;

    }

    public Student getStudent(int userId) {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;

        try {
            connection = connectionManager.getConnection();
            statement = connection
                    .prepareStatement("select * from Students where userId=?");
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = DBUtils.getStudent(resultSet);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(resultSet, statement, connection);
        }

        return student;
    }
}