package com.achini.dataaccess;

import com.achini.dataaccess.util.DBConnectionManager;
import com.achini.dataaccess.util.DBUtils;
import com.achini.models.Tutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */
public class TutorDataAccess {
    private static final String TUTOR_INSERT_QUERY = "INSERT INTO " +
            "Tutors(userId,qualification) VALUES (?,?)";

    private static final String TUTOR_UPDATE_QUERY = "UPDATE " +
            "Tutors set qualification=? WHERE tutorId=?";

    private static final String TUTOR_FOR_GRADE_SELECT_QUERY = "SELECT * FROM Tutors T " +
            "JOIN Fees F on T.tutorId = F.tutorId " +
            "left join Subjects S on F.subjectId = S.subjectId " +
            "left join Users U on T.userId = U.userId WHERE F.grade=?;";

    public int insertTutor(Tutor tutor) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        int tutorId = -1;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(TUTOR_INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, tutor.getUserId());
            statement.setString(2, tutor.getHighestQualification());

            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    tutorId = resultSet.getInt(1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(null, statement, connection);
        }
        return tutorId;

    }

    public Tutor getTutorForUser(int userId) {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Tutor tutor = null;

        try {
            connection = connectionManager.getConnection();
            statement = connection
                    .prepareStatement("select * from Tutors where userId=?");
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tutor = DBUtils.getOnlyTutor(resultSet);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(resultSet, statement, connection);
        }

        return tutor;
    }

    public boolean updateTutor(Tutor student) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(TUTOR_UPDATE_QUERY);

            statement.setString(1, student.getHighestQualification());
            statement.setInt(2, student.getTutorId());

            result = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(null, statement, connection);
        }
        return result == 1;

    }

    public List<Tutor> getTutorsForGrade(int grade) {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Tutor> tutors = new ArrayList<>();

        try {
            connection = connectionManager.getConnection();
            statement = connection
                    .prepareStatement(TUTOR_FOR_GRADE_SELECT_QUERY);
            statement.setInt(1, grade);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tutors.add(DBUtils.getTutor(resultSet));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(resultSet, statement, connection);
        }

        return tutors;
    }
}
