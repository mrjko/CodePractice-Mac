package com.company.Practice;

public class Booking {
    int start;
    int end;

    public Booking(int s, int e) {
        this.start = s;
        this.end = e;
    }

    @Override
    public String toString() {
        return "[" + this.start + " ," + this.end + "]";
    }
}
