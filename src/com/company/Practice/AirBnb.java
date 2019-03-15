package com.company.Practice;

import java.util.*;

public class AirBnb {
    List<Booking> bookings;

    public AirBnb() {
        this.bookings = new ArrayList<Booking>();
    }

    public void addBooking(int start, int end) {
        if (isAvailable(start, end)) {
            this.bookings.add(new Booking(start, end));
            Collections.sort(bookings, (a, b) -> a.start - b.start);
        }
    }

    public void printBookings() {
        for (Booking b : this.bookings) {
            System.out.println(b.toString());
        }
    }

    public boolean isAvailable(int start, int end) {
        int lo = 0;
        int hi = bookings.size() - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            Booking curr = bookings.get(mid);

            if ((end >= curr.start && start <= curr.end) || (end >= curr.start && start <= curr.end)) {
                return false;
            }

            if (end < curr.start) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }

        }

        return true;
    }
}
