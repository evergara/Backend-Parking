package co.com.ceiba.parking.parking.domain.exception;

public class ExceptionLicensePlate  extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExceptionLicensePlate(String message) {
        super(message);
    }
}
