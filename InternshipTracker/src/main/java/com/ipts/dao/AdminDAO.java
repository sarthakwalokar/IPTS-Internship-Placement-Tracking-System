package com.ipts.dao;

import com.ipts.model.Admin;
import com.ipts.util.DBUtil;

import java.sql.*;

/**
 * AdminDAO - handles admin authentication (Module 1)
 */
public class AdminDAO {

    /**
     * Validate admin credentials.
     * Returns Admin object if found, null otherwise.
     */
    public Admin login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username=? AND password=?";
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin a = new Admin();
                a.setAdminId(rs.getInt("admin_id"));
                a.setUsername(rs.getString("username"));
                a.setFullName(rs.getString("full_name"));
                a.setEmail(rs.getString("email"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    /**
     * Change admin password.
     * Useful for settings page (optional enhancement).
     */
    public boolean changePassword(int adminId, String oldPass, String newPass) {
        // First verify old password
        String checkSql = "SELECT admin_id FROM admin WHERE admin_id=? AND password=?";
        String updateSql = "UPDATE admin SET password=? WHERE admin_id=?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();

            // Verify old password matches
            PreparedStatement check = conn.prepareStatement(checkSql);
            check.setInt(1, adminId);
            check.setString(2, oldPass);
            if (!check.executeQuery().next()) {
                return false; // old password wrong
            }

            // Update to new password
            PreparedStatement upd = conn.prepareStatement(updateSql);
            upd.setString(1, newPass);
            upd.setInt(2, adminId);
            return upd.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
}
