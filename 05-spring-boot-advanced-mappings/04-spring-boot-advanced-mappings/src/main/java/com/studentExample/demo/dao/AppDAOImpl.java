package com.studentExample.demo.dao;

import com.studentExample.demo.entity.Lecture;
import com.studentExample.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    //define field for entitymanager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public AppDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findStudentById(int theId) {
        return entityManager.find(Student.class, theId);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        entityManager.remove(entityManager.find(Student.class, theId));
    }

    @Override
    public List<Student> findAllStudentsById(List<Integer> list) {
        return entityManager.createQuery("from Student where id in (:list)", Student.class).setParameter("list", list).getResultList();
    }

    @Override
    public Lecture findLectureById(int theId) {
        return entityManager.find(Lecture.class, theId);
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void update(Lecture theLecture) {
        entityManager.merge(theLecture);
    }

    @Override
    @Transactional
    public void save(Lecture theLecture) {
        entityManager.persist(theLecture);
    }

    @Override
    @Transactional
    public void addLectureToStudent(int studentId, int lectureId) {
        Student student = entityManager.find(Student.class, studentId);
        Lecture lecture = entityManager.find(Lecture.class, lectureId);
        student.getLectures().add(lecture);
        lecture.getStudents().add(student);
    }

    @Override
    public List<Integer> findStudentsByLectureId(int lectureId) {
        Lecture lecture = entityManager.find(Lecture.class, lectureId);
        List<Integer> list = entityManager.createQuery("select id from Student where :lecture member of lectures", Integer.class).setParameter("lecture", lecture).getResultList();
        return list;
    }
}
