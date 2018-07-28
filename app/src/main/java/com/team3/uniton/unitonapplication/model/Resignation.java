package com.team3.uniton.unitonapplication.model;

public class Resignation {

    int resignation_id;
    String first_reason;
    String second_reason;
    String third_reason;
    String date;

    public Resignation(int resignation_id, String first_reason, String second_reason, String third_reason, String date) {
        this.resignation_id = resignation_id;
        this.first_reason = first_reason;
        this.second_reason = second_reason;
        this.third_reason = third_reason;
        this.date = date;
    }

    public int getResignation_id() {
        return resignation_id;
    }

    public void setResignation_id(int resignation_id) {
        this.resignation_id = resignation_id;
    }

    public String getFirst_reason() {
        return first_reason;
    }

    public void setFirst_reason(String first_reason) {
        this.first_reason = first_reason;
    }

    public String getSecond_reason() {
        return second_reason;
    }

    public void setSecond_reason(String second_reason) {
        this.second_reason = second_reason;
    }

    public String getThird_reason() {
        return third_reason;
    }

    public void setThird_reason(String third_reason) {
        this.third_reason = third_reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
