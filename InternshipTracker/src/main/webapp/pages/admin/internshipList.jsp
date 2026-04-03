<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ipts.model.Internship" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Internships - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#128188; Internship Listings</div>

    <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>

    <div style="margin-bottom:16px;">
        <a href="<%= request.getContextPath() %>/internship?action=add"          class="btn btn-success">+ Add Internship</a>
        <a href="<%= request.getContextPath() %>/internship?action=applications"  class="btn btn-primary" style="margin-left:8px;">View Applications</a>
    </div>

    <div class="table-box">
        <h3>All Internship Listings</h3>
        <%
            List<Internship> internships = (List<Internship>) request.getAttribute("internships");
        %>
        <% if (internships == null || internships.isEmpty()) { %>
            <p style="padding:16px;color:#888;">No internship listings yet.</p>
        <% } else { %>
        <table>
            <tr>
                <th>#</th><th>Title</th><th>Company</th><th>Location</th>
                <th>Stipend/mo</th><th>Duration</th><th>Last Apply Date</th><th>Actions</th>
            </tr>
            <% int sno = 1; for (Internship i : internships) { %>
            <tr>
                <td><%= sno++ %></td>
                <td><strong><%= i.getTitle() %></strong></td>
                <td><%= i.getCompanyName() %></td>
                <td><%= i.getLocation() %></td>
                <td><%= i.getStipend() %></td>
                <td><%= i.getDurationMonths() %> months</td>
                <td><%= i.getLastApplyDate() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/internship?action=delete&id=<%= i.getInternshipId() %>"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this internship?')">Delete</a>
                </td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </div>

</div></div>
</body>
</html>
