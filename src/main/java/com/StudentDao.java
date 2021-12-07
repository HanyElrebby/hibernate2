package com;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public void saveStudent(Student student) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Student> selectStudents(int studentId) {
        List<Student> students = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "from Student s where s.id =:studentId";
            Query query = (Query) session.createQuery(hql);
            query.setParameter("studentId", studentId);
            students = query.list();
            students.forEach(student -> System.out.println(student.getName()));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return students;
    }

    public Student updateStudent(int id, String name) {
        Transaction transaction = null;
        Student student = new Student();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            student.setName(name);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }
    public void delete(int id) {
        Transaction transaction = null;
        Student student = new Student();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            session.delete(student);
            transaction.commit();
            session.close();
        } catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
