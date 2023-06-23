package com.studentExample.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student_detail")
@NoArgsConstructor
@AllArgsConstructor
public @Data class StudentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="gpa")
    private double gpa;

    @Column(name="hobby")
    private String hobby;

    @OneToOne(mappedBy = "studentDetail", cascade = CascadeType.ALL)
    private Student student;

    // This constructor is used for creating a student detail object without an id
    public StudentDetail(double gpa, String hobby) {
        this.gpa = gpa;
        this.hobby = hobby;
    }

    @Override
    public String toString(){
        return "StudentDetail [id=" + id + ", gpa=" + gpa + ", hobby=" + hobby + "]";
    }
}
