package com.ipts.model;

/**
 * Placement - POJO for placement table (Module 5)
 */
public class Placement {
    private int placementId;
    private int companyId;
    private int studentId;
    private String companyName;   // from JOIN
    private String studentName;   // from JOIN
    private String rollNo;        // from JOIN
    private String jobRole;
    private double packageLpa;
    private String offerDate;
    private String joiningDate;
    private String placementType; // On-Campus / Off-Campus
    private String remarks;

    // ---------- Getters & Setters ----------

    public int getPlacementId()              { return placementId; }
    public void setPlacementId(int id)       { this.placementId = id; }

    public int getCompanyId()                { return companyId; }
    public void setCompanyId(int id)         { this.companyId = id; }

    public int getStudentId()                { return studentId; }
    public void setStudentId(int id)         { this.studentId = id; }

    public String getCompanyName()           { return companyName; }
    public void setCompanyName(String n)     { this.companyName = n; }

    public String getStudentName()           { return studentName; }
    public void setStudentName(String n)     { this.studentName = n; }

    public String getRollNo()                { return rollNo; }
    public void setRollNo(String r)          { this.rollNo = r; }

    public String getJobRole()               { return jobRole; }
    public void setJobRole(String r)         { this.jobRole = r; }

    public double getPackageLpa()            { return packageLpa; }
    public void setPackageLpa(double p)      { this.packageLpa = p; }

    public String getOfferDate()             { return offerDate; }
    public void setOfferDate(String d)       { this.offerDate = d; }

    public String getJoiningDate()           { return joiningDate; }
    public void setJoiningDate(String d)     { this.joiningDate = d; }

    public String getPlacementType()         { return placementType; }
    public void setPlacementType(String t)   { this.placementType = t; }

    public String getRemarks()               { return remarks; }
    public void setRemarks(String r)         { this.remarks = r; }
}
