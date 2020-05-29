package booking;

import java.util.List;
import java.util.stream.Collectors;

public class SeatArrangement {

    private List<SeatColumn> seats;
    private BookingRules bookingRules;

    public SeatArrangement(List<SeatColumn> seats, BookingRules bookingRules) {
        this.seats = seats;
        this.bookingRules = bookingRules;
    }

    public void arrange() {

    }

    public int capacity() {
        return seats.stream().collect(Collectors.summingInt(s -> s.totalSeats()));
    }

    public Seat book() {
        // WMA AMMA AW
        // WMA AMMA AW
        // WMA AMMA AW
        // WMA
        
        return null;
    }

}