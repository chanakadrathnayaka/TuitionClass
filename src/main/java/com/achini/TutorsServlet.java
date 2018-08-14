package com.achini;

import com.achini.models.Tutor;
import com.achini.models.types.ClassType;
import com.achini.service.TutorManager;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author Chanaka Rathnayaka
 */
@WebServlet(
        name = "TutorsServlet",
        urlPatterns = {"/tutors/grade/*", "/tutors/class/*"}
)
public class TutorsServlet extends HttpServlet {
    private TutorManager tutorManager;
    private Gson gson;

    public TutorsServlet() {
        this.tutorManager = new TutorManager();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case "/tutors/grade":
                processTutorGrade(req, resp);
                break;
        }
    }

    private void processTutorGrade(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        String a = req.getPathInfo();
        int grade = Integer.parseInt(a.substring(1, a.length()));

        Map<String, Map<ClassType, List<Tutor>>> tutors = tutorManager.getTutorsForGrade(grade);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        gson.toJson(tutors, out);
        out.flush();
    }
}
