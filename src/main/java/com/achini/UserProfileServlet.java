package com.achini;

import com.achini.models.*;
import com.achini.models.types.ClassType;
import com.achini.models.types.UserType;
import com.achini.service.StudentManager;
import com.achini.service.SubjectManager;
import com.achini.service.TutorManager;
import com.achini.servlets.utils.Constants;
import com.achini.servlets.utils.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Chanaka Rathnayaka
 */
@WebServlet(
        name = "UserProfileServlet",
        urlPatterns = "/profile"
)
public class UserProfileServlet extends HttpServlet {
    private StudentManager studentManager;
    private TutorManager tutorManager;
    private SubjectManager subjectManager;

    public UserProfileServlet() {
        this.studentManager = new StudentManager();
        this.tutorManager = new TutorManager();
        this.subjectManager = new SubjectManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String page;
        request.setAttribute("password", String.valueOf(user.getPassword()));
        request.setAttribute("supportedGrades", Constants.SUPPORTED_GRADES);
        if (user.getUserType().equals(UserType.STUDENT)) {
            Student student = this.studentManager.getStudent(user);
            request.setAttribute("student", student);
            page = "student-profile.jsp";
        } else {
            Tutor tutor = this.tutorManager.getTutor(user);
            request.setAttribute("classTypes", ClassType.values());
            request.setAttribute("tutor", tutor);
            request.setAttribute("subjects", subjectManager.getAllSubject());
            page = "tutor-profile.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(page);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        User newUser;
        if (user.getUserType().equals(UserType.STUDENT)) {
            newUser = this.studentManager.saveStudent(extractStudent(request.getParameterMap()));
        } else {
            newUser = this.tutorManager.saveTutor(extractTutor(request.getParameterMap()));
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", newUser);
        session.setMaxInactiveInterval(30 * 60);

        doGet(request, response);
    }

    private void extractUser(Map<String, String[]> paramMap, User user) {

        user.setName(paramMap.get("updateName")[0]);
        user.setEmail(paramMap.get("updateEmail")[0]);
        user.setUsername(paramMap.get("updateUserName")[0]);
        user.setPassword(paramMap.get("updatePassword")[0].toCharArray());

        try {
            user.setBirthDate(WebUtils.getDate(paramMap.get("updateBirthDate")[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Student extractStudent(Map<String, String[]> paramMap) {
        Student student = new Student();
        extractUser(paramMap, student);
        student.setUserId(Integer.parseInt(paramMap.get("updateUserId")[0]));
        student.setGrade(Integer.parseInt(paramMap.get("updateGrade")[0]));
        student.setSchool(paramMap.get("updateSchool")[0]);
        String[] tutorIds = paramMap.get("updateTutor");
        String[] subjectIds = paramMap.get("updateSubjects");
        String[] classTypes = paramMap.get("updateClassType");
        String[] feeIds = paramMap.get("updateFeeId");
        String[] feeAmounts = paramMap.get("updateFee");
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


    private Tutor extractTutor(Map<String, String[]> paramMap) {
        Tutor tutor = new Tutor();
        extractUser(paramMap, tutor);
        tutor.setTutorId(Integer.parseInt(paramMap.get("updateTutorId")[0]));
        tutor.setUserId(Integer.parseInt(paramMap.get("updateUserId")[0]));
        tutor.setHighestQualification(paramMap.get("updateHighestQualification")[0]);
        tutor.setSubjects(extractSubject(paramMap));

        return tutor;
    }

    private Set<Subject> extractSubject(Map<String, String[]> paramMap) {

        String[] subjectIds = paramMap.get("updateSubjects");
        String[] grades = paramMap.get("updateGrade");
        String[] classTypes = paramMap.get("updateClassType");
        String[] fees = paramMap.get("updateFee");

        Set<Subject> subjects = new HashSet<>(subjectIds.length);

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
