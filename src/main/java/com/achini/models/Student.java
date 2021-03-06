package com.achini.models;

import java.util.Objects;
import java.util.Set;

/**
 * @author Chanaka Rathnayaka
 */

public class Student extends User {

    private int studentId;
    private int grade;
    private String school;
    private Set<Tutor> tutors;

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

    public Set<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(Set<Tutor> tutors) {
        this.tutors = tutors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return studentId == student.studentId &&
                grade == student.grade &&
                Objects.equals(tutors, student.tutors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, grade, tutors);
    }
}
