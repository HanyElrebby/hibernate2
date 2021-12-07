package com;

public class MainClass {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        studentDao.selectStudents(3);
    }
}
