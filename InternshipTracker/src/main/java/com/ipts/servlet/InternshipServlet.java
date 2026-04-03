package com.ipts.servlet;

import com.ipts.dao.CompanyDAO;
import com.ipts.dao.InternshipDAO;
import com.ipts.model.Internship;
import com.ipts.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * InternshipServlet - manages internship listings and applications (Module 4)
 * URL: /internship  with action param
 */
@WebServlet("/internship")
public class InternshipServlet extends HttpServlet {

    private InternshipDAO dao    = new InternshipDAO();
    private CompanyDAO compDao   = new CompanyDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";
        String role = (String) req.getSession().getAttribute("role");

        switch (action) {
            case "add":
                // Admin: show add form with company dropdown
                if (!isAdmin(req)) { res.sendRedirect("index.jsp"); return; }
                req.setAttribute("companies", compDao.getAllCompanies());
                req.getRequestDispatcher("pages/admin/internshipForm.jsp").forward(req, res);
                break;

            case "delete":
                if (!isAdmin(req)) { res.sendRedirect("index.jsp"); return; }
                dao.deleteInternship(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("internship?action=list");
                break;

            case "applications":
                // Admin: view all applications and update status
                if (!isAdmin(req)) { res.sendRedirect("index.jsp"); return; }
                req.setAttribute("applications", dao.getAllApplications());
                req.getRequestDispatcher("pages/admin/applicationList.jsp").forward(req, res);
                break;

            case "apply":
                // Student: apply for an internship
                if (!"student".equals(role)) { res.sendRedirect("index.jsp"); return; }
                Student student = (Student) req.getSession().getAttribute("student");
                int internshipId = Integer.parseInt(req.getParameter("id"));
                boolean applied = dao.applyForInternship(internshipId, student.getStudentId());
                req.setAttribute("message", applied ? "Applied successfully!" : "Already applied or error.");
                // Fall through to list
                req.setAttribute("internships", dao.getAllInternships());
                req.getRequestDispatcher("pages/student/internshipList.jsp").forward(req, res);
                break;

            default:
                // List all internships (both admin and student can view)
                req.setAttribute("internships", dao.getAllInternships());
                String page = isAdmin(req) ? "pages/admin/internshipList.jsp" : "pages/student/internshipList.jsp";
                req.getRequestDispatcher(page).forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("save".equals(action)) {

            if (!isAdmin(req)) { 
                res.sendRedirect("index.jsp"); 
                return; 
            }

            Internship i = new Internship();

            // Safe parsing
            String companyStr = req.getParameter("companyId");
            String stipendStr = req.getParameter("stipend");
            String durationStr = req.getParameter("durationMonths");

            int companyId = 0;
            int stipend = 0;
            int duration = 0;

            try {
                if (companyStr != null && !companyStr.trim().isEmpty())
                    companyId = Integer.parseInt(companyStr);

                if (stipendStr != null && !stipendStr.trim().isEmpty())
                    stipend = Integer.parseInt(stipendStr);

                if (durationStr != null && !durationStr.trim().isEmpty())
                    duration = Integer.parseInt(durationStr);

            } catch (Exception e) {
                e.printStackTrace(); // debug ke liye
            }

            // Set values
            i.setCompanyId(companyId);
            i.setTitle(req.getParameter("title"));
            i.setDescription(req.getParameter("description"));
            i.setStipend((double) stipend); // ✅ fixed
            i.setDurationMonths(duration); // ✅ fixed
            i.setStartDate(req.getParameter("startDate"));
            i.setEndDate(req.getParameter("endDate"));
            i.setLocation(req.getParameter("location"));
            i.setLastApplyDate(req.getParameter("lastApplyDate"));

            boolean ok = dao.addInternship(i);

            req.setAttribute("message", ok ? "Internship added!" : "Error.");
            req.setAttribute("internships", dao.getAllInternships());
            req.getRequestDispatcher("pages/admin/internshipList.jsp").forward(req, res);
        }
    }

    private boolean isAdmin(HttpServletRequest req) {
        return "admin".equals(req.getSession().getAttribute("role"));
    }
}
