package com.mindflytech.education.oop.records;

import java.util.Objects;

/**
 * POJO stands for Plain Old Java Object
 */
public class Pojo {
    private long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String address;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pojo pojo = (Pojo) o;
        return studentId == pojo.studentId && Objects.equals(studentFirstName, pojo.studentFirstName) && Objects.equals(studentLastName, pojo.studentLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentFirstName, studentLastName);
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "studentId=" + studentId +
                ", studentFirstName='" + studentFirstName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                '}';
    }
}
