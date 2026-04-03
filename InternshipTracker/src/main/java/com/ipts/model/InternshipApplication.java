package com.ipts.model;

/**
 * InternshipApplication - POJO for internship_application table
 * Used when we need a typed object instead of Object[]
 */
public class InternshipApplication {
    private int appId;
    private int internshipId;
    private int studentId;
    private String studentName;
    private String rollNo;
    private String internshipTitle;
    private String companyName;
    private String status;       // Applied | Shortlisted | Selected | Rejected
    private String appliedDate;
    private String remarks;

    // ---------- Getters & Setters ----------

    public int getAppId()                    { return appId; }
    public void setAppId(int id)             { this.appId = id; }

    public int getInternshipId()             { return internshipId; }
    public void setInternshipId(int id)      { this.internshipId = id; }

    public int getStudentId()                { return studentId; }
    public void setStudentId(int id)         { this.studentId = id; }

    public String getStudentName()           { return studentName; }
    public void setStudentName(String n)     { this.studentName = n; }

    public String getRollNo()                { return rollNo; }
    public void setRollNo(String r)          { this.rollNo = r; }

    public String getInternshipTitle()       { return internshipTitle; }
    public void setInternshipTitle(String t) { this.internshipTitle = t; }

    public String getCompanyName()           { return companyName; }
    public void setCompanyName(String n)     { this.companyName = n; }

    public String getStatus()                { return status; }
    public void setStatus(String s)          { this.status = s; }

    public String getAppliedDate()           { return appliedDate; }
    public void setAppliedDate(String d)     { this.appliedDate = d; }

    public String getRemarks()               { return remarks; }
    public void setRemarks(String r)         { this.remarks = r; }
}
