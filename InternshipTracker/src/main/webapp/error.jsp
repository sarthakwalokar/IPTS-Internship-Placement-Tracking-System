<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - IPTS</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="login-page">
    <div class="login-box" style="text-align:center;">
        <div style="font-size:60px;">&#9888;</div>
        <h2 style="color:#c62828;">Oops!</h2>
        <p style="color:#555;margin:12px 0;">
            <%
                Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
                String  errMsg     = (String)  request.getAttribute("javax.servlet.error.message");
                if (statusCode != null && statusCode == 404) {
            %>
                Page not found (404).
            <% } else if (statusCode != null) { %>
                Error <%= statusCode %>: <%= errMsg != null ? errMsg : "Something went wrong." %>
            <% } else { %>
                An unexpected error occurred.
            <% } %>
        </p>
        <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-primary">
            Go to Login
        </a>
    </div>
</div>
</body>
</html>
