package model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "studenttest")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @Column(name = "STUDENT_ID")
    private int studentId;

    @Column(name = "NAME")
    private String studentName;

    @Column(name = "EMAIL")
    private String studentEmail;

    @Column(name = "PHONE")
    private String studentPhone;

    @Column(name = "ENROLLMENT_DATE")
    private String enrollmentDate;

    public Student() {
    }

    public Student(int studentId, String studentName, String studentEmail, String studentPhone, String enrollmentDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                ", enrollmentDate='" + enrollmentDate + '\'' +
                '}';
    }
}