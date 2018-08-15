package com.achini.service;

import com.achini.dataaccess.FeeDataAccess;
import com.achini.dataaccess.TutorDataAccess;
import com.achini.dataaccess.UserDataAccess;
import com.achini.models.Subject;
import com.achini.models.Tutor;
import com.achini.models.User;
import com.achini.models.types.ClassType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Tutor tutor = tutorDataAccess.getTutorForUser(user.getUserId());
       /* tutor.setUsername(user.getUsername());
        tutor.setUserId(user.getUserId());
        tutor.setBirthDate(user.getBirthDate());
        tutor.setEmail(user.getEmail());
        tutor.setEnrolledDate(user.getEnrolledDate());
        tutor.setName(user.getName());*/
        return tutor;
    }

    public User saveTutor(Tutor tutor) {
        tutorDataAccess.updateTutor(tutor);
        this.feeDataAccess.insertOrUpdateFee(tutor);
        return userDataAccess.updateUser(tutor);
    }

    public Map<String, Map<ClassType, List<Tutor>>> getTutorsForGrade(int grade) {
        List<Tutor> tutors = tutorDataAccess.getTutorsForGrade(grade);
        Map<String, Map<ClassType, List<Tutor>>> subjectMap = new HashMap<>();
        for (Tutor tutor : tutors) {
            Subject subject = tutor.getSubjects().iterator().next();
            String subjectMapKey = subject.getSubjectId() + "~" + subject.getName();

            if (subjectMap.containsKey(subjectMapKey)) {
                Map<ClassType, List<Tutor>> classTypeTutorMap = subjectMap.get(subjectMapKey);
                if (classTypeTutorMap.containsKey(subject.getFee().getClassType())) {
                    List<Tutor> tutorList = classTypeTutorMap.get(subject.getFee().getClassType());
                    tutorList.add(tutor);
                } else {
                    List<Tutor> tutorList = new ArrayList<>();
                    tutorList.add(tutor);
                    classTypeTutorMap.put(subject.getFee().getClassType(), tutorList);
                }
            } else {
                Map<ClassType, List<Tutor>> classTypeTutorMap = new HashMap<>();
                subjectMap.put(subjectMapKey, classTypeTutorMap);
                List<Tutor> tutorList = new ArrayList<>();
                tutorList.add(tutor);
                classTypeTutorMap.put(subject.getFee().getClassType(), tutorList);
            }
        }
        return subjectMap;
    }
}
