package booking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import booking.BookingRules.SeatType;

public class BookingManagerTest {

    @Test(expected = AssertionError.class)
    public void shouldFailBookingSeatsWhenCapacityExceedsExpected() {
        BookingRules rules = new BookingRules();
        rules.bookAscending();
        rules.addBookingOrder(SeatType.WINDOW);
        rules.addBookingOrder(SeatType.AISLE);
        rules.addBookingOrder(SeatType.MIDDLE);
        SeatMap seatMap = SeatMapConfigurer.newConfiguration().newSeatColumn(2, 3, false).newSeatColumn(3, 3, true)
                .newSeatColumn(2, 3, false).build();
        BookingManager bookingManager = new BookingManager(seatMap, rules);
        bookingManager.book(0);
    }

    @Test()
    public void shouldBookTicketsGivenCount() {
        // WA AMA AW
        // WA AMA AW
        // WA AMA AW

        BookingRules rules = new BookingRules();
        rules.bookAscending();
        rules.addBookingOrder(SeatType.AISLE);
        rules.addBookingOrder(SeatType.WINDOW);
        rules.addBookingOrder(SeatType.MIDDLE);
        SeatMap seatMap = SeatMapConfigurer.newConfiguration().newSeatColumn(2, 3, false).newSeatColumn(3, 3, true)
                .newSeatColumn(2, 3, false).build();
        BookingManager bookingManager = new BookingManager(seatMap, rules);
        long booked = bookingManager.book(19);
        assertThat(booked).isEqualTo(19);
        assertThat(seatMap.availableSeats()).isEqualTo(2);
        assertThat(seatMap.isBooked(1, 5)).isTrue();
        assertThat(seatMap.isBooked(1, 3)).isFalse();
        assertThat(seatMap.isBooked(2, 3)).isFalse();
        assertThat(seatMap.seatNo(2, 4)).isEqualTo("11");
        assertThat(seatMap.seatNo(0, 3)).isEqualTo("19");
        assertThat(seatMap.seatNo(2, 3)).isNull();
    }

    @Test
    public void shouldPrintCurrentState() {
        BookingRules rules = new BookingRules();
        rules.bookAscending();
        rules.addBookingOrder(SeatType.AISLE);
        rules.addBookingOrder(SeatType.WINDOW);
        rules.addBookingOrder(SeatType.MIDDLE);
        SeatMap seatMap = SeatMapConfigurer.newConfiguration().newSeatColumn(2, 3, false).newSeatColumn(3, 3, true)
                .newSeatColumn(2, 4, false).build();
        BookingManager bookingManager = new BookingManager(seatMap, rules);
        bookingManager.book(18);
        seatMap.printState();
    }
}