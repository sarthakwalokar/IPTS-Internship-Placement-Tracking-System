<%-- navbar.jsp - included in every admin page --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    // Session guard: redirect to login if no admin session
    if (session.getAttribute("adminUser") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    String adminUser = (String) session.getAttribute("adminUser");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<script src="<%= request.getContextPath() %>/js/app.js" defer></script>

<div class="navbar">
    <span class="brand">&#127979; IPTS - Admin Panel</span>
    <div>
        <span style="color:#90caf9;font-size:13px;">Welcome, <%= adminUser %></span>
        <a href="<%= request.getContextPath() %>/logout">Logout</a>
    </div>
</div>

<div class="layout">
<!-- Sidebar -->
<div class="sidebar">
    <div class="section-label">Main</div>
    <a href="<%= request.getContextPath() %>/dashboard">&#128200; Dashboard</a>

    <div class="section-label">Manage</div>
    <a href="<%= request.getContextPath() %>/student?action=list">&#127891; Students</a>
    <a href="<%= request.getContextPath() %>/company?action=list">&#127970; Companies</a>
    <a href="<%= request.getContextPath() %>/internship?action=list">&#128188; Internships</a>
    <a href="<%= request.getContextPath() %>/internship?action=applications">&#128203; Applications</a>
    <a href="<%= request.getContextPath() %>/placement?action=list">&#127942; Placements</a>

    <div class="section-label">Reports</div>
    <a href="<%= request.getContextPath() %>/report">&#128202; View Reports</a>

    <div class="section-label">Account</div>
    <a href="<%= request.getContextPath() %>/logout">&#128274; Logout</a>
</div>
<!-- Main content starts after this include -->
<div class="main">
