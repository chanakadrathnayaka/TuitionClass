package com.achini;

import com.achini.models.Student;
import com.achini.models.User;
import com.achini.service.StudentManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chanaka Rathnayaka
 */
@WebServlet(
        name = "StudentDashboardServlet",
        urlPatterns = "/student-dashboard"
)
public class StudentDashboardServlet extends HttpServlet {
    private StudentManager studentManager;

    public StudentDashboardServlet() {
        this.studentManager = new StudentManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Student student = this.studentManager.getStudent(user);
        request.setAttribute("student", student);
        RequestDispatcher view = request.getRequestDispatcher("student-dashboard.jsp");
        view.forward(request, response);
    }
}
