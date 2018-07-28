package com.team3.uniton.unitonapplication.model;

import java.util.List;

public class MainModel {

    String company_name;
    int attendance_day;
    int current_reason_count;
    List<Resignation> resignation;

    public MainModel(String company_name, int attendance_day, int current_reason_count, List<Resignation> resignation) {
        this.company_name = company_name;
        this.attendance_day = attendance_day;
        this.current_reason_count = current_reason_count;
        this.resignation = resignation;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getAttendance_day() {
        return attendance_day;
    }

    public void setAttendance_day(int attendance_day) {
        this.attendance_day = attendance_day;
    }

    public int getCurrent_reason_count() {
        return current_reason_count;
    }

    public void setCurrent_reason_count(int current_reason_count) {
        this.current_reason_count = current_reason_count;
    }

    public List<Resignation> getResignation() {
        return resignation;
    }

    public void setResignation(List<Resignation> resignation) {
        this.resignation = resignation;
    }
}
