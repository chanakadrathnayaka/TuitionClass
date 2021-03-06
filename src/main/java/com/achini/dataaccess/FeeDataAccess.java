package com.achini.dataaccess;

import com.achini.dataaccess.util.DBConnectionManager;
import com.achini.dataaccess.util.DBUtils;
import com.achini.models.Subject;
import com.achini.models.Tutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chanaka Rathnayaka
 */
public class FeeDataAccess {
    private static final String FEE_INSERT_UPDATE_QUERY = "INSERT INTO Fees (subjectId, grade, tutorId, amount, classType) " +
            "VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE amount = ?";

    public void insertOrUpdateFee(Tutor tutor) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(FEE_INSERT_UPDATE_QUERY);

            for (Subject s : tutor.getSubjects()) {
                statement.setInt(1, s.getSubjectId());
                statement.setInt(2, s.getGrade());
                statement.setInt(3, tutor.getTutorId());
                statement.setDouble(4, s.getFee().getAmount());
                statement.setString(5, s.getFee().getClassType().name());
                statement.setDouble(6, s.getFee().getAmount());
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(null, statement, connection);
        }
    }

    public Set<Subject> getClasses(int tutorId) {
        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Set<Subject> subjects = null;

        try {
            connection = connectionManager.getConnection();
            statement = connection
                    .prepareStatement("select * from Fees where tutorId=?");
            statement.setInt(1, tutorId);
            resultSet = statement.executeQuery();
            subjects = new HashSet<>();
            while (resultSet.next()) {
                subjects.add(DBUtils.getSubject(resultSet));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(resultSet, statement, connection);
        }

        return subjects;
    }
}
