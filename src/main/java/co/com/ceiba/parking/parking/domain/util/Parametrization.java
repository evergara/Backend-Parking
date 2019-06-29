package co.com.ceiba.parking.parking.domain.util;

public final class Parametrization {

    public static final String MESSAGE_LICENCEPLATE_REQUIRED = "La placa es un dato requerido.";
    public static final String MESSAGE_VEHICLETYPE_REQUIRED = "El campo tipo vehiculo es querido.";
    public static final String MESSAGE_VEHICLETYPE_WRONG_DATA = "El campo tipo vehiculo no tiene valor (%S) valido.";
    public static final String MESSAGE_DISPLACEMENT_REQUIRED = "El campo cilindraje es requerido.";
    public static final String MESSAGE_DISPLACEMENT_WRONG_DATA  = "El campo cilindraje debe ser numerico.";

    public static final String VEHICLETYPE_VALUE_CAR = "CAR";
    public static final String VEHICLETYPE_VALUE_MOTORCYCLE = "MOTORCYCLE";


    public static final String MESSAGE_RESTRICTED_PLATES_THAT_START_WITH_THE_LETTER = "A";
    public static final String MESSAGE_DAYS_NOT_ALLOWED = "El vehiculo no esta autorizado a ingresar.";
    public static final String MESSAGE_THERE_IS_NOT_SPACE = "En este momento no hay cupo para el tipo de vehiculo %S";
    public static final String MESSAGE_THIS_UNRESOLVED_VEHICLE = "El vehiculo no se encuentra en el parqueadero";
    public static final String MESSAGE_ALREADY_EXISTS_VEHICLE = "El vehiculo ya ha ingresado";

    public static final int VEHICLETYPE_MAXIMO_CAR = 20;
    public static final int VEHICLETYPE_MAXIMO_MOTORCYCLE = 10;

    private Parametrization(){}
}
