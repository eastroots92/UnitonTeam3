package com.team3.uniton.unitonapplication.model;

public class Info {
  private int join_year;
  private int join_month;
  private int join_day;
  private String company_name;
  private String department;
  private String position;

  public Info(int join_year, int join_month, int join_day, String company_name, String department, String position) {
    this.join_year = join_year;
    this.join_month = join_month;
    this.join_day = join_day;
    this.company_name = company_name;
    this.department = department;
    this.position = position;
  }

  public int getJoin_year() {
    return join_year;
  }

  public void setJoin_year(int join_year) {
    this.join_year = join_year;
  }

  public int getJoin_month() {
    return join_month;
  }

  public void setJoin_month(int join_month) {
    this.join_month = join_month;
  }

  public int getJoin_day() {
    return join_day;
  }

  public void setJoin_day(int join_day) {
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
