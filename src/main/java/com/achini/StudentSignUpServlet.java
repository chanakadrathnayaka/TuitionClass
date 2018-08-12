package com.achini;

import com.achini.dataaccess.StudentDataAccess;
import com.achini.models.Student;
import com.achini.service.StudentManager;
import com.achini.service.SubjectManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isSuccess = studentManager.registerStudent(extractStudent(req.getParameterMap()));
//        todo set user attrib
        RequestDispatcher view = req.getRequestDispatcher("student-dashboard");
        view.forward(req, resp);
    }

    private Student extractStudent(Map<String, String[]> paramMap) {
        Student student = new Student();

        student.setUserId(Integer.parseInt(paramMap.get("signUpUserId")[0]));
        student.setGrade(Integer.parseInt(paramMap.get("signUpGrade")[0]));
        student.setSchool(paramMap.get("signUpSchool")[0]);

        return student;
    }
}
