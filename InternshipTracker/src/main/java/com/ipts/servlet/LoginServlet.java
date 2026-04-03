package com.ipts.servlet;

import com.ipts.dao.AdminDAO;
import com.ipts.dao.StudentDAO;
import com.ipts.model.Admin;
import com.ipts.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * LoginServlet - handles login for both Admin and Student (Module 1 & 2)
 * URL: /login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role     = req.getParameter("role"); // "admin" or "student"

        HttpSession session = req.getSession();

        if ("admin".equals(role)) {
            // Use AdminDAO to verify credentials
            AdminDAO adminDAO = new AdminDAO();
            Admin admin = adminDAO.login(username, password);
            if (admin != null) {
                session.setAttribute("adminUser", admin.getFullName());
                session.setAttribute("adminObj",  admin);
                session.setAttribute("role", "admin");
                res.sendRedirect("dashboard"); // go to AdminDashboardServlet
            } else {
                req.setAttribute("error", "Invalid admin credentials.");
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        } else {
            // Check student table
            StudentDAO dao = new StudentDAO();
            Student student = dao.login(username, password);
            if (student != null) {
                session.setAttribute("student", student);
                session.setAttribute("role", "student");
                res.sendRedirect("student/dashboard.jsp"); // go to student dashboard
            } else {
                req.setAttribute("error", "Invalid student credentials.");
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        }
    }

}
