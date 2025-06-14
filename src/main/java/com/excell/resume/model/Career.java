package com.excell.resume.model;

public class Career {
    String workPeriod;
    String companyName;
    String jobTile;
    String employmentYears;

    public Career() {
    }

    public Career(String workPeriod, String companyName, String jobTile, String employmentYears) {
        this.workPeriod = workPeriod;
        this.companyName = companyName;
        this.jobTile = jobTile;
        this.employmentYears = employmentYears;
    }

    public String getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(String workPeriod) {
        this.workPeriod = workPeriod;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTile() {
        return jobTile;
    }

    public void setJobTile(String jobTile) {
        this.jobTile = jobTile;
    }

    public String getEmploymentYears() {
        return employmentYears;
    }

    public void setEmploymentYears(String employmentYears) {
        this.employmentYears = employmentYears;
    }

    @Override
    public String toString() {
        return "Career{" +
            "workPeriod='" + workPeriod + '\'' +
            ", companyName='" + companyName + '\'' +
            ", jobTile='" + jobTile + '\'' +
            ", employmentYears='" + employmentYears + '\'' +
            '}';
    }
}
