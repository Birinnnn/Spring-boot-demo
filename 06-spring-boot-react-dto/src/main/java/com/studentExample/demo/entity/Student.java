package com.studentExample.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="student")
@NoArgsConstructor
@AllArgsConstructor
public @Data
class Student {
    // This is the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    // These are the columns
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    // This is the foreign key
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="student_detail_id")
    private StudentDetail studentDetail;

    // This is the relationship lecture-student
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "lecture_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id"))
    private List<Lecture> lectures;

    // This constructor is used for creating a student object without an id
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentDetail = null;
    }

    @Override
    public String toString(){
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }

    public void addLecture(Lecture lecture){
        if(lectures == null){
            lectures = new ArrayList<>();
        }
        lectures.add(lecture);
    }
}
