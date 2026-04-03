<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ipts.model.Student" %>
<%
    // If student is set in request, we are editing; otherwise adding
    Student s = (Student) request.getAttribute("student");
    boolean isEdit = (s != null);
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title><%= isEdit ? "Edit" : "Add" %> Student - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title"><%= isEdit ? "&#9998; Edit Student" : "&#43; Add Student" %></div>

    <div class="form-box">
        <!-- action=save for new, action=update for edit -->
        <form action="<%= request.getContextPath() %>/student" method="post">
            <input type="hidden" name="action" value="<%= isEdit ? "update" : "save" %>">
            <% if (isEdit) { %>
                <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
            <% } %>

            <div class="form-row">
                <div class="form-group">
                    <label>Full Name *</label>
                    <input type="text" name="fullName" required
                           value="<%= isEdit ? s.getFullName() : "" %>">
                </div>
                <div class="form-group">
                    <label>Roll Number *</label>
                    <input type="text" name="rollNo" required
                           value="<%= isEdit ? s.getRollNo() : "" %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Username *</label>
                    <input type="text" name="username" required
                           value="<%= isEdit ? s.getUsername() : "" %>"
                           <%= isEdit ? "readonly" : "" %>>
                </div>
                <div class="form-group">
                    <label>Password *</label>
                    <input type="password" name="password" required
                           value="<%= isEdit ? s.getPassword() : "" %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email"
                           value="<%= isEdit ? (s.getEmail() != null ? s.getEmail() : "") : "" %>">
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone"
                           value="<%= isEdit ? (s.getPhone() != null ? s.getPhone() : "") : "" %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Branch *</label>
                    <select name="branch" required>
                        <option value="">-- Select Branch --</option>
                        <%
                            String[] branches = {"BCA","CSE","IT","ECE","EEE","Mech","Civil"};
                            for (String b : branches) {
                                boolean selected = isEdit && b.equals(s.getBranch());
                        %>
                        <option value="<%= b %>" <%= selected ? "selected" : "" %>><%= b %></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Batch Year *</label>
                    <input type="number" name="batchYear" min="2020" max="2030" required
                           value="<%= isEdit ? s.getBatchYear() : "" %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>CGPA</label>
                    <input type="number" name="cgpa" min="0" max="10" step="0.01"
                           value="<%= isEdit ? s.getCgpa() : "" %>">
                </div>
                <div class="form-group">
                    <label>Resume Link</label>
                    <input type="text" name="resumeLink" placeholder="URL or file path"
                           value="<%= isEdit && s.getResumeLink() != null ? s.getResumeLink() : "" %>">
                </div>
            </div>

            <div style="display:flex;gap:10px;margin-top:8px;">
                <button type="submit" class="btn btn-success">
                    <%= isEdit ? "Update Student" : "Add Student" %>
                </button>
                <a href="<%= request.getContextPath() %>/student?action=list" class="btn btn-warning">Cancel</a>
            </div>
        </form>
    </div>

</div></div>
</body>
</html>
