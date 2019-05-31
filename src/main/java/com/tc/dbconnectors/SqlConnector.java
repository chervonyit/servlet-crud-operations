package com.tc.dbconnectors;

import com.tc.dao.Student;
import com.tc.datasources.DBDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlConnector implements IConnector {

    private DBDataSource ds = new DBDataSource();

    @Override
    public void create(Student student) {
        Connection c = null;
        try {
            c = getConnection();
            Statement statement = c.createStatement();

            String query = String.format("INSERT INTO student(name, age, school_id) VALUES('%s', '%s', '%s')",
                    student.getName(),
                    student.getAge(),
                    student.getSchoolId());

            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Student> read() {
        List<Student> studentList = new ArrayList<>();
        try (Connection c = getConnection()) {
            Statement statement = c.createStatement();

            String query = "SELECT * FROM student";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String name = result.getString("name");
                int age = result.getInt("age");
                int school_id = result.getInt("school_id");
                studentList.add(new Student(name, age, school_id));
            }
            return studentList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student current, Student candidate) {
        try (Connection c = getConnection()) {
            String query = "UPDATE student SET name=?, age=?, school_id=? WHERE name=? AND age=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getAge());
            ps.setInt(3, candidate.getSchoolId());
            ps.setString(4, current.getName());
            ps.setInt(5, current.getAge());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Student student) {
        try (Connection c = getConnection()) {
            c.prepareStatement(String.format("DELETE FROM student WHERE name='%s' AND age='%s'",
                    student.getName(),
                    student.getAge())).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return ds.getConnection("root", "root");
    }
}
