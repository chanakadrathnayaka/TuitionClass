package com.achini;

import com.achini.models.User;
import com.achini.models.types.UserType;
import com.achini.service.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Chanaka Rathnayaka
 */
@WebServlet(
        name = "SignInServlet",
        urlPatterns = "/sign-in"
)
public class SignInServlet extends HttpServlet {

    private UserManager userManager;

    public SignInServlet() {
        this.userManager = new UserManager();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("loginUserName"));
        user.setPassword(request.getParameter("loginPassword").toCharArray());

        User existingUser = this.userManager.getUser(user);
        if (existingUser == null) {
            RequestDispatcher view = request.getRequestDispatcher("service-pages/user-not-found.jsp");
            view.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", existingUser);
            session.setMaxInactiveInterval(30 * 60);
            if (existingUser.getUserType().equals(UserType.STUDENT)) {
                response.sendRedirect("student-dashboard");
            } else {
                response.sendRedirect("tutor-dashboard");
            }
        }
    }
}
