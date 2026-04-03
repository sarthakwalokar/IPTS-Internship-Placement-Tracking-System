package com.ipts.model;

/**
 * Student - POJO for student table (Module 2)
 */
public class Student {
    private int studentId;
    private String username;
    private String password;
    private String rollNo;
    private String fullName;
    private String email;
    private String phone;
    private String branch;
    private int batchYear;
    private double cgpa;
    private String resumeLink;
    private boolean isPlaced;

    // ---------- Getters & Setters ----------

    public int getStudentId()            { return studentId; }
    public void setStudentId(int id)     { this.studentId = id; }

    public String getUsername()          { return username; }
    public void setUsername(String u)    { this.username = u; }

    public String getPassword()          { return password; }
    public void setPassword(String p)    { this.password = p; }

    public String getRollNo()            { return rollNo; }
    public void setRollNo(String r)      { this.rollNo = r; }

    public String getFullName()          { return fullName; }
    public void setFullName(String n)    { this.fullName = n; }

    public String getEmail()             { return email; }
    public void setEmail(String e)       { this.email = e; }

    public String getPhone()             { return phone; }
    public void setPhone(String p)       { this.phone = p; }

    public String getBranch()            { return branch; }
    public void setBranch(String b)      { this.branch = b; }

    public int getBatchYear()            { return batchYear; }
    public void setBatchYear(int y)      { this.batchYear = y; }

    public double getCgpa()              { return cgpa; }
    public void setCgpa(double c)        { this.cgpa = c; }

    public String getResumeLink()        { return resumeLink; }
    public void setResumeLink(String r)  { this.resumeLink = r; }

    public boolean isPlaced()            { return isPlaced; }
    public void setPlaced(boolean p)     { this.isPlaced = p; }
}
