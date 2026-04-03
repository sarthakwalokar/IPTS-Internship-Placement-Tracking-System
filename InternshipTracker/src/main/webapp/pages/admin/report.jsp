<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Reports - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#128202; Placement Reports</div>

    <!-- ---- Summary Stats ---- -->
    <div class="stats-grid">
        <div class="stat-card">
            <div class="number"><%= request.getAttribute("totalStudents") %></div>
            <div class="label">Total Students</div>
        </div>
        <div class="stat-card green">
            <div class="number"><%= request.getAttribute("placedStudents") %></div>
            <div class="label">Placed</div>
        </div>
        <div class="stat-card orange">
            <div class="number"><%= request.getAttribute("placementPct") %>%</div>
            <div class="label">Placement Rate</div>
        </div>
        <div class="stat-card teal">
            <div class="number"><%= request.getAttribute("totalPlacements") %></div>
            <div class="label">Total Records</div>
        </div>
    </div>

    <!-- ---- Branch-wise Report ---- -->
    <div class="table-box report-section">
        <h3>&#127979; Branch-wise Placement Summary</h3>
        <%
            List<Object[]> branchReport = (List<Object[]>) request.getAttribute("branchReport");
        %>
        <% if (branchReport == null || branchReport.isEmpty()) { %>
            <p style="padding:16px;color:#888;">No data available.</p>
        <% } else { %>
        <table>
            <tr>
                <th>Branch</th>
                <th>Total Placed</th>
                <th>Avg Package (LPA)</th>
            </tr>
            <% for (Object[] row : branchReport) { %>
            <tr>
                <td><strong><%= row[0] %></strong></td>
                <td><%= row[1] %></td>
                <td>&#8377;<%= String.format("%.2f", row[2]) %></td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </div>

    <!-- ---- Company-wise Report ---- -->
    <div class="table-box report-section">
        <h3>&#127970; Company-wise Placement Summary</h3>
        <%
            List<Object[]> companyReport = (List<Object[]>) request.getAttribute("companyReport");
        %>
        <% if (companyReport == null || companyReport.isEmpty()) { %>
            <p style="padding:16px;color:#888;">No data available.</p>
        <% } else { %>
        <table>
            <tr>
                <th>Company</th>
                <th>Students Placed</th>
                <th>Avg Package (LPA)</th>
            </tr>
            <% for (Object[] row : companyReport) { %>
            <tr>
                <td><strong><%= row[0] %></strong></td>
                <td><%= row[1] %></td>
                <td>&#8377;<%= String.format("%.2f", row[2]) %></td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </div>

    <!-- Print button -->
    <div style="margin-top:8px;">
        <button onclick="window.print()" class="btn btn-primary">&#128424; Print Report</button>
    </div>

</div></div>
</body>
</html>
