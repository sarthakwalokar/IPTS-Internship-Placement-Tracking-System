<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Applications - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#128203; Internship Applications</div>

    <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>

    <div class="table-box">
        <h3>All Student Applications</h3>
        <%
            List<Object[]> apps = (List<Object[]>) request.getAttribute("applications");
        %>
        <% if (apps == null || apps.isEmpty()) { %>
            <p style="padding:16px;color:#888;">No applications submitted yet.</p>
        <% } else { %>
        <table>
            <tr>
                <th>#</th><th>Student</th><th>Roll No</th>
                <th>Internship</th><th>Company</th>
                <th>Applied On</th><th>Status</th><th>Remarks</th><th>Update</th>
            </tr>
            <% int sno = 1; for (Object[] row : apps) {
                // row: [appId, fullName, rollNo, title, companyName, status, appliedDate, remarks]
                int    appId     = (Integer) row[0];
                String stuName   = (String)  row[1];
                String rollNo    = (String)  row[2];
                String title     = (String)  row[3];
                String company   = (String)  row[4];
                String status    = (String)  row[5];
                String appDate   = (String)  row[6];
                String remarks   = row[7] != null ? (String) row[7] : "";
            %>
            <tr>
                <td><%= sno++ %></td>
                <td><%= stuName %></td>
                <td><%= rollNo %></td>
                <td><%= title %></td>
                <td><%= company %></td>
                <td><%= appDate %></td>
                <td>
                    <%-- Color badge based on status --%>
                    <span class="badge badge-<%= status.toLowerCase() %>"><%= status %></span>
                </td>
                <td><%= remarks %></td>
                <td>
                    <%-- Inline status update form --%>
                    <form action="<%= request.getContextPath() %>/internship" method="post"
                          style="display:flex;gap:4px;align-items:center;">
                        <input type="hidden" name="action" value="updateStatus">
                        <input type="hidden" name="appId"  value="<%= appId %>">
                        <select name="status" style="font-size:12px;padding:3px;">
                            <option value="Applied"     <%= "Applied".equals(status)     ? "selected" : "" %>>Applied</option>
                            <option value="Shortlisted" <%= "Shortlisted".equals(status) ? "selected" : "" %>>Shortlisted</option>
                            <option value="Selected"    <%= "Selected".equals(status)    ? "selected" : "" %>>Selected</option>
                            <option value="Rejected"    <%= "Rejected".equals(status)    ? "selected" : "" %>>Rejected</option>
                        </select>
                        <input type="text" name="remarks" placeholder="Remarks"
                               style="font-size:12px;padding:3px;width:90px;" value="<%= remarks %>">
                        <button type="submit" class="btn btn-primary btn-sm">Save</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </div>

</div></div>
</body>
</html>
