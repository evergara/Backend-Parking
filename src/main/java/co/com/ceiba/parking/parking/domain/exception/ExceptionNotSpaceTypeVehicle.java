package co.com.ceiba.parking.parking.domain.exception;

public class ExceptionNotSpaceTypeVehicle extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public ExceptionNotSpaceTypeVehicle(String message) {
        super(message);
    }
}
