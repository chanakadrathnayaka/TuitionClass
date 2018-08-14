package com.achini.models;

import java.util.Objects;
import java.util.Set;

/**
 * @author Chanaka Rathnayaka
 */
public class Tutor extends User {
    private int tutorId;
    private String highestQualification;
    private Set<Subject> subjects;
    private Set<Student> students;

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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tutor)) return false;
        Tutor tutor = (Tutor) o;
        return tutorId == tutor.tutorId &&
                Objects.equals(subjects, tutor.subjects) &&
                Objects.equals(students, tutor.students);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tutorId, subjects, students);
    }
}
