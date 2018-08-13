package com.achini.service;

import com.achini.dataaccess.FeeDataAccess;
import com.achini.dataaccess.TutorDataAccess;
import com.achini.dataaccess.UserDataAccess;
import com.achini.models.Subject;
import com.achini.models.Tutor;
import com.achini.models.User;

import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */
public class TutorManager {
    private TutorDataAccess tutorDataAccess;
    private FeeDataAccess feeDataAccess;
    private UserDataAccess userDataAccess;

    public TutorManager() {
        this.tutorDataAccess = new TutorDataAccess();
        this.feeDataAccess = new FeeDataAccess();
        this.userDataAccess = new UserDataAccess();
    }

    public void registerTutor(Tutor tutor) {
        tutor.setTutorId(this.tutorDataAccess.insertTutor(tutor));
        this.feeDataAccess.insertOrUpdateFee(tutor);
    }

    public Tutor getTutor(User user) {
        Tutor tutor = tutorDataAccess.getTutor(user.getUserId());
        List<Subject> subjects = feeDataAccess.getClasses(tutor.getTutorId());
        tutor.setUsername(user.getUsername());
        tutor.setUserId(user.getUserId());
        tutor.setBirthDate(user.getBirthDate());
        tutor.setEmail(user.getEmail());
        tutor.setEnrolledDate(user.getEnrolledDate());
        tutor.setName(user.getName());
        tutor.setSubjects(subjects);
        return tutor;
    }

    public User saveTutor(Tutor tutor) {
        tutorDataAccess.updateTutor(tutor);
        this.feeDataAccess.insertOrUpdateFee(tutor);
        return userDataAccess.updateUser(tutor);
    }
}
