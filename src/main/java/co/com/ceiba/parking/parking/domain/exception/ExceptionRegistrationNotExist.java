package co.com.ceiba.parking.parking.domain.exception;

public class ExceptionRegistrationNotExist extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExceptionRegistrationNotExist(String message) {
        super(message);
    }
}
