package com.achini;

import com.achini.models.Fee;
import com.achini.models.Student;
import com.achini.models.Subject;
import com.achini.models.Tutor;
import com.achini.models.types.ClassType;
import com.achini.service.StudentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Chanaka Rathnayaka
 */
@WebServlet(
        name = "StudentSignUpServlet",
        urlPatterns = "/student-sign-up"
)
public class StudentSignUpServlet extends HttpServlet {
    private StudentManager studentManager;

    public StudentSignUpServlet() {
        studentManager = new StudentManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        studentManager.registerStudent(extractStudent(req.getParameterMap()));
        response.sendRedirect("student-dashboard");
    }

    private Student extractStudent(Map<String, String[]> paramMap) {
        Student student = new Student();

        student.setUserId(Integer.parseInt(paramMap.get("signUpUserId")[0]));
        student.setGrade(Integer.parseInt(paramMap.get("signUpGrade")[0]));
        student.setSchool(paramMap.get("signUpSchool")[0]);
        String[] tutorIds = paramMap.get("signUpTutor");
        String[] subjectIds = paramMap.get("signUpSubjects");
        String[] classTypes = paramMap.get("signUpClassType");
        String[] feeIds = paramMap.get("signUpFeeId");
        String[] feeAmounts = paramMap.get("signUpFee");
        Set<Tutor> tutors = new HashSet<>(tutorIds.length);

        for (int i = 0; i < tutorIds.length; i++) {
            Tutor tutor = new Tutor();
            Set<Subject> subjects = new HashSet<>(1);
            Subject subject = new Subject();
            Fee fee = new Fee(Double.parseDouble(feeAmounts[i]), ClassType.valueOf(classTypes[i]));
            fee.setFeeId(Integer.parseInt(feeIds[i]));
            subject.setSubjectId(Integer.parseInt(subjectIds[i]));
            subject.setFee(fee);
            subjects.add(subject);
            tutor.setTutorId(Integer.parseInt(tutorIds[i]));
            tutor.setSubjects(subjects);
            tutors.add(tutor);
        }
        student.setTutors(tutors);
        return student;
    }
}
