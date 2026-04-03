package com.ipts.servlet;

import com.ipts.dao.StudentDAO;
import com.ipts.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * StudentServlet - handles add/edit/delete/list for students (Module 2)
 * URL: /student  with action param: list, add, edit, delete, save, update
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentDAO dao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Only admin can manage students
        if (!isAdmin(req)) {
            res.sendRedirect("index.jsp");
            return;
        }

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                // Show add form
                req.getRequestDispatcher("pages/admin/studentForm.jsp").forward(req, res);
                break;

            case "edit":
                // Load student data and show edit form
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("student", dao.getStudentById(id));
                req.getRequestDispatcher("pages/admin/studentForm.jsp").forward(req, res);
                break;

            case "delete":
                // Delete and redirect back to list
                dao.deleteStudent(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("student?action=list");
                break;

            case "search":
                // Search/filter students (Module 6)
                String keyword  = req.getParameter("keyword");
                String branch   = req.getParameter("branch");
                String year     = req.getParameter("year");
                req.setAttribute("students", dao.searchStudents(keyword, branch, year));
                req.setAttribute("keyword", keyword);
                req.getRequestDispatcher("pages/admin/studentList.jsp").forward(req, res);
                break;

            default:
                // List all students
                req.setAttribute("students", dao.getAllStudents());
                req.getRequestDispatcher("pages/admin/studentList.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isAdmin(req)) {
            res.sendRedirect("index.jsp");
            return;
        }

        String action = req.getParameter("action");

        // Build Student object from form params
        Student s = new Student();
        s.setUsername(req.getParameter("username"));
        s.setPassword(req.getParameter("password"));
        s.setRollNo(req.getParameter("rollNo"));
        s.setFullName(req.getParameter("fullName"));
        s.setEmail(req.getParameter("email"));
        s.setPhone(req.getParameter("phone"));
        s.setBranch(req.getParameter("branch"));
        s.setBatchYear(Integer.parseInt(req.getParameter("batchYear")));
        s.setCgpa(Double.parseDouble(req.getParameter("cgpa")));
        s.setResumeLink(req.getParameter("resumeLink"));

        if ("save".equals(action)) {
            // Add new student
            boolean ok = dao.addStudent(s);
            req.setAttribute("message", ok ? "Student added successfully!" : "Error adding student.");
        } else if ("update".equals(action)) {
            // Update existing student
            s.setStudentId(Integer.parseInt(req.getParameter("studentId")));
            boolean ok = dao.updateStudent(s);
            req.setAttribute("message", ok ? "Student updated successfully!" : "Error updating student.");
        }

        // Reload list after save/update
        req.setAttribute("students", dao.getAllStudents());
        req.getRequestDispatcher("pages/admin/studentList.jsp").forward(req, res);
    }

    /** Helper: check admin session */
    private boolean isAdmin(HttpServletRequest req) {
        return "admin".equals(req.getSession().getAttribute("role"));
    }
}
