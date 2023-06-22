package com.studentExample.demo.dao;

import com.studentExample.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class StudentDAOJpaImp implements StudentDAO{

    //Define field for entitymanager
    private EntityManager entityManager;

    //Set up constructor injection
    @Autowired
    public StudentDAOJpaImp(EntityManager manager){
        entityManager = manager;
    }
    @Override
    public List<Student> findAll() {

        //Create query
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);

        //Execute and Get results to a list
        List<Student> students = query.getResultList();


        //Return results
        return students;
    }

    @Override
    public Student findById(int theId) {
        return entityManager.find(Student.class, theId);
    }

    @Override
    public Student save(Student theStudent) {
        //Save Student
        Student dbStudent = entityManager.merge(theStudent);
        //Return dbStudent
        return dbStudent;
    }

    @Override
    public void deleteById(int theId) {
        //Find Student by id
        Student student = entityManager.find(Student.class, theId);
        //Remove the student
        entityManager.remove(student);
    }
}
