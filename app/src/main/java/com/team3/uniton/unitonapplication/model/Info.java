package com.team3.uniton.unitonapplication.model;

public class Info {
  private String join_year;
  private String join_month;
  private String join_day;
  private String company_name;
  private String department;
  private String position;

  public Info(String join_year, String join_month, String join_day, String company_name, String department, String position) {
    this.join_year = join_year;
    this.join_month = join_month;
    this.join_day = join_day;
    this.company_name = company_name;
    this.department = department;
    this.position = position;
  }

  public String getJoin_year() {
    return join_year;
  }

  public void setJoin_year(String join_year) {
    this.join_year = join_year;
  }

  public String getJoin_month() {
    return join_month;
  }

  public void setJoin_month(String join_month) {
    this.join_month = join_month;
  }

  public String getJoin_day() {
    return join_day;
  }

  public void setJoin_day(String join_day) {
    this.join_day = join_day;
  }

  public String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(String company_name) {
    this.company_name = company_name;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
