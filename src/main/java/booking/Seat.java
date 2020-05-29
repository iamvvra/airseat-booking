package booking;

import booking.BookingRules.SeatType;

public class Seat {
    private int column;
    private int row;
    private boolean aisle;
    private boolean window;
    private boolean booked;
    private SeatType type;
    private String seatNo;

    public Seat(int column, int row, boolean aisle, boolean window) {
        this.column = column;
        this.row = row;
        this.aisle = aisle;
        this.window = window;
        type(aisle, window);
    }

    public String getSeatNo() {
        return seatNo;
    }

    private void type(boolean aisle, boolean window) {
        this.type = aisle ? SeatType.AISLE : window ? SeatType.WINDOW : SeatType.MIDDLE;
    }

    public SeatType getType() {
        return type;
    }

    public boolean isType(SeatType type) {
        return this.type.equals(type);
    }

    public Seat setBooked(String seatNo) {
        this.seatNo = seatNo;
        this.booked = true;
        return this;
    }

    public boolean isWindow() {
        return window;
    }

    public boolean isAisle() {
        return aisle;
    }

    public int getColumn() {
        return column;
    }

    public int getRows() {
        return row;
    }

    public boolean isBooked() {
        return booked;
    }

    public void markAsBook() {
        booked = true;
    }

}