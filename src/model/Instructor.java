package model;

import jakarta.persistence.*;

@Entity
@Table(name = "instructortest")


public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_generator")
    @SequenceGenerator(name="instructor_generator", sequenceName = "instructor_seq", allocationSize=1)
    @Column(name = "INSTRUCTOR_ID")
    private int instructorId;
    @Column(name = "NAME")
    private String instructorName;
    @Column(name = "EMAIL")
    private String instructorEmail;
    @Column(name = "DEPARTMENT")
    private String instructorDepartment;
    @Column(name = "PHONE")
    private String instructorPhone;

    public Instructor() {
    }

    public Instructor(int instructorId, String instructorName, String instructorEmail, String instructorDepartment, String instructorPhone) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorDepartment = instructorDepartment;
        this.instructorPhone = instructorPhone;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorDepartment() {
        return instructorDepartment;
    }

    public void setInstructorDepartment(String instructorDepartment) {
        this.instructorDepartment = instructorDepartment;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", instructorName='" + instructorName + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", instructorDepartment='" + instructorDepartment + '\'' +
                ", instructorPhone='" + instructorPhone + '\'' +
                '}';
    }
}
