package com.achini.service;

import com.achini.dataaccess.SubjectDataAccess;
import com.achini.models.Subject;

import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */
public class SubjectManager {

    private SubjectDataAccess subjectDataAccess;

    public SubjectManager() {
        subjectDataAccess = new SubjectDataAccess();
    }

    public List<Subject> getAllSubject() {
        return subjectDataAccess.getAllSubjects();
    }
}
