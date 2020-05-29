package booking;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int[][] seatConfig = { { 2, 2 }, { 3, 2 }, { 3, 4 }, { 3, 5 } };
        arrangeSeat(seatConfig);
    }

    private static List<Seat> arrangeSeat(int[][] seatConfig) {
        System.out.println(seatConfig.length);
        return null;
    }
}
