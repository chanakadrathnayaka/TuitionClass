package com.achini.dataaccess;

import com.achini.dataaccess.util.DBConnectionManager;
import com.achini.models.Tutor;

import java.sql.*;

/**
 * @author Chanaka Rathnayaka
 */
public class TutorDataAccess {
    private static final String TUTOR_INSERT_QUERY = "INSERT INTO " +
            "Tutors(userId,qualification) VALUES (?,?)";

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
}
