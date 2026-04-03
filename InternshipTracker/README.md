# Internship & Placement Tracking System (IPTS)
### Java Web Application | Servlet + JSP + JDBC + MySQL

---

## Tech Stack
| Layer      | Technology           |
|------------|----------------------|
| Language   | Java 11              |
| Frontend   | JSP + HTML/CSS       |
| Backend    | Java Servlets        |
| Database   | MySQL                |
| Build Tool | Maven                |
| Server     | Apache Tomcat 9+     |

---

## Modules Implemented

| Module | Description |
|--------|-------------|
| Module 1 - Admin | Login, session management |
| Module 2 - Student | Add/Edit/Delete/View students |
| Module 3 - Company | Add/Edit/Delete/View companies |
| Module 4 - Internship | Listings + Student Applications + Status Updates |
| Module 5 - Placement | Record placements, auto-mark student as placed |
| Module 6 - Search & Filter | Search students by name/roll/branch/year; companies by name/industry |
| Module 7 - Reports | Branch-wise & company-wise placement summary with print option |
| Module 8 - Admin Dashboard | Stats cards + recent placements + quick actions |
| Module 9 - Student Dashboard | View applications, placement status, browse internships |

---

## Project Structure

```
InternshipTracker/
├── pom.xml
├── sql/
│   └── schema.sql                   ← Run this first
└── src/main/
    ├── java/com/ipts/
    │   ├── model/                   ← POJOs (Student, Company, Internship, Placement)
    │   ├── dao/                     ← Database layer (CRUD operations)
    │   ├── servlet/                 ← Controllers (LoginServlet, StudentServlet, etc.)
    │   └── util/
    │       └── DBUtil.java          ← DB connection helper
    └── webapp/
        ├── index.jsp                ← Login page
        ├── css/style.css
        ├── WEB-INF/
        │   ├── web.xml
        │   ├── navbar.jsp           ← Admin sidebar include
        │   └── studentNavbar.jsp    ← Student sidebar include
        ├── admin/
        │   └── dashboard.jsp        ← Admin dashboard (Module 8)
        ├── student/
        │   └── dashboard.jsp        ← Student dashboard (Module 9)
        └── pages/
            ├── admin/               ← All admin CRUD pages
            └── student/             ← Student browse pages
```

---

## Setup & Run

### Step 1 – Database
1. Open MySQL (XAMPP / MySQL Workbench / CLI)
2. Run the schema file:
   ```sql
   source sql/schema.sql
   ```
3. This creates `ipts_db` with all tables and sample data.

### Step 2 – Configure DB Connection
Open `src/main/java/com/ipts/util/DBUtil.java` and update:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/ipts_db";
private static final String USER   = "root";
private static final String PASS   = "";   // your MySQL password
```

### Step 3 – Build
```bash
mvn clean package
```
This generates `target/ipts.war`

### Step 4 – Deploy to Tomcat
1. Copy `target/ipts.war` to `<TOMCAT_HOME>/webapps/`
2. Start Tomcat
3. Open browser: `http://localhost:8080/ipts/`

---

## Default Login Credentials

| Role    | Username | Password |
|---------|----------|----------|
| Admin   | admin    | admin123 |
| Student | john     | pass123  |
| Student | jane     | pass123  |

---

## Key Design Decisions (Keep it Simple)
- **No frameworks** – Plain Servlets + JSP, easy to understand and run
- **No password hashing** – For hackathon simplicity (add BCrypt in production)
- **Object[] for report rows** – Avoids extra model classes for one-off queries
- **Session-based auth** – `role` attribute in session controls access
- **Transactions in PlacementDAO** – addPlacement() updates `is_placed` flag atomically

---

## Possible Enhancements
- Password hashing (BCrypt)
- Export reports to Excel/PDF
- Email notifications on application status change
- File upload for resumes
- Pagination for large student lists
