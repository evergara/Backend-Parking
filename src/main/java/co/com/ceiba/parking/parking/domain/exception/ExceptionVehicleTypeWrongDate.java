package co.com.ceiba.parking.parking.domain.exception;

public class ExceptionVehicleTypeWrongDate extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExceptionVehicleTypeWrongDate(String message) {
        super(message);
    }
}
