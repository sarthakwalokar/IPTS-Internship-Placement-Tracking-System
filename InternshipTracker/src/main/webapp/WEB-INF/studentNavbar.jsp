<%-- studentNavbar.jsp - included in every student page --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ipts.model.Student" %>
<%
    // Session guard for student
    Student stu = (Student) session.getAttribute("student");
    if (stu == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<script src="<%= request.getContextPath() %>/js/app.js" defer></script>

<div class="navbar" style="background:#fffff0;">
    <span class="brand">&#127891; IPTS - Student Portal</span>
    <div>
        <span style="color:#b2dfdb;font-size:13px;">Welcome, <%= stu.getFullName() %></span>
        <a href="<%= request.getContextPath() %>/logout">Logout</a>
    </div>
</div>

<div class="layout">
<div class="sidebar" style="background:#fffff;">
    <div class="section-label" style="color:#80cbc4;">Main</div>
    <a href="<%= request.getContextPath() %>/student/dashboard.jsp">&#128200; Dashboard</a>

    <div class="section-label" style="color:#80cbc4;">Explore</div>
    <a href="<%= request.getContextPath() %>/internship?action=list">&#128188; Internships</a>

    <div class="section-label" style="color:#80cbc4;">My Profile</div>
    <a href="<%= request.getContextPath() %>/student/profile.jsp">&#128100; My Profile</a>
    <a href="<%= request.getContextPath() %>/logout">&#128274; Logout</a>
</div>
<div class="main">
