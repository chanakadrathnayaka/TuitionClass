package com.achini.dataaccess;

import com.achini.dataaccess.util.DBConnectionManager;
import com.achini.models.Subject;
import com.achini.models.Tutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Chanaka Rathnayaka
 */
public class FeeDataAccess {
    private static final String FEE_INSERT_QUERY = "INSERT INTO " +
            "Fees(subjectId,grade,tutorId,amount,classType) VALUES (?,?,?,?,?)";

    public void insertFee(Tutor tutor) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(FEE_INSERT_QUERY);

            for (Subject s:tutor.getSubjects()) {
                statement.setInt(1, s.getSubjectId());
                statement.setInt(2, s.getGrade());
                statement.setInt(3, tutor.getTutorId());
                statement.setDouble(4, s.getFee().getAmount());
                statement.setString(5, s.getFee().getClassType().name());
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(null, statement, connection);
        }
    }
}
