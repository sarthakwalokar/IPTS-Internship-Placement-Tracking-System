package com.ipts.dao;

import com.ipts.model.Company;
import com.ipts.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CompanyDAO - CRUD for company table (Module 3)
 */
public class CompanyDAO {

    /** Add new company */
    public boolean addCompany(Company c) {
        String sql = "INSERT INTO company (company_name, industry, website, contact_person, contact_email, contact_phone, address) VALUES (?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCompanyName());
            ps.setString(2, c.getIndustry());
            ps.setString(3, c.getWebsite());
            ps.setString(4, c.getContactPerson());
            ps.setString(5, c.getContactEmail());
            ps.setString(6, c.getContactPhone());
            ps.setString(7, c.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    /** Get all companies */
    public List<Company> getAllCompanies() {
        List<Company> list = new ArrayList<>();
        String sql = "SELECT * FROM company ORDER BY company_name";
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

    /** Get company by ID */
    public Company getCompanyById(int id) {
        String sql = "SELECT * FROM company WHERE company_id=?";
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

    /** Update company */
    public boolean updateCompany(Company c) {
        String sql = "UPDATE company SET company_name=?, industry=?, website=?, contact_person=?, contact_email=?, contact_phone=?, address=? WHERE company_id=?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCompanyName());
            ps.setString(2, c.getIndustry());
            ps.setString(3, c.getWebsite());
            ps.setString(4, c.getContactPerson());
            ps.setString(5, c.getContactEmail());
            ps.setString(6, c.getContactPhone());
            ps.setString(7, c.getAddress());
            ps.setInt(8, c.getCompanyId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    /** Delete company */
    public boolean deleteCompany(int id) {
        String sql = "DELETE FROM company WHERE company_id=?";
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

    /** Search companies by name or industry (Module 6) */
    public List<Company> searchCompanies(String keyword) {
        List<Company> list = new ArrayList<>();
        String sql = "SELECT * FROM company WHERE company_name LIKE ? OR industry LIKE ? ORDER BY company_name";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    }

    /** Count companies (for dashboard) */
    public int countCompanies() {
        String sql = "SELECT COUNT(*) FROM company";
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

    /** Helper: map row to Company */
    private Company mapRow(ResultSet rs) throws SQLException {
        Company c = new Company();
        c.setCompanyId(rs.getInt("company_id"));
        c.setCompanyName(rs.getString("company_name"));
        c.setIndustry(rs.getString("industry"));
        c.setWebsite(rs.getString("website"));
        c.setContactPerson(rs.getString("contact_person"));
        c.setContactEmail(rs.getString("contact_email"));
        c.setContactPhone(rs.getString("contact_phone"));
        c.setAddress(rs.getString("address"));
        return c;
    }
}
