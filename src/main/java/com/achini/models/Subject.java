package com.achini.models;

import java.util.Objects;

/**
 * @author Chanaka Rathnayaka
 */
public class Subject {
    private int subjectId;
    private String name;
    private int grade;
    private Fee fee;

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return subjectId == subject.subjectId &&
                grade == subject.grade &&
                Objects.equals(fee, subject.fee);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subjectId, grade, fee);
    }
}
