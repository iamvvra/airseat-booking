package booking;

public class SeatsUnavailableException extends RuntimeException {
    public SeatsUnavailableException(String msg) {
        super(msg);
    }
}
