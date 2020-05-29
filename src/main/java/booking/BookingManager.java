package booking;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import booking.BookingRules.SeatType;

public class BookingManager {
    private BookingRules rules;
    private SeatMap seatMap;

    public BookingManager(SeatMap seatMap, BookingRules rules) {
        this.seatMap = seatMap;
        this.rules = rules;
    }

    public Long book(int seats) {

        assert seats > 0 : "Seats should be non zero/non negative";
        int capacity = seatMap.capacity();
        if (seats > capacity) {
            throw new SeatsUnavailableException("Total seats available is, " + capacity);
        }

        return bookSeats(seats);
    }

    private Long bookSeats(int seats) {
        Map<SeatType, Integer> plan = bookingPlan(seats);
        return plan.entrySet().stream().map(this::bookIt).reduce(0l, Long::sum);
    }

    private long bookIt(Entry<SeatType, Integer> entry) {
        return seatMap.book(entry.getKey(), entry.getValue());
    }

    private Map<SeatType, Integer> bookingPlan(int seats) {
        AtomicInteger total = new AtomicInteger(seats);
        Map<SeatType, Integer> split = new LinkedHashMap<>();
        while (rules.hasNext()) {
            SeatType type = rules.next();
            int seatCount = seatMap.getSeatCount(type);
            total.addAndGet(seatCount * -1);
            split.put(type, seatCount);
            if (total.get() <= 0) {
                if (total.get() < 0) {
                    split.computeIfPresent(type, (k, v) -> v + total.get());
                }
                break;
            }
        }
        return split;
    }
}