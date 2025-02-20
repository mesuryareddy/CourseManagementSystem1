package model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "enrollmenttest")

public class Enrollment {
    @Id
    @Column(name = "ENROLLMENT_ID")
    private int enrollmentId;
    @Column(name = "STUDENT_ID")
    private int studentId;
    @Column(name = "COURSE_ID")
    private int courseId;
    @Column(name = "ENROLLMENT_DATE")
    private Date enrollmentDate;
    @Column(name = "GRADE")
    private String enrollmentGrade;
}
