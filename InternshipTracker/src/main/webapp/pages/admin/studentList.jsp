<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ipts.model.Student" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Students - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#127891; Student Management</div>

    <!-- Message feedback -->
    <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>

    <!-- Search / Filter bar (Module 6) -->
    <form action="<%= request.getContextPath() %>/student" method="get" class="search-bar">
        <input type="hidden" name="action" value="search">
        <input type="text"   name="keyword"  placeholder="Search name / roll no"
               value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>">
        <select name="branch">
            <option value="">All Branches</option>
            <option value="BCA">BCA</option>
            <option value="CSE">CSE</option>
            <option value="IT">IT</option>
            <option value="ECE">ECE</option>
            <option value="Mech">Mechanical</option>
            <option value="Civil">Civil</option>
        </select>
        <select name="year">
            <option value="">All Years</option>
            <option value="2024">2024</option>
            <option value="2025">2025</option>
            <option value="2026">2026</option>
        </select>
        <button type="submit" class="btn btn-primary">Search</button>
        <a href="<%= request.getContextPath() %>/student?action=list" class="btn btn-warning">Clear</a>
        <a href="<%= request.getContextPath() %>/student?action=add"  class="btn btn-success">+ Add Student</a>
    </form>

    <!-- Student table -->
    <div class="table-box">
        <h3>All Students</h3>
        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
        %>
        <% if (students == null || students.isEmpty()) { %>
            <p style="padding:16px;color:#888;">No students found.</p>
        <% } else { %>
        <table>
            <tr>
                <th>#</th><th>Roll No</th><th>Name</th><th>Branch</th>
                <th>Batch</th><th>CGPA</th><th>Email</th>
                <th>Status</th><th>Actions</th>
            </tr>
            <% int sno = 1; for (Student s : students) { %>
            <tr>
                <td><%= sno++ %></td>
                <td><%= s.getRollNo() %></td>
                <td><%= s.getFullName() %></td>
                <td><%= s.getBranch() %></td>
                <td><%= s.getBatchYear() %></td>
                <td><%= s.getCgpa() %></td>
                <td><%= s.getEmail() %></td>
                <td>
                    <% if (s.isPlaced()) { %>
                        <span class="badge badge-placed">Placed</span>
                    <% } else { %>
                        <span class="badge badge-notplaced">Not Placed</span>
                    <% } %>
                </td>
                <td>
                    <a href="<%= request.getContextPath() %>/student?action=edit&id=<%= s.getStudentId() %>"
                       class="btn btn-warning btn-sm">Edit</a>
                    <a href="<%= request.getContextPath() %>/student?action=delete&id=<%= s.getStudentId() %>"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this student?')">Delete</a>
                </td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </div>

</div></div>
</body>
</html>
