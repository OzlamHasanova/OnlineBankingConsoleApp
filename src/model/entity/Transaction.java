package model.entity;

import java.time.LocalDate;

public class Transaction {
    private String detail;
    private LocalDate date;
    private int accountNumber;

    public Transaction(String detail, LocalDate date) {
        this.detail = detail;
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "detail='" + detail + '\'' +
                ", date=" + date +
                '}';
    }
}
