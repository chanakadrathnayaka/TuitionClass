package com.achini.dataaccess;

import com.achini.dataaccess.util.DBConnectionManager;
import com.achini.models.Student;
import com.achini.models.Subject;
import com.achini.models.Tutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Chanaka Rathnayaka
 */
public class StudentEnrollDataAccess {
    private static final String STUDENT_ENROLL_INSERT_QUERY = "INSERT INTO " +
            "StudentEnroll(studentId,tutorId,subjectId,feeId) VALUES (?,?,?,?)";

    private static final String STUDENT_ENROLL_SELECT_QUERY = "select * from StudentEnroll as SE join Fees F on SE.feeId = F.feeId join Subjects S on SE.subjectId = S.subjectId join Tutors T on SE.tutorId = T.tutorId join Users U on T.userId = U.userId where SE.studentId = ?";

    public void insertStudentEnroll(Student student) {

        DBConnectionManager connectionManager = new DBConnectionManager();
        Connection connection = null;
        PreparedStatement statement = null;
        int studentId = -1;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(STUDENT_ENROLL_INSERT_QUERY);
            for (Tutor tutor : student.getTutors()) {
                for (Subject subject : tutor.getSubjects()) {
                    statement.setInt(1, student.getStudentId());
                    statement.setInt(2, tutor.getTutorId());
                    statement.setInt(3, subject.getSubjectId());
                    statement.setInt(4, subject.getFee().getFeeId());
                    statement.addBatch();
                }
            }
            statement.executeBatch();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnectionManager.closeResources(null, statement, connection);
        }
    }
}
