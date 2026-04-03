package com.ipts.model;

/**
 * Admin - POJO for admin table (Module 1)
 */
public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String fullName;
    private String email;

    // ---------- Getters & Setters ----------

    public int getAdminId()              { return adminId; }
    public void setAdminId(int id)       { this.adminId = id; }

    public String getUsername()          { return username; }
    public void setUsername(String u)    { this.username = u; }

    public String getPassword()          { return password; }
    public void setPassword(String p)    { this.password = p; }

    public String getFullName()          { return fullName; }
    public void setFullName(String n)    { this.fullName = n; }

    public String getEmail()             { return email; }
    public void setEmail(String e)       { this.email = e; }
}
