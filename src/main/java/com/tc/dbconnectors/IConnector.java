package com.tc.dbconnectors;

import com.tc.dao.Student;
import java.util.List;

public interface IConnector {

    void create(Student student);

    List<Student> read();

    void update(Student current, Student candidate);

    void delete(Student student);
}
