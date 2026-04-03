package com.ipts.dao;

import com.ipts.model.Placement;
import com.ipts.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlacementDAO - CRUD for placement table (Module 5)
 */
public class PlacementDAO {

    /** Add placement record and mark student as placed */
    public boolean addPlacement(Placement p) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // use transaction

            // Insert placement record
            String sql = "INSERT INTO placement (company_id, student_id, job_role, package_lpa, offer_date, joining_date, placement_type, remarks) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getCompanyId());
            ps.setInt(2, p.getStudentId());
            ps.setString(3, p.getJobRole());
            ps.setDouble(4, p.getPackageLpa());
            ps.setString(5, p.getOfferDate());
            ps.setString(6, p.getJoiningDate());
            ps.setString(7, p.getPlacementType());
            ps.setString(8, p.getRemarks());
            ps.executeUpdate();

            // Also mark student as placed
            String updateSql = "UPDATE student SET is_placed=TRUE WHERE student_id=?";
            PreparedStatement upd = conn.prepareStatement(updateSql);
            upd.setInt(1, p.getStudentId());
            upd.executeUpdate();

            conn.commit(); // commit both operations together
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (SQLException ignored) {}
            return false;
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ignored) {}
            
        }
    }

    /** Get all placements with student and company names */
    public List<Placement> getAllPlacements() {
        List<Placement> list = new ArrayList<>();
        String sql = "SELECT p.*, s.full_name, s.roll_no, c.company_name " +
                     "FROM placement p " +
                     "JOIN student s ON p.student_id = s.student_id " +
                     "JOIN company c ON p.company_id = c.company_id " +
                     "ORDER BY p.offer_date DESC";
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

    /** Get placements by student (for student dashboard) */
    public List<Placement> getPlacementsByStudent(int studentId) {
        List<Placement> list = new ArrayList<>();
        String sql = "SELECT p.*, s.full_name, s.roll_no, c.company_name " +
                     "FROM placement p " +
                     "JOIN student s ON p.student_id = s.student_id " +
                     "JOIN company c ON p.company_id = c.company_id " +
                     "WHERE p.student_id=?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /** Delete placement record */
    public boolean deletePlacement(int id) {
        String sql = "DELETE FROM placement WHERE placement_id=?";
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

    /** Count total placements */
    public int countPlacements() {
        String sql = "SELECT COUNT(*) FROM placement";
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

    /** Get branch-wise placement count (for report, Module 7) */
    public List<Object[]> getBranchWiseReport() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT s.branch, COUNT(p.placement_id) as total, AVG(p.package_lpa) as avg_pkg " +
                     "FROM placement p JOIN student s ON p.student_id = s.student_id " +
                     "GROUP BY s.branch ORDER BY total DESC";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Object[] row = { rs.getString("branch"), rs.getInt("total"), rs.getDouble("avg_pkg") };
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }         return list;
    }

    /** Get company-wise placement count (for report) */
    public List<Object[]> getCompanyWiseReport() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT c.company_name, COUNT(p.placement_id) as total, AVG(p.package_lpa) as avg_pkg " +
                     "FROM placement p JOIN company c ON p.company_id = c.company_id " +
                     "GROUP BY c.company_name ORDER BY total DESC";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Object[] row = { rs.getString("company_name"), rs.getInt("total"), rs.getDouble("avg_pkg") };
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    }

    /** Helper: map row to Placement */
    private Placement mapRow(ResultSet rs) throws SQLException {
        Placement p = new Placement();
        p.setPlacementId(rs.getInt("placement_id"));
        p.setCompanyId(rs.getInt("company_id"));
        p.setStudentId(rs.getInt("student_id"));
        p.setCompanyName(rs.getString("company_name"));
        p.setStudentName(rs.getString("full_name"));
        p.setRollNo(rs.getString("roll_no"));
        p.setJobRole(rs.getString("job_role"));
        p.setPackageLpa(rs.getDouble("package_lpa"));
        p.setOfferDate(rs.getString("offer_date"));
        p.setJoiningDate(rs.getString("joining_date"));
        p.setPlacementType(rs.getString("placement_type"));
        p.setRemarks(rs.getString("remarks"));
        return p;
    }
}
