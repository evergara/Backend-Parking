package co.com.ceiba.parking.parking.domain.exception;

public class ExceptionDisplacementRequired extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExceptionDisplacementRequired(String message) {
        super(message);
    }
}
