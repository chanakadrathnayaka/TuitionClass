package com.achini;

import com.achini.dataaccess.UserDataAccess;
import com.achini.models.User;
import com.achini.models.types.UserType;
import com.achini.service.SubjectManager;
import com.achini.service.UserManager;
import com.achini.servlets.utils.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * @author Chanaka Rathnayaka
 */

@WebServlet(
        name = "SignUpServlet",
        urlPatterns = "/sign-up"
)
public class SignUpServlet extends HttpServlet {

    private SubjectManager subjectManager;
    private UserManager userManager;

    public SignUpServlet() {
        subjectManager = new SubjectManager();
        userManager = new UserManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = extractUser(req.getParameterMap());
        int userId = userManager.registerUser(user);

        req.setAttribute("signUpUserId", userId);

        String page;
        if (user.getUserType().equals(UserType.STUDENT)) {
            page = "student-sign-up.jsp";
        } else {
            req.setAttribute("subjects", subjectManager.getAllSubject());
            page = "tutor-sign-up.jsp";
        }
        RequestDispatcher view = req.getRequestDispatcher(page);
        view.forward(req, resp);
    }

    private User extractUser(Map<String, String[]> paramMap) {
        User user = new User();

        user.setName(paramMap.get("signUpName")[0]);
        user.setEmail(paramMap.get("signUpEmail")[0]);
        user.setUsername(paramMap.get("signUpUserName")[0]);
        user.setPassword(paramMap.get("signUpPassword")[0].toCharArray());
        user.setUserType(UserType.valueOf(paramMap.get("signUpUserType")[0]));

        try {
            user.setBirthDate(WebUtils.getDate(paramMap.get("signUpBirthDate")[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return user;
    }
}