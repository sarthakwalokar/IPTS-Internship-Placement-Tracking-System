package com.ipts.servlet;

import com.ipts.dao.CompanyDAO;
import com.ipts.dao.PlacementDAO;
import com.ipts.dao.StudentDAO;
import com.ipts.model.Placement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * PlacementServlet - manages placement records (Module 5)
 * URL: /placement
 */
@WebServlet("/placement")
public class PlacementServlet extends HttpServlet {

    private PlacementDAO dao    = new PlacementDAO();
    private CompanyDAO compDao  = new CompanyDAO();
    private StudentDAO stuDao   = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isAdmin(req)) { res.sendRedirect("index.jsp"); return; }

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                // Show form with company and student dropdowns
                req.setAttribute("companies", compDao.getAllCompanies());
                req.setAttribute("students", stuDao.getAllStudents());
                req.getRequestDispatcher("pages/admin/placementForm.jsp").forward(req, res);
                break;

            case "delete":
                dao.deletePlacement(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("placement?action=list");
                break;

            default:
                req.setAttribute("placements", dao.getAllPlacements());
                req.getRequestDispatcher("pages/admin/placementList.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isAdmin(req)) { res.sendRedirect("index.jsp"); return; }

        // Build Placement from form params
        Placement p = new Placement();
        p.setCompanyId(Integer.parseInt(req.getParameter("companyId")));
        p.setStudentId(Integer.parseInt(req.getParameter("studentId")));
        p.setJobRole(req.getParameter("jobRole"));
        p.setPackageLpa(Double.parseDouble(req.getParameter("packageLpa")));
        p.setOfferDate(req.getParameter("offerDate"));
        p.setJoiningDate(req.getParameter("joiningDate"));
        p.setPlacementType(req.getParameter("placementType"));
        p.setRemarks(req.getParameter("remarks"));

        boolean ok = dao.addPlacement(p);
        req.setAttribute("message", ok ? "Placement recorded successfully!" : "Error saving placement.");
        req.setAttribute("placements", dao.getAllPlacements());
        req.getRequestDispatcher("pages/admin/placementList.jsp").forward(req, res);
    }

    private boolean isAdmin(HttpServletRequest req) {
        return "admin".equals(req.getSession().getAttribute("role"));
    }
}
