package com.achini.models;

import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */
public class Tutor extends User {
    private int tutorId;
    private String highestQualification;
    private List<Subject> subjects;
    private List<Student> students;

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
