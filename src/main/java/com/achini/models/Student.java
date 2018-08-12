package com.achini.models;

import java.util.List;

/**
 * @author Chanaka Rathnayaka
 */

public class Student extends User {

    private int studentId;
    private int grade;
    private String school;
    private List<Tutor> tutors;

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getGrade() {
        return grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }
}
