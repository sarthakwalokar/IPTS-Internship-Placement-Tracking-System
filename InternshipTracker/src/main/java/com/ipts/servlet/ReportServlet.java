package com.ipts.servlet;

import com.ipts.dao.PlacementDAO;
import com.ipts.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * ReportServlet - generates placement and internship reports (Module 7)
 * URL: /report
 */
@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    private PlacementDAO placDao = new PlacementDAO();
    private StudentDAO stuDao    = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isAdmin(req)) { res.sendRedirect("index.jsp"); return; }

        // Gather all report data
        req.setAttribute("branchReport",   placDao.getBranchWiseReport());    // branch-wise placements
        req.setAttribute("companyReport",  placDao.getCompanyWiseReport());   // company-wise placements
        req.setAttribute("totalStudents",  stuDao.countStudents());
        req.setAttribute("placedStudents", stuDao.countPlacedStudents());
        req.setAttribute("totalPlacements",placDao.countPlacements());

        // Calculate placement percentage
        int total  = stuDao.countStudents();
        int placed = stuDao.countPlacedStudents();
        double pct = total > 0 ? (placed * 100.0 / total) : 0;
        req.setAttribute("placementPct", String.format("%.1f", pct));

        req.getRequestDispatcher("pages/admin/report.jsp").forward(req, res);
    }

    private boolean isAdmin(HttpServletRequest req) {
        return "admin".equals(req.getSession().getAttribute("role"));
    }
}
