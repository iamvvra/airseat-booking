package booking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SeatMapTest {

    @Test
    public void shouldCreateSeatingsWithMultipleRowsAndColumns() {
        SeatMap seatMap = new SeatMap();
        seatMap.addSeating(0, 3, 3, false);
        seatMap.addSeating(1, 4, 3, true);
        seatMap.addSeating(2, 2, 4, false);

        assertThat(seatMap.seatsOn(0)).isEqualTo(9);
        assertThat(seatMap.seatsOn(3)).isEqualTo(2);
        assertThat(seatMap.capacity()).isEqualTo(29);
    }

    @Test
    public void shouldVerifySeatTypes() {
        SeatMap seatMap = new SeatMap();
        seatMap.addSeating(0, 3, 3, false);
        seatMap.addSeating(1, 4, 3, true);
        seatMap.addSeating(2, 2, 4, false);

        assertThat(seatMap.isAisle(0, 0)).isFalse();
        assertThat(seatMap.isAisle(0, 2)).isTrue();
        assertThat(seatMap.isAisle(0, 1)).isFalse();
        assertThat(seatMap.isWindow(0, 1)).isFalse();
        assertThat(seatMap.isWindow(0, 3)).isFalse();
        assertThat(seatMap.isAisle(0, 3)).isTrue();
        assertThat(seatMap.isAisle(0, 4)).isFalse();
        assertThat(seatMap.isAisle(0, 6)).isTrue();
    }

}