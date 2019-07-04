package co.com.ceiba.parking.parking.infrastructure.exception;
import co.com.ceiba.parking.parking.domain.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    private static final String A_FAILURE_OCCURS_FAVOR_TO_CONTACT_THE_ADMINISTRATOR = "Ocurrió un error favor contactar al administrador.";
    private static final ConcurrentHashMap<String, Integer> STATE_CODES = new ConcurrentHashMap<>();

    public ExceptionHandler(){
        STATE_CODES.put(ExceptionDisplacementNotNumber.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionDisplacementRequired.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionDuplicity.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionLicensePlate.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionNullVehicleType.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATE_CODES.put(ExceptionVehicleTypeWrongDate.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionInfrastucture> handleAllExceptions(ExceptionInfrastucture exception) {
        ResponseEntity<ExceptionInfrastucture> response;

        String name = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        Integer code = STATE_CODES.get(name);

        if (code != null) {
            ExceptionInfrastucture error = new ExceptionInfrastucture(name, message);
            response = new ResponseEntity<>(error, HttpStatus.valueOf(code));
        } else {
            LOGGER .error(name, exception);
            ExceptionInfrastucture error = new ExceptionInfrastucture(name, A_FAILURE_OCCURS_FAVOR_TO_CONTACT_THE_ADMINISTRATOR);
            response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
