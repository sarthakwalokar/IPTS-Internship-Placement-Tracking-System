package com.ipts.model;

/**
 * Internship - POJO for internship table (Module 4)
 */
public class Internship {
    private int internshipId;
    private int companyId;
    private String companyName; // joined from company table for display
    private String title;
    private String description;
    private Double stipend;
    private int durationMonths;
    private String startDate;
    private String endDate;
    private String location;
    private String lastApplyDate;

    // ---------- Getters & Setters ----------

    public int getInternshipId()              { return internshipId; }
    public void setInternshipId(int id)       { this.internshipId = id; }

    public int getCompanyId()                 { return companyId; }
    public void setCompanyId(int id)          { this.companyId = id; }

    public String getCompanyName()            { return companyName; }
    public void setCompanyName(String n)      { this.companyName = n; }

    public String getTitle()                  { return title; }
    public void setTitle(String t)            { this.title = t; }

    public String getDescription()            { return description; }
    public void setDescription(String d)      { this.description = d; }

    public Double getStipend()                { return stipend; }
    public void setStipend(Double s)          { this.stipend = s; }

    public int getDurationMonths()            { return durationMonths; }
    public void setDurationMonths(int m)      { this.durationMonths = m; }

    public String getStartDate()              { return startDate; }
    public void setStartDate(String d)        { this.startDate = d; }

    public String getEndDate()                { return endDate; }
    public void setEndDate(String d)          { this.endDate = d; }

    public String getLocation()               { return location; }
    public void setLocation(String l)         { this.location = l; }

    public String getLastApplyDate()          { return lastApplyDate; }
    public void setLastApplyDate(String d)    { this.lastApplyDate = d; }
}
