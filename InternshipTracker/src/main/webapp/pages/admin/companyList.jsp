<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ipts.model.Company" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Companies - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#127970; Company Management</div>

    <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>

    <!-- Search bar (Module 6) -->
    <form action="<%= request.getContextPath() %>/company" method="get" class="search-bar">
        <input type="hidden" name="action" value="search">
        <input type="text" name="keyword" placeholder="Search company name / industry"
               value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>">
        <button type="submit" class="btn btn-primary">Search</button>
        <a href="<%= request.getContextPath() %>/company?action=list" class="btn btn-warning">Clear</a>
        <a href="<%= request.getContextPath() %>/company?action=add"  class="btn btn-success">+ Add Company</a>
    </form>

    <div class="table-box">
        <h3>All Companies</h3>
        <%
            List<Company> companies = (List<Company>) request.getAttribute("companies");
        %>
        <% if (companies == null || companies.isEmpty()) { %>
            <p style="padding:16px;color:#888;">No companies found.</p>
        <% } else { %>
        <table>
            <tr>
                <th>#</th><th>Company Name</th><th>Industry</th>
                <th>Contact Person</th><th>Email</th><th>Phone</th>
                <th>Website</th><th>Actions</th>
            </tr>
            <% int sno = 1; for (Company c : companies) { %>
            <tr>
                <td><%= sno++ %></td>
                <td><strong><%= c.getCompanyName() %></strong></td>
                <td><%= c.getIndustry() %></td>
                <td><%= c.getContactPerson() %></td>
                <td><%= c.getContactEmail() %></td>
                <td><%= c.getContactPhone() %></td>
                <td>
                    <% if (c.getWebsite() != null && !c.getWebsite().isEmpty()) { %>
                        <a href="http://<%= c.getWebsite() %>" target="_blank">&#127758;</a>
                    <% } %>
                </td>
                <td>
                    <a href="<%= request.getContextPath() %>/company?action=edit&id=<%= c.getCompanyId() %>"
                       class="btn btn-warning btn-sm">Edit</a>
                    <a href="<%= request.getContextPath() %>/company?action=delete&id=<%= c.getCompanyId() %>"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this company?')">Delete</a>
                </td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </div>

</div></div>
</body>
</html>
