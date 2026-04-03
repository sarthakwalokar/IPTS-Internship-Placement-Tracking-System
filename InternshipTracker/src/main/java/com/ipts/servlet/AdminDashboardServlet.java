package com.ipts.servlet;

import com.ipts.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * AdminDashboardServlet - loads live stats and forwards to dashboard JSP (Module 8)
 * URL: /dashboard
 * The admin/dashboard.jsp can also load DAO directly, but this servlet
 * keeps logic out of JSP for cleaner code.
 */
@WebServlet("/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Only admin can access
        if (!"admin".equals(req.getSession().getAttribute("role"))) {
            res.sendRedirect("index.jsp");
            return;
        }

        // Load counts from DAOs
        StudentDAO    stuDao  = new StudentDAO();
        CompanyDAO    comDao  = new CompanyDAO();
        InternshipDAO intDao  = new InternshipDAO();
        PlacementDAO  plaDao  = new PlacementDAO();

        int totalStudents    = stuDao.countStudents();
        int placedStudents   = stuDao.countPlacedStudents();
        int totalCompanies   = comDao.countCompanies();
        int totalInternships = intDao.countInternships();
        int totalPlacements  = plaDao.countPlacements();

        // Calculate placement percentage
        double placePct = totalStudents > 0 ? (placedStudents * 100.0 / totalStudents) : 0;

        // Set attributes for JSP
        req.setAttribute("totalStudents",    totalStudents);
        req.setAttribute("placedStudents",   placedStudents);
        req.setAttribute("unplacedStudents", totalStudents - placedStudents);
        req.setAttribute("totalCompanies",   totalCompanies);
        req.setAttribute("totalInternships", totalInternships);
        req.setAttribute("totalPlacements",  totalPlacements);
        req.setAttribute("placementPct",     String.format("%.1f", placePct));

        // Recent placements for the table (last 5)
        req.setAttribute("recentPlacements", plaDao.getAllPlacements());

        req.getRequestDispatcher("admin/dashboard.jsp").forward(req, res);
    }
}
