<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ipts.model.Student, com.ipts.dao.StudentDAO" %>

<%
    // Guard - only logged-in students
    Student stu = (Student) session.getAttribute("student");
    if (stu == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    String message = null;

    // Handle profile update POST
    if ("POST".equals(request.getMethod())) {
        StudentDAO dao = new StudentDAO();

        stu.setEmail(request.getParameter("email"));
        stu.setPhone(request.getParameter("phone"));
        stu.setResumeLink(request.getParameter("resumeLink"));

        try {
            stu.setCgpa(Double.parseDouble(request.getParameter("cgpa")));
        } catch (Exception e) { }

        boolean ok = dao.updateStudent(stu);

        if (ok) {
            session.setAttribute("student", stu);
            message = "Profile updated successfully!";
        } else {
            message = "Error updating profile.";
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile - IPTS</title>
</head>
<style>/* ===== style.css ===== */

/* Global reset & font */
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f5f6fa;
    margin: 0;
    padding: 0;
    color: #333;
}

.page-title {
    font-size: 28px;
    font-weight: 600;
    margin: 20px auto;
    text-align: center;
    color: #2f3640;
}

/* Form container */
form {
    max-width: 600px;
    margin: 30px auto;
    padding: 25px;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

/* Headings inside form */
form h3 {
    margin-top: 0;
    color: #40739e;
    border-bottom: 2px solid #40739e;
    padding-bottom: 5px;
    margin-bottom: 20px;
}

/* Input fields */
form input[type="text"],
form input[type="email"],
form input[type="number"] {
    width: 100%;
    padding: 10px 12px;
    margin-bottom: 15px;
    border: 1px solid #dcdde1;
    border-radius: 6px;
    font-size: 14px;
    transition: border-color 0.3s;
}

form input[type="text"]:focus,
form input[type="email"]:focus,
form input[type="number"]:focus {
    border-color: #40739e;
    outline: none;
}

/* Status text */
form p {
    font-size: 16px;
    margin-bottom: 20px;
    font-weight: 500;
}

/* Submit button */
form button {
    background-color: #40739e;
    color: white;
    padding: 12px 25px;
    border: none;
    border-radius: 6px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
}

form button:hover {
    background-color: #273c75;
}

/* Message styling */
div.message {
    max-width: 600px;
    margin: 20px auto;
    padding: 12px 15px;
    border-radius: 6px;
    font-weight: 500;
    text-align: center;
}

div.message.success {
    background-color: #dff9fb;
    color: #22a6b3;
}

div.message.error {
    background-color: #ffcccc;
    color: #e84118;
}</style>
<body>

<!-- ✅ FIXED INCLUDE -->
<jsp:include page="/WEB-INF/studentNavbar.jsp" />

<div class="page-title">&#128100; My Profile</div>

<% if (message != null) { %>
    <div><%= message %></div>
<% } %>

<form action="<%= request.getContextPath() %>/student/profile.jsp" method="post">

    <h3>Basic Info</h3>

    Name: <input type="text" value="<%= stu.getFullName() %>" readonly><br><br>
    Roll No: <input type="text" value="<%= stu.getRollNo() %>" readonly><br><br>
    Branch: <input type="text" value="<%= stu.getBranch() %>" readonly><br><br>
    Batch: <input type="text" value="<%= stu.getBatchYear() %>" readonly><br><br>

    <h3>Update Info</h3>

    Email:
    <input type="email" name="email"
           value="<%= stu.getEmail() != null ? stu.getEmail() : "" %>"><br><br>

    Phone:
    <input type="text" name="phone"
           value="<%= stu.getPhone() != null ? stu.getPhone() : "" %>"><br><br>

    CGPA:
    <input type="number" name="cgpa" step="0.01"
           value="<%= stu.getCgpa() %>"><br><br>

    Resume Link:
    <input type="text" name="resumeLink"
           value="<%= stu.getResumeLink() != null ? stu.getResumeLink() : "" %>"><br><br>

    <p>
        Status:
        <%= stu.isPlaced() ? "Placed" : "Not Placed" %>
    </p>

    <button type="submit">Update Profile</button>

</form>

<!-- ✅ CLOSE layout if navbar opened it -->
</div></div>

</body>
</html>