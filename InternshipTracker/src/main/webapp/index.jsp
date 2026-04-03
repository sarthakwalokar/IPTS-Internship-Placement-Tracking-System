<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>IPTS - Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-page">
    <div class="login-box">
        <h2>&#127979; IPTS</h2>
        <p>Internship & Placement Tracking System</p>

        <!-- Show error if login failed -->
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>

        <!-- Role switch tabs -->
        <div class="role-tabs">
            <button id="tabAdmin" class="active" onclick="switchRole('admin')">Admin</button>
            <button id="tabStudent" onclick="switchRole('student')">Student</button>
        </div>

        <!-- Login form - action goes to LoginServlet -->
        <form action="login" method="post">
            <input type="hidden" name="role" id="roleField" value="admin">

            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" placeholder="Enter username" required>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Enter password" required>
            </div>
            <button type="submit" class="btn btn-primary" style="width:100%;padding:10px;">Login</button>
        </form>

        
    </div>
</div>

<script src="js/app.js"></script>
</body>
</html>
