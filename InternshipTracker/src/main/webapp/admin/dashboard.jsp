<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ipts.dao.*" %>
<%
    // Session guard
    if (session.getAttribute("adminUser") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    // Load dashboard counts from DAOs
    StudentDAO   stuDao  = new StudentDAO();
    CompanyDAO   comDao  = new CompanyDAO();
    InternshipDAO intDao = new InternshipDAO();
    PlacementDAO  plaDao = new PlacementDAO();

    int totalStudents   = stuDao.countStudents();
    int placedStudents  = stuDao.countPlacedStudents();
    int totalCompanies  = comDao.countCompanies();
    int totalInternships= intDao.countInternships();
    int totalPlacements = plaDao.countPlacements();
    double placePct     = totalStudents > 0 ? (placedStudents * 100.0 / totalStudents) : 0;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - IPTS</title>
</head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#128200; Admin Dashboard</div>

    <!-- ---- Stats Cards (Module 8) ---- -->
    <div class="stats-grid">
        <div class="stat-card">
            <div class="number"><%= totalStudents %></div>
            <div class="label">Total Students</div>
        </div>
        <div class="stat-card green">
            <div class="number"><%= placedStudents %></div>
            <div class="label">Placed Students</div>
        </div>
        <div class="stat-card orange">
            <div class="number"><%= totalStudents - placedStudents %></div>
            <div class="label">Unplaced Students</div>
        </div>
        <div class="stat-card teal">
            <div class="number"><%= totalCompanies %></div>
            <div class="label">Companies</div>
        </div>
        <div class="stat-card">
            <div class="number"><%= totalInternships %></div>
            <div class="label">Internship Listings</div>
        </div>
        <div class="stat-card green">
            <div class="number"><%= String.format("%.1f", placePct) %>%</div>
            <div class="label">Placement Rate</div>
        </div>
    </div>

    <!-- ---- Quick Links ---- -->
    <div class="table-box" style="padding:20px;">
        <h3>Quick Actions</h3>
        <div style="padding:16px;display:flex;gap:12px;flex-wrap:wrap;">
            <a href="<%= request.getContextPath() %>/student?action=add"     class="btn btn-primary">+ Add Student</a>
            <a href="<%= request.getContextPath() %>/company?action=add"     class="btn btn-success">+ Add Company</a>
            <a href="<%= request.getContextPath() %>/internship?action=add"  class="btn btn-warning">+ Add Internship</a>
            <a href="<%= request.getContextPath() %>/placement?action=add"   class="btn btn-primary">+ Record Placement</a>
            <a href="<%= request.getContextPath() %>/report"                 class="btn btn-success">View Reports</a>
        </div>
    </div>

    <!-- ---- Recent Placements ---- -->
    <div class="table-box">
        <h3>Recent Placements</h3>
        <%
            java.util.List<com.ipts.model.Placement> recent = plaDao.getAllPlacements();
            int showCount = Math.min(recent.size(), 5); // show last 5
        %>
        <% if (showCount == 0) { %>
            <p style="padding:16px;color:#888;">No placement records yet.</p>
        <% } else { %>
        <table>
            <tr>
                <th>Student</th><th>Roll No</th><th>Company</th>
                <th>Role</th><th>Package (LPA)</th><th>Type</th>
            </tr>
            <% for (int i = 0; i < showCount; i++) {
                com.ipts.model.Placement pl = recent.get(i); %>
            <tr>
                <td><%= pl.getStudentName() %></td>
                <td><%= pl.getRollNo() %></td>
                <td><%= pl.getCompanyName() %></td>
                <td><%= pl.getJobRole() %></td>
                <td><%= pl.getPackageLpa() %></td>
                <td><span class="badge badge-placed"><%= pl.getPlacementType() %></span></td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </div>

</div></div><!-- close .main and .layout -->
</body>

</html>
