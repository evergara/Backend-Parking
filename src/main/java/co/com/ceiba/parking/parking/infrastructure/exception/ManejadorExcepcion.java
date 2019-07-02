package co.com.ceiba.parking.parking.infrastructure.exception;
import co.com.ceiba.parking.parking.domain.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ManejadorExcepcion {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger(ManejadorExcepcion.class);
    private static final String OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR = "Ocurrió un error favor contactar al administrador.";
    private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();

    public ManejadorExcepcion(){
        CODIGOS_ESTADO.put(ExceptionDisplacementNotNumber.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExceptionDisplacementRequired.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExceptionDuplicity.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExceptionLicensePlate.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExceptionNullVehicleType.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExceptionVehicleTypeWrongDate.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionInfrastucture> handleAllExceptions(ExceptionInfrastucture exception) {
        ResponseEntity<ExceptionInfrastucture> response;

        String name = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        Integer code = CODIGOS_ESTADO.get(name);

        if (code != null) {
            ExceptionInfrastucture error = new ExceptionInfrastucture(name, message);
            response = new ResponseEntity<>(error, HttpStatus.valueOf(code));
        } else {
            LOGGER_ERROR.error(name, exception);
            ExceptionInfrastucture error = new ExceptionInfrastucture(name, OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR);
            response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
