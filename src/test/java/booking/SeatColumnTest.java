package booking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SeatColumnTest {

    @Test
    public void shouldCheckWindowSeatInFirstColumn() {
        SeatColumn column = new SeatColumn(0, 3, 2, false);
        column.create();
        assertThat(column.isWindowSeat(0, 0)).isTrue();
        assertThat(column.isWindowSeat(1, 0)).isTrue();
        assertThat(column.isWindowSeat(0, 1)).isFalse();
        assertThat(column.isWindowSeat(1, 1)).isFalse();
    }

    @Test
    public void shouldCheckWindowSeatInMiddleColumn() {
        SeatColumn column = new SeatColumn(1, 3, 2, true);
        column.create();
        assertThat(column.isWindowSeat(0, 0)).isFalse();
        assertThat(column.isWindowSeat(1, 0)).isFalse();
        assertThat(column.isWindowSeat(0, 1)).isFalse();
        assertThat(column.isWindowSeat(1, 1)).isFalse();
    }

    @Test
    public void shouldVerifyAisleSeatInMiddleColumn() {
        SeatColumn column = new SeatColumn(1, 3, 2, true);
        column.create();
        assertThat(column.isAisleSeat(0, 0)).isTrue();
        assertThat(column.isAisleSeat(1, 1)).isFalse();
        assertThat(column.isAisleSeat(1, 2)).isTrue();
    }

    @Test
    public void shouldVerifyAisleSeatInFirstColumn() {
        SeatColumn column = new SeatColumn(1, 3, 2, false);
        column.create();
        assertThat(column.isAisleSeat(0, 0)).isTrue();
        assertThat(column.isAisleSeat(1, 1)).isFalse();
        assertThat(column.isAisleSeat(1, 2)).isFalse();
    }

    @Test
    public void shouldReturnTotalSeatPerColumn() {
        SeatColumn column = new SeatColumn(1, 3, 2, false);
        column.create();

        assertThat(column.totalSeats()).isEqualTo(6);
    }

}