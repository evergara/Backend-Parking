package co.com.ceiba.parking.parking.domain.exception;

public class ExceptionDuplicity extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExceptionDuplicity(String message) {
        super(message);
    }
}
