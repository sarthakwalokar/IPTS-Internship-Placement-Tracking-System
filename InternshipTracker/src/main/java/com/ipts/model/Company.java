package com.ipts.model;

/**
 * Company - POJO for company table (Module 3)
 */
public class Company {
    private int companyId;
    private String companyName;
    private String industry;
    private String website;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String address;

    // ---------- Getters & Setters ----------

    public int getCompanyId()               { return companyId; }
    public void setCompanyId(int id)        { this.companyId = id; }

    public String getCompanyName()          { return companyName; }
    public void setCompanyName(String n)    { this.companyName = n; }

    public String getIndustry()             { return industry; }
    public void setIndustry(String i)       { this.industry = i; }

    public String getWebsite()              { return website; }
    public void setWebsite(String w)        { this.website = w; }

    public String getContactPerson()        { return contactPerson; }
    public void setContactPerson(String c)  { this.contactPerson = c; }

    public String getContactEmail()         { return contactEmail; }
    public void setContactEmail(String e)   { this.contactEmail = e; }

    public String getContactPhone()         { return contactPhone; }
    public void setContactPhone(String p)   { this.contactPhone = p; }

    public String getAddress()              { return address; }
    public void setAddress(String a)        { this.address = a; }
}
