package com.achini.dataaccess;

import com.achini.dataaccess.util.DBConnectionManager;
import com.achini.models.Student;
import com.achini.models.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */
public class SubjectDataAccess {
    public List<Subject> getAllSubjects() {

        DBConnectionManager connectionManager = new DBConnectionManager();
        List<Subject> subjects = null;
        try (
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from Subjects");) {

            subjects = new ArrayList<>();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt("subjectId"));
                subject.setName(resultSet.getString("name"));

                subjects.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public List<Subject> getAllSubjectsWithFees(int grade) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        List<Subject> subjects = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement("select * from Subjects as S join " +
                    "(select * from Fees where grade=?) as F on S.subjectId=F.subjectId");
            statement.setInt(1, grade);
            resultSet = statement.executeQuery();

            subjects = new ArrayList<>();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt("subjectId"));
                subject.setName(resultSet.getString("name"));

                subjects.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(resultSet, statement, connection);
        }

        return subjects;
    }

}
