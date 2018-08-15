package com.achini;

import com.achini.models.Tutor;
import com.achini.models.User;
import com.achini.service.TutorManager;

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
        name = "TutorDashboardServlet",
        urlPatterns = "/tutor-dashboard"
)
public class TutorDashboardServlet extends HttpServlet {
    private TutorManager tutorManager;

    public TutorDashboardServlet() {
        this.tutorManager = new TutorManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Tutor tutor = this.tutorManager.getTutor(user);
        request.setAttribute("tutor", tutor);

        RequestDispatcher view = request.getRequestDispatcher("tutor-dashboard.jsp");
        view.forward(request, response);
    }
}