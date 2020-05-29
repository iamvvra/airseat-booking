package booking;

public class SeatColumn {
    private int seatCountPerRow;
    private int rows;
    private int columnIndex;
    Seat[][] seats = null;
    private boolean middleColumn;

    public SeatColumn(int columnIndex, int seatCountPerRow, int rows, boolean middleColumn) {
        this.columnIndex = columnIndex;
        this.seatCountPerRow = seatCountPerRow;
        this.rows = rows;
        this.middleColumn = middleColumn;
    }

    public int totalSeats() {
        return seatCountPerRow * rows;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getSeatCountPerRow() {
        return seatCountPerRow;
    }

    public int getRows() {
        return rows;
    }

    public boolean isMiddleColumn() {
        return middleColumn;
    }

    public void create() {
        seats = new Seat[rows][seatCountPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatCountPerRow; j++) {
                seats[i][j] = new Seat(columnIndex, j, isAisle(i, j), isWindow(i, j));
            }
        }
    }

    private boolean isWindow(int i, int j) {
        // WXX XXX XX XXXW
        // WXX XXX XX XXXW

        // WX XW
        // WX XW
        if (isMiddleColumn())
            return false;

        if (columnIndex == 0 && j == 0) {
            return true;
        } else {
            if (!isMiddleColumn() && j == (seatCountPerRow - 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAisle(int i, int j) {
        // WXA AXA AA AXXW
        // WXA AXA AA AXXW
        if (isMiddleColumn() && (j == 0 || j == (seatCountPerRow - 1))) {
            return true;
        }
        if (columnIndex == 0) {

        }
        if (!isMiddleColumn() && columnIndex != 0) {
            if (j == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isWindowSeat(int row, int column) {
        return seats[row][column].isWindow();
    }

    public boolean isAisleSeat(int row, int column) {
        return seats[row][column].isAisle();
    }

    public void getNextWindowSeat() {

    }

}