package booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import booking.BookingRules.SeatType;

public class SeatMap {
    Map<Integer, List<Seat>> seats;
    private int windowSeatsCount;
    private int aisleSeatsCount;
    private int middleSeatsCount;
    private int seatNoCounter;
    private int columnCount;

    public SeatMap() {
        seats = new LinkedHashMap<>();
    }

    public int maxRowCount() {
        return seats.values().stream().max(Comparator.comparingInt(List::size)).map(l -> l.size()).orElse(0);
    }

    public void addSeating(int column, int seatsPerRow, int rows, boolean middle) {
        for (int i = 0; i < rows; i++) {
            List<Seat> row = seats.getOrDefault(i, new ArrayList<>());
            for (int j = 0; j < seatsPerRow; j++) {
                Seat seat = new Seat(column, i, isAisle(i, j, middle, column, seatsPerRow),
                        isWindow(i, j, middle, column, seatsPerRow));
                if (seat.isWindow()) {
                    windowSeatsCount += 1;
                } else if (seat.isAisle()) {
                    aisleSeatsCount += 1;
                } else {
                    middleSeatsCount += 1;
                }
                columnCount += 1;
                row.add(seat);
            }
            seats.put(i, row);
        }
    }

    public int getColumnCount() {
        return columnCount;
    }

    private boolean isWindow(int i, int seatCol, boolean middle, int columnIndex, int seatsPerRow) {
        // WXX XXX XX XXXW
        // WXX XXX XX XXXW

        // WX XW
        // WX XW

        if (middle)
            return false;
        if (columnIndex == 0 && seatCol == 0) {
            return true;
        }
        if (columnIndex != 0 && seatCol == (seatsPerRow - 1)) {
            return true;
        }
        return false;
    }

    private boolean isAisle(int row, int seatCol, boolean middle, int columnIndex, int seatsPerRow) {
        // WXA AXA AA AXXW
        // WXA AXA AA AXXW
        if (middle && (seatCol == 0 || seatCol == (seatsPerRow - 1))) {
            return true;
        }
        if (!middle) {
            if (columnIndex != 0 && seatCol == 0) {
                return true;
            }
            if (columnIndex == 0 && seatCol == (seatsPerRow - 1)) {
                return true;
            }
        }
        return false;
    }

    public int getSeatCount(SeatType type) {
        switch (type) {
            case WINDOW:
                return windowSeatsCount;
            case MIDDLE:
                return middleSeatsCount;
            case AISLE:
                return aisleSeatsCount;
        }
        return 0;
    }

    public int getAisleSeatsCount() {
        return aisleSeatsCount;
    }

    public int getMiddleSeatsCOunt() {
        return middleSeatsCount;
    }

    public int getWindowSeatsCount() {
        return windowSeatsCount;
    }

    public boolean isWindow(int rowNo, int seatNo) {
        return isWindowOrAisle((seat) -> seat.get(seatNo).isWindow(), seats.get(rowNo));
    }

    public boolean isAisle(int rowNo, int seatNo) {
        return isWindowOrAisle((seat) -> seat.get(seatNo).isAisle(), seats.get(rowNo));
    }

    public boolean isWindowOrAisle(Predicate<List<Seat>> predicate, List<Seat> list) {
        return list == null ? false : predicate.test(list);
    }

    public int seatsOn(int rowNo) {
        List<Seat> rows = seats.get(rowNo);
        return rows == null ? 0 : rows.size();
    }

    public int capacity() {
        return seats.keySet().stream().flatMap(i -> Stream.of(seats.get(i))).collect(Collectors.summingInt(List::size));
    }

    public long availableWindowSeats() {
        return seats.keySet().stream().flatMap(i -> seats.get(i).stream()).filter(s -> s.isBooked()).count();
    }

    public long book(SeatType seatType, int noOfSeats) {
        AtomicInteger counter = new AtomicInteger(noOfSeats);
        long value = seats.values().stream().flatMap(l -> l.stream()).filter(s -> s.isType(seatType) && !s.isBooked())
                .filter(s -> counter.decrementAndGet() >= 0).map(s -> {
                    return s.setBooked(String.valueOf(seatNoCounter += 1));
                }).map(s -> updateBookedSeatCount(s)).map(s -> counter.get()).count();
        return value;
    }

    private boolean updateBookedSeatCount(Seat seat) {
        switch (seat.getType()) {
            case WINDOW:
                windowSeatsCount -= 1;
                break;
            case MIDDLE:
                middleSeatsCount -= 1;
                break;
            case AISLE:
                aisleSeatsCount -= 1;
        }
        return true;
    }

    private <T> T getSeatAttribute(Function<List<Seat>, T> func, List<Seat> rows) {
        return rows != null ? func.apply(rows) : null;
    }

    public boolean isBooked(int row, int seatLocation) {
        return getSeatAttribute((seatList) -> seatList.get(seatLocation).isBooked(), seats.get(row));
    }

    public int availableSeats() {
        return windowSeatsCount + aisleSeatsCount + middleSeatsCount;
    }

    public String seatNo(int row, int seatLoc) {
        return getSeatAttribute((seatList) -> seatList.get(seatLoc).getSeatNo(), seats.get(row));
    }

    public void printState() {
        Iterator<Entry<Integer, List<Seat>>> iterator = seats.entrySet().iterator();
        while (iterator.hasNext()) {
            List<Seat> seats = iterator.next().getValue();
            int prevCol = -1;
            System.out.println("+" + new String(new char[71]).replace("\0", "-") + "+");
            for (int i = 0; i < seats.size(); i++) {
                Seat seat = seats.get(i);
                if (prevCol == -1) {
                    System.out.format("%1s", "|");
                }
                if (prevCol != -1 && prevCol != seat.getColumn()) {
                    System.out.format("%7s", "|");
                }
                System.out.format("%7s", ((seat.isBooked() ? seat.getSeatNo() : "X")));
                prevCol = seat.getColumn();
            }
            System.out.format("|%7s", "\n");
        }
    }
}