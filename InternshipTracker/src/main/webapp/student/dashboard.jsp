<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ipts.model.Student, com.ipts.dao.InternshipDAO, com.ipts.dao.PlacementDAO" %>
<%@ page import="java.util.List" %>
<%
    Student stu = (Student) session.getAttribute("student");
    if (stu == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    InternshipDAO intDao = new InternshipDAO();
    PlacementDAO  plaDao = new PlacementDAO();

    List<Object[]> myApplications = intDao.getApplicationsByStudent(stu.getStudentId());
    List<com.ipts.model.Placement> myPlacements = plaDao.getPlacementsByStudent(stu.getStudentId());
    int openInternships = intDao.countInternships();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Dashboard - IPTS</title>
</head>

<body>

<!-- ✅ FIXED LINE -->
<jsp:include page="/WEB-INF/studentNavbar.jsp" />

<div class="page-title">&#128200; My Dashboard</div>

<div class="stats-grid">
    <div class="stat-card teal">
        <div class="number"><%= myApplications.size() %></div>
        <div class="label">My Applications</div>
    </div>
    <div class="stat-card green">
        <div class="number"><%= myPlacements.size() %></div>
        <div class="label">Placements</div>
    </div>
    <div class="stat-card">
        <div class="number"><%= openInternships %></div>
        <div class="label">Open Internships</div>
    </div>
    <div class="stat-card <%= stu.isPlaced() ? "green" : "orange" %>">
        <div class="number"><%= stu.isPlaced() ? "&#10003;" : "&#10007;" %></div>
        <div class="label"><%= stu.isPlaced() ? "Placed" : "Not Placed Yet" %></div>
    </div>
</div>

</body>
</html>