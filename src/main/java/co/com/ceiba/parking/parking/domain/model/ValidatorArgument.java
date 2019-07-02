package co.com.ceiba.parking.parking.domain.model;

import co.com.ceiba.parking.parking.domain.exception.*;

public final class ValidatorArgument {

    private ValidatorArgument(){}

    public static void validateLicencePlate(Object value, String message) {
        if (value == null) {
            throw new ExceptionLicensePlate(message);
        }
    }

    public static void validateNullVehicleType(String value, String message) {
        if (value == null) {
            throw new ExceptionNullVehicleType(message);
        }
    }

    public static void validateVehicleTypeWrongDate(String message) {
        throw new ExceptionVehicleTypeWrongDate(message);
    }

    public static void validateDisplacementNumber(String value, String message) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException numberFormatException) {
            throw new ExceptionDisplacementNotNumber(message);
        }
    }

    public static void validateDisplacementRequired(String value, String message) {
        if (value == null) {
            throw new ExceptionDisplacementRequired(message);
        }
    }
}
