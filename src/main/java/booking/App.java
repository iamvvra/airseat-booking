package booking;

import booking.BookingRules.SeatType;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        BookingRules rules = new BookingRules();
        rules.bookAscending();
        rules.addBookingOrder(SeatType.AISLE);
        rules.addBookingOrder(SeatType.WINDOW);
        rules.addBookingOrder(SeatType.MIDDLE);
        SeatMap seatMap = SeatMapConfigurer.newConfiguration().newSeatColumn(3, 2, false).newSeatColumn(4, 3, true)
                .newSeatColumn(2, 3, true).newSeatColumn(3, 4, false).build();
        BookingManager bookingManager = new BookingManager(seatMap, rules);
        bookingManager.book(18);

        seatMap.printState();

    }
}
