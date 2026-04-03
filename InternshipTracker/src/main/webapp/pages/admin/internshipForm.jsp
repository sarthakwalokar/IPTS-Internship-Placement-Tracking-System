<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ipts.model.Company" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Add Internship - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#43; Add Internship Listing</div>

    <div class="form-box">
        <form action="<%= request.getContextPath() %>/internship" method="post">
            <input type="hidden" name="action" value="save">

            <div class="form-row">
                <div class="form-group">
                    <label>Internship Title *</label>
                    <input type="text" name="title" placeholder="e.g. Web Development Intern" required>
                </div>
                <div class="form-group">
                    <label>Company *</label>
                    <select name="companyId" required>
                        <option value="">-- Select Company --</option>
                        <%
                            List<Company> companies = (List<Company>) request.getAttribute("companies");
                            if (companies != null) {
                                for (Company c : companies) {
                        %>
                        <option value="<%= c.getCompanyId() %>"><%= c.getCompanyName() %></option>
                        <%      }
                            }
                        %>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label>Description</label>
                <textarea name="description" rows="3" placeholder="About the internship role..."></textarea>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Stipend (per month &#8377;)</label>
                    <input type="number" name="stipend" min="0" step="100" placeholder="e.g. 10000">
                </div>
                <div class="form-group">
                    <label>Duration (months)</label>
                    <input type="number" name="durationMonths" min="1" max="24" placeholder="e.g. 3">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Start Date</label>
                    <input type="date" name="startDate">
                </div>
                <div class="form-group">
                    <label>End Date</label>
                    <input type="date" name="endDate">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Location</label>
                    <input type="text" name="location" placeholder="e.g. Mumbai / Remote">
                </div>
                <div class="form-group">
                    <label>Last Date to Apply *</label>
                    <input type="date" name="lastApplyDate" required>
                </div>
            </div>

            <div style="display:flex;gap:10px;margin-top:8px;">
                <button type="submit" class="btn btn-success">Add Internship</button>
                <a href="<%= request.getContextPath() %>/internship?action=list" class="btn btn-warning">Cancel</a>
            </div>
        </form>
    </div>

</div></div>
</body>
</html>
