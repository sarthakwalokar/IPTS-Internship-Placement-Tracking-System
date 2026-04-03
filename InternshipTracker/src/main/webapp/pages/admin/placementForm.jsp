<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ipts.model.Company, com.ipts.model.Student" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Record Placement - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title">&#43; Record New Placement</div>

    <div class="form-box">
        <form action="<%= request.getContextPath() %>/placement" method="post">

            <div class="form-row">
                <div class="form-group">
                    <label>Student *</label>
                    <select name="studentId" required>
                        <option value="">-- Select Student --</option>
                        <%
                            List<Student> students = (List<Student>) request.getAttribute("students");
                            if (students != null) for (Student s : students) {
                        %>
                        <option value="<%= s.getStudentId() %>">
                            <%= s.getFullName() %> (<%= s.getRollNo() %>) - <%= s.getBranch() %>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Company *</label>
                    <select name="companyId" required>
                        <option value="">-- Select Company --</option>
                        <%
                            List<Company> companies = (List<Company>) request.getAttribute("companies");
                            if (companies != null) for (Company c : companies) {
                        %>
                        <option value="<%= c.getCompanyId() %>"><%= c.getCompanyName() %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Job Role *</label>
                    <input type="text" name="jobRole" placeholder="e.g. Software Engineer" required>
                </div>
                <div class="form-group">
                    <label>Package (LPA) *</label>
                    <input type="number" name="packageLpa" min="0" step="0.5" placeholder="e.g. 6.5" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Offer Date</label>
                    <input type="date" name="offerDate">
                </div>
                <div class="form-group">
                    <label>Joining Date</label>
                    <input type="date" name="joiningDate">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Placement Type</label>
                    <select name="placementType">
                        <option value="On-Campus">On-Campus</option>
                        <option value="Off-Campus">Off-Campus</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Remarks</label>
                    <input type="text" name="remarks" placeholder="Optional notes">
                </div>
            </div>

            <div style="display:flex;gap:10px;margin-top:8px;">
                <button type="submit" class="btn btn-success">Save Placement</button>
                <a href="<%= request.getContextPath() %>/placement?action=list" class="btn btn-warning">Cancel</a>
            </div>
        </form>
    </div>

</div></div>
</body>
</html>
