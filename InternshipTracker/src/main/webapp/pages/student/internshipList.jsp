<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ipts.model.Internship" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Browse Internships - IPTS</title>
</head>
<body>
<%@ include file="/WEB-INF/studentNavbar.jsp" %>

<div class="page-title">&#128188; Available Internships</div>

<!-- Feedback message after applying -->
<% if (request.getAttribute("message") != null) { %>
    <div class="alert alert-info"><%= request.getAttribute("message") %></div>
<% } %>

<%
    List<Internship> internships = (List<Internship>) request.getAttribute("internships");
%>
<% if (internships == null || internships.isEmpty()) { %>
    <div class="alert alert-info">No internship listings available at the moment.</div>
<% } else { %>

    <% for (Internship i : internships) { %>
    <div class="table-box" style="padding:18px;margin-bottom:14px;">
        <div style="display:flex;justify-content:space-between;align-items:flex-start;flex-wrap:wrap;">
            <div>
                <div style="font-size:17px;font-weight:bold;color:#00695c;">
                    <%= (i != null && i.getTitle() != null) ? i.getTitle() : "Untitled" %>
                </div>
                <div style="font-size:14px;color:#555;margin-top:4px;">
                    🏢 <%= (i != null && i.getCompanyName() != null) ? i.getCompanyName() : "Unknown" %>
                    &nbsp;|&nbsp; 📍 <%= (i != null && i.getLocation() != null) ? i.getLocation() : "Unknown" %>
                    &nbsp;|&nbsp; ₹<%= (i != null && i.getStipend() != null) ? i.getStipend() : "N/A" %>/month
                    &nbsp;|&nbsp; 📅 <%=  i.getDurationMonths()  %> months
                </div>
                <% if (i.getDescription() != null && !i.getDescription().isEmpty()) { %>
                    <div style="font-size:13px;color:#777;margin-top:6px;">
                        <%= i.getDescription() %>
                    </div>
                <% } %>
                <div style="font-size:12px;color:#999;margin-top:6px;">
                    Last date to apply: <strong>
                        <%= (i != null && i.getLastApplyDate() != null) ? i.getLastApplyDate() : "N/A" %>
                    </strong>
                </div>
            </div>
            <div style="margin-top:8px;">
                <a href="<%= request.getContextPath() %>/internship?action=apply&id=<%= i.getInternshipId() %>"
                   class="btn btn-success"
                   onclick="return confirm('Apply for: <%= (i != null && i.getTitle() != null) ? i.getTitle() : "Untitled" %> at <%= (i != null && i.getCompanyName() != null) ? i.getCompanyName() : "Unknown" %>?')">
                   Apply Now
                </a>
            </div>
        </div>
    </div>
    <% } %>
<% } %>

</body>
</html>