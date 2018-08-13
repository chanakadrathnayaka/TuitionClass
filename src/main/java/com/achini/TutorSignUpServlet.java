package com.achini;

import com.achini.models.Fee;
import com.achini.models.Subject;
import com.achini.models.Tutor;
import com.achini.models.types.ClassType;
import com.achini.service.TutorManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Chanaka Rathnayaka
 */
@WebServlet(
        name = "TutorSignUpServlet",
        urlPatterns = "/tutor-sign-up"
)
public class TutorSignUpServlet extends HttpServlet {

    private TutorManager tutorManager;

    public TutorSignUpServlet() {
        tutorManager = new TutorManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getParameterMap();
        Tutor tutor = extractTutor(req.getParameterMap());
        this.tutorManager.registerTutor(tutor);
        RequestDispatcher view = req.getRequestDispatcher("tutor-dashboard.jsp");
        view.forward(req, resp);
    }

    private Tutor extractTutor(Map<String, String[]> paramMap) {
        Tutor student = new Tutor();

        student.setUserId(Integer.parseInt(paramMap.get("signUpUserId")[0]));
        student.setHighestQualification(paramMap.get("signUpHighestQualification")[0]);
        student.setSubjects(extractSubject(paramMap));

        return student;
    }

    private List<Subject> extractSubject(Map<String, String[]> paramMap) {

        String[] subjectIds = paramMap.get("signUpSubjects");
        String[] grades = paramMap.get("signUpGrade");
        String[] classTypes = paramMap.get("signUpClassType");
        String[] fees = paramMap.get("signUpFee");

        List<Subject> subjects = new ArrayList<>(subjectIds.length);

        for (int i = 0; i < subjectIds.length; i++) {
            Subject subject = new Subject();
            subject.setSubjectId(Integer.parseInt(subjectIds[i]));
            subject.setGrade(Integer.parseInt(grades[i]));
            Fee fee = new Fee(Double.parseDouble(fees[i]), ClassType.valueOf(classTypes[i]));
            subject.setFee(fee);

            subjects.add(subject);
        }
        return subjects;
    }
}
