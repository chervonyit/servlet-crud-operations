package com.tc;

import com.tc.dao.Student;
import com.tc.dbconnectors.SqlConnector;

public class CrudMain {

    public static void main(String[] args) {
        SqlConnector connector = new SqlConnector();

        Student student = new Student("test", 12, 1);
        Student student1 = new Student("s2 updated", 12, 1);
        Student student2 = new Student("s4", 12, 1);
//        connector.create(student);
//        connector.create(student1);
//        connector.create(student2);

        //connector.create(student);
        //connector.read();
        //connector.update(student, student1);
        connector.delete(student1);
    }
}
