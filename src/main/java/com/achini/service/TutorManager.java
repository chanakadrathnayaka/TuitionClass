package com.achini.service;

import com.achini.dataaccess.FeeDataAccess;
import com.achini.dataaccess.TutorDataAccess;
import com.achini.models.Tutor;

/**
 * @author Chanaka Rathnayaka
 */
public class TutorManager {
private TutorDataAccess tutorDataAccess;
private FeeDataAccess feeDataAccess;

    public TutorManager() {
        this.tutorDataAccess = new TutorDataAccess();
        this.feeDataAccess = new FeeDataAccess();
    }

    public void saveTutorDetails(Tutor tutor){
        tutor.setTutorId(this.tutorDataAccess.insertTutor(tutor));
        this.feeDataAccess.insertFee(tutor);
    }
}
