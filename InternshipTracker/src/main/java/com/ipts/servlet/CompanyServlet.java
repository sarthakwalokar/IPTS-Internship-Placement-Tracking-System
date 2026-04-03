package com.ipts.servlet;

import com.ipts.dao.CompanyDAO;
import com.ipts.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * CompanyServlet - handles add/edit/delete/list for companies (Module 3)
 * URL: /company  with action param
 */
@WebServlet("/company")
public class CompanyServlet extends HttpServlet {

    private CompanyDAO dao = new CompanyDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isAdmin(req)) { res.sendRedirect("index.jsp"); return; }

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                req.getRequestDispatcher("pages/admin/companyForm.jsp").forward(req, res);
                break;

            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("company", dao.getCompanyById(id));
                req.getRequestDispatcher("pages/admin/companyForm.jsp").forward(req, res);
                break;

            case "delete":
                dao.deleteCompany(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("company?action=list");
                break;

            case "search":
                String keyword = req.getParameter("keyword");
                req.setAttribute("companies", dao.searchCompanies(keyword));
                req.setAttribute("keyword", keyword);
                req.getRequestDispatcher("pages/admin/companyList.jsp").forward(req, res);
                break;

            default:
                req.setAttribute("companies", dao.getAllCompanies());
                req.getRequestDispatcher("pages/admin/companyList.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isAdmin(req)) { res.sendRedirect("index.jsp"); return; }

        String action = req.getParameter("action");

        // Build Company from form
        Company c = new Company();
        c.setCompanyName(req.getParameter("companyName"));
        c.setIndustry(req.getParameter("industry"));
        c.setWebsite(req.getParameter("website"));
        c.setContactPerson(req.getParameter("contactPerson"));
        c.setContactEmail(req.getParameter("contactEmail"));
        c.setContactPhone(req.getParameter("contactPhone"));
        c.setAddress(req.getParameter("address"));

        if ("save".equals(action)) {
            boolean ok = dao.addCompany(c);
            req.setAttribute("message", ok ? "Company added!" : "Error adding company.");
        } else if ("update".equals(action)) {
            c.setCompanyId(Integer.parseInt(req.getParameter("companyId")));
            boolean ok = dao.updateCompany(c);
            req.setAttribute("message", ok ? "Company updated!" : "Error updating company.");
        }

        req.setAttribute("companies", dao.getAllCompanies());
        req.getRequestDispatcher("pages/admin/companyList.jsp").forward(req, res);
    }

    private boolean isAdmin(HttpServletRequest req) {
        return "admin".equals(req.getSession().getAttribute("role"));
    }
}
