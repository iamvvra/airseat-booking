package booking;

public class SeatMapConfigurer {

    private SeatMap seatMap;
    int columns = 0;

    private SeatMapConfigurer() {
        this.seatMap = new SeatMap();
    }

    public static SeatMapConfigurer newConfiguration() {
        return new SeatMapConfigurer();
    }

    public SeatMapConfigurer newSeatColumn(int seatsPerRow, int rows, boolean middle) {
        seatMap.addSeating(columns, seatsPerRow, rows, middle);
        columns += 1;
        return this;
    }

    public SeatMap build() {
        return seatMap;
    }

}
