<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ipts.model.Placement" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Placements - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#127942; Placement Records</div>

    <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>

    <div style="margin-bottom:16px;">
        <a href="<%= request.getContextPath() %>/placement?action=add" class="btn btn-success">+ Record Placement</a>
    </div>

    <div class="table-box">
        <h3>All Placements</h3>
        <%
            List<Placement> placements = (List<Placement>) request.getAttribute("placements");
        %>
        <% if (placements == null || placements.isEmpty()) { %>
            <p style="padding:16px;color:#888;">No placement records yet.</p>
        <% } else { %>
        <table>
            <tr>
                <th>#</th><th>Student</th><th>Roll No</th><th>Company</th>
                <th>Job Role</th><th>Package (LPA)</th><th>Type</th>
                <th>Offer Date</th><th>Joining Date</th><th>Actions</th>
            </tr>
            <% int sno = 1; for (Placement p : placements) { %>
            <tr>
                <td><%= sno++ %></td>
                <td><%= p.getStudentName() %></td>
                <td><%= p.getRollNo() %></td>
                <td><%= p.getCompanyName() %></td>
                <td><%= p.getJobRole() %></td>
                <td><strong>&#8377;<%= p.getPackageLpa() %> LPA</strong></td>
                <td><span class="badge badge-placed"><%= p.getPlacementType() %></span></td>
                <td><%= p.getOfferDate() %></td>
                <td><%= p.getJoiningDate() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/placement?action=delete&id=<%= p.getPlacementId() %>"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Delete this record?')">Delete</a>
                </td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </div>

</div></div>
</body>
</html>
