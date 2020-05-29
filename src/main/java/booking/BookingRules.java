package booking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingRules {
    private final List<SeatType> bookingOrder;

    private boolean ascending;
    private boolean descending;

    private int currentPosition = 0;

    private Iterator<SeatType> ruleIterator;

    public BookingRules() {
        bookingOrder = new ArrayList<>();
    }

    public void addBookingOrder(SeatType seatType) {
        bookingOrder.add(seatType);
    }

    public void bookAscending() {
        ascending = true;
    }

    public void bookDescendinig() {
        descending = true;
    }

    public boolean isAscending() {
        return ascending;
    }

    public boolean isDescending() {
        return descending;
    }

    public SeatType nextAvailableSeat() {
        SeatType nextSeat = bookingOrder.get(currentPosition);
        currentPosition += 1;
        if (currentPosition >= bookingOrder.size())
            currentPosition = 0;
        return nextSeat;
    }

    public boolean hasNext() {
        if (ruleIterator == null) {
            ruleIterator = bookingOrder.iterator();
        }
        boolean hasNext = ruleIterator.hasNext();
        if (!hasNext)
            ruleIterator = null;
        return hasNext;
    }

    public SeatType next() {
        return ruleIterator.next();
    }

    public static enum SeatType {
        WINDOW, AISLE, MIDDLE;
    }
}