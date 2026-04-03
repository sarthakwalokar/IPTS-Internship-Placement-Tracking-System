<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ipts.model.Company" %>
<%
    Company c = (Company) request.getAttribute("company");
    boolean isEdit = (c != null);
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title><%= isEdit ? "Edit" : "Add" %> Company - IPTS</title></head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="page-title"><%= isEdit ? "&#9998; Edit Company" : "&#43; Add Company" %></div>

    <div class="form-box">
        <form action="<%= request.getContextPath() %>/company" method="post">
            <input type="hidden" name="action" value="<%= isEdit ? "update" : "save" %>">
            <% if (isEdit) { %>
                <input type="hidden" name="companyId" value="<%= c.getCompanyId() %>">
            <% } %>

            <div class="form-row">
                <div class="form-group">
                    <label>Company Name *</label>
                    <input type="text" name="companyName" required
                           value="<%= isEdit ? c.getCompanyName() : "" %>">
                </div>
                <div class="form-group">
                    <label>Industry</label>
                    <select name="industry">
                        <option value="">-- Select --</option>
                        <% String[] inds = {"IT","Finance","Core Engineering","Healthcare","Education","E-Commerce","Manufacturing","Other"};
                           for (String ind : inds) {
                               boolean sel = isEdit && ind.equals(c.getIndustry());
                        %>
                        <option value="<%= ind %>" <%= sel ? "selected" : "" %>><%= ind %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Contact Person</label>
                    <input type="text" name="contactPerson"
                           value="<%= isEdit && c.getContactPerson() != null ? c.getContactPerson() : "" %>">
                </div>
                <div class="form-group">
                    <label>Contact Email</label>
                    <input type="email" name="contactEmail"
                           value="<%= isEdit && c.getContactEmail() != null ? c.getContactEmail() : "" %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Contact Phone</label>
                    <input type="text" name="contactPhone"
                           value="<%= isEdit && c.getContactPhone() != null ? c.getContactPhone() : "" %>">
                </div>
                <div class="form-group">
                    <label>Website</label>
                    <input type="text" name="website" placeholder="e.g. www.company.com"
                           value="<%= isEdit && c.getWebsite() != null ? c.getWebsite() : "" %>">
                </div>
            </div>

            <div class="form-group">
                <label>Address</label>
                <textarea name="address" rows="2"
                ><%= isEdit && c.getAddress() != null ? c.getAddress() : "" %></textarea>
            </div>

            <div style="display:flex;gap:10px;margin-top:8px;">
                <button type="submit" class="btn btn-success">
                    <%= isEdit ? "Update Company" : "Add Company" %>
                </button>
                <a href="<%= request.getContextPath() %>/company?action=list" class="btn btn-warning">Cancel</a>
            </div>
        </form>
    </div>

</div></div>
</body>
</html>
