package co.com.ceiba.parking.parking.domain.exception;

public class ExceptionNullVehicleType extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ExceptionNullVehicleType(String message) {
        super(message);
    }
}