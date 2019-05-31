package com.tc.servlet;

import com.tc.dao.Student;
import com.tc.dbconnectors.SqlConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "crudServlet", urlPatterns = "/rest")
public class CrudServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        SqlConnector connector = new SqlConnector();
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(connector.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        int school_id = Integer.parseInt(req.getParameter("school_id"));

        SqlConnector sqlConnector = new SqlConnector();
        sqlConnector.create(new Student(name, age, school_id));
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        SqlConnector sqlConnector = new SqlConnector();
        sqlConnector.delete(new Student(name, age, 1));
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String candidate_name = req.getParameter("candidate_name");
        int candidate_age = Integer.parseInt(req.getParameter("candidate_age"));
        int candidate_school_id = Integer.parseInt(req.getParameter("candidate_school_id"));

        String current_name = req.getParameter("current_name");
        int current_age = Integer.parseInt(req.getParameter("current_age"));
        SqlConnector connector = new SqlConnector();
        connector.update(new Student(current_name, current_age, 1),
                new Student(candidate_name, candidate_age, candidate_school_id));
    }
}
