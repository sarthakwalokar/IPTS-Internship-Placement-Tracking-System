package com.ipts.dao;

import com.ipts.model.Student;
import com.ipts.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAO - handles all CRUD for student table (Module 2)
 */
public class StudentDAO {

    /** Add new student */
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO student (username, password, roll_no, full_name, email, phone, branch, batch_year, cgpa, resume_link) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getRollNo());
            ps.setString(4, s.getFullName());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getPhone());
            ps.setString(7, s.getBranch());
            ps.setInt(8, s.getBatchYear());
            ps.setDouble(9, s.getCgpa());
            ps.setString(10, s.getResumeLink());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    /** Get all students */
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student ORDER BY full_name";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(mapRow(rs)); // reuse helper
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    }

    /** Get student by ID */
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    /** Login check - returns Student object if credentials match */
    public Student login(String username, String password) {
        String sql = "SELECT * FROM student WHERE username=? AND password=?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    /** Update student info */
    public boolean updateStudent(Student s) {
        String sql = "UPDATE student SET full_name=?, email=?, phone=?, branch=?, batch_year=?, cgpa=?, resume_link=? WHERE student_id=?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getFullName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhone());
            ps.setString(4, s.getBranch());
            ps.setInt(5, s.getBatchYear());
            ps.setDouble(6, s.getCgpa());
            ps.setString(7, s.getResumeLink());
            ps.setInt(8, s.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    /** Delete student */
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE student_id=?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    /** Search students by name, roll_no, or branch (Module 6) */
    public List<Student> searchStudents(String keyword, String branch, String batchYear) {
        List<Student> list = new ArrayList<>();
        // Build dynamic query based on filters
        StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE 1=1");
        if (keyword != null && !keyword.trim().isEmpty())
            sql.append(" AND (full_name LIKE ? OR roll_no LIKE ?)");
        if (branch != null && !branch.trim().isEmpty())
            sql.append(" AND branch = ?");
        if (batchYear != null && !batchYear.trim().isEmpty())
            sql.append(" AND batch_year = ?");
        sql.append(" ORDER BY full_name");

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            int idx = 1;
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(idx++, "%" + keyword + "%");
                ps.setString(idx++, "%" + keyword + "%");
            }
            if (branch != null && !branch.trim().isEmpty())
                ps.setString(idx++, branch);
            if (batchYear != null && !batchYear.trim().isEmpty())
                ps.setInt(idx++, Integer.parseInt(batchYear));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    }

    /** Count total students (for dashboard) */
    public int countStudents() {
        String sql = "SELECT COUNT(*) FROM student";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }

    /** Count placed students (for dashboard) */
    public int countPlacedStudents() {
        String sql = "SELECT COUNT(*) FROM student WHERE is_placed = TRUE";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }

    /** Helper: map ResultSet row to Student object */
    private Student mapRow(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setStudentId(rs.getInt("student_id"));
        s.setUsername(rs.getString("username"));
        s.setPassword(rs.getString("password"));
        s.setRollNo(rs.getString("roll_no"));
        s.setFullName(rs.getString("full_name"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        s.setBranch(rs.getString("branch"));
        s.setBatchYear(rs.getInt("batch_year"));
        s.setCgpa(rs.getDouble("cgpa"));
        s.setResumeLink(rs.getString("resume_link"));
        s.setPlaced(rs.getBoolean("is_placed"));
        return s;
    }
}
