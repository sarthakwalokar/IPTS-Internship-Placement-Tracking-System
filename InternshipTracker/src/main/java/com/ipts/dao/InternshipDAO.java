package com.ipts.dao;

import com.ipts.model.Internship;
import com.ipts.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * InternshipDAO - CRUD + application tracking (Module 4)
 */
public class InternshipDAO {

    /** Add new internship listing */
    public boolean addInternship(Internship i) {
        String sql = "INSERT INTO internship (company_id, title, description, stipend, duration_months, start_date, end_date, location, last_apply_date) VALUES (?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i.getCompanyId());
            ps.setString(2, i.getTitle());
            ps.setString(3, i.getDescription());
            ps.setDouble(4, i.getStipend());
            ps.setInt(5, i.getDurationMonths());
            ps.setString(6, i.getStartDate());
            ps.setString(7, i.getEndDate());
            ps.setString(8, i.getLocation());
            ps.setString(9, i.getLastApplyDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    /** Get all internships with company name (JOIN) */
    public List<Internship> getAllInternships() {
        List<Internship> list = new ArrayList<>();
        // JOIN to get company name alongside internship details
        String sql = "SELECT i.*, c.company_name FROM internship i JOIN company c ON i.company_id = c.company_id ORDER BY i.last_apply_date DESC";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    }

    /** Get internship by ID */
    public Internship getInternshipById(int id) {
        String sql = "SELECT i.*, c.company_name FROM internship i JOIN company c ON i.company_id = c.company_id WHERE i.internship_id=?";
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

    /** Delete internship */
    public boolean deleteInternship(int id) {
        String sql = "DELETE FROM internship WHERE internship_id=?";
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

    /** Student applies for internship */
    public boolean applyForInternship(int internshipId, int studentId) {
        // Check if already applied
        String checkSql = "SELECT app_id FROM internship_application WHERE internship_id=? AND student_id=?";
        String insertSql = "INSERT INTO internship_application (internship_id, student_id, status) VALUES (?,?,'Applied')";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement check = conn.prepareStatement(checkSql);
            check.setInt(1, internshipId);
            check.setInt(2, studentId);
            if (check.executeQuery().next()) return false; // already applied

            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.setInt(1, internshipId);
            ps.setInt(2, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    /** Update application status (admin action) */
    public boolean updateApplicationStatus(int appId, String status, String remarks) {
        String sql = "UPDATE internship_application SET status=?, remarks=? WHERE app_id=?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, remarks);
            ps.setInt(3, appId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    /** Get all applications for admin view */
    public List<Object[]> getAllApplications() {
        List<Object[]> list = new ArrayList<>();
        // Fetch app details with student name and internship title
        String sql = "SELECT ia.app_id, s.full_name, s.roll_no, i.title, c.company_name, ia.status, ia.applied_date, ia.remarks " +
                     "FROM internship_application ia " +
                     "JOIN student s ON ia.student_id = s.student_id " +
                     "JOIN internship i ON ia.internship_id = i.internship_id " +
                     "JOIN company c ON i.company_id = c.company_id " +
                     "ORDER BY ia.applied_date DESC";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                // Pack each row as Object array for JSP display
                Object[] row = {
                    rs.getInt("app_id"),
                    rs.getString("full_name"),
                    rs.getString("roll_no"),
                    rs.getString("title"),
                    rs.getString("company_name"),
                    rs.getString("status"),
                    rs.getString("applied_date"),
                    rs.getString("remarks")
                };
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    }

    /** Get applications for a specific student (student dashboard) */
    public List<Object[]> getApplicationsByStudent(int studentId) {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT ia.app_id, i.title, c.company_name, ia.status, ia.applied_date, ia.remarks " +
                     "FROM internship_application ia " +
                     "JOIN internship i ON ia.internship_id = i.internship_id " +
                     "JOIN company c ON i.company_id = c.company_id " +
                     "WHERE ia.student_id=? ORDER BY ia.applied_date DESC";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("app_id"),
                    rs.getString("title"),
                    rs.getString("company_name"),
                    rs.getString("status"),
                    rs.getString("applied_date"),
                    rs.getString("remarks")
                };
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    }

    /** Count total internship listings */
    public int countInternships() {
        String sql = "SELECT COUNT(*) FROM internship";
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

    /** Helper: map row to Internship */
    private Internship mapRow(ResultSet rs) throws SQLException {
        Internship i = new Internship();
        i.setInternshipId(rs.getInt("internship_id"));
        i.setCompanyId(rs.getInt("company_id"));
        i.setCompanyName(rs.getString("company_name"));
        i.setTitle(rs.getString("title"));
        i.setDescription(rs.getString("description"));
        i.setStipend(rs.getDouble("stipend"));
        i.setDurationMonths(rs.getInt("duration_months"));
        i.setStartDate(rs.getString("start_date"));
        i.setEndDate(rs.getString("end_date"));
        i.setLocation(rs.getString("location"));
        i.setLastApplyDate(rs.getString("last_apply_date"));
        return i;
    }
}
