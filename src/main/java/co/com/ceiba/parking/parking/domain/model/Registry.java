package co.com.ceiba.parking.parking.domain.model;

import java.util.Date;

public class Registry {

    private static final String MESSAGE_LICENCEPLATE_REQUIRED = "La placa es un dato requerido.";
    private static final String MESSAGE_VEHICLETYPE_REQUIRED = "El campo tipo vehiculo es querido.";
    private static final String MESSAGE_VEHICLETYPE_WRONG_DATA = "El campo tipo vehiculo no tiene valor (%S) valido.";
    private static final String MESSAGE_DISPLACEMENT_REQUIRED = "El campo cilindraje es requerido.";
    private static final String MESSAGE_DISPLACEMENT_WRONG_DATA  = "El campo cilindraje debe ser numerico.";

    private static final String VEHICLETYPE_VALUE_CAR = "CAR";
    private static final String VEHICLETYPE_VALUE_MOTORCYCLE = "MOTORCYCLE";


    private Integer id;
    private String vehicleType;
    private String licensePlate;
    private String displacement;
    private Date  dateArrival;
    private Date dateDeparture;


    public Registry(Integer id, String vehicleType, String licensePlate, String displacement, Date dateArrival, Date dateDeparture) {
        ValidatorArgument.validateLicencePlate(licensePlate, MESSAGE_LICENCEPLATE_REQUIRED);
        ValidatorArgument.validateNullVehicleType(vehicleType, MESSAGE_VEHICLETYPE_REQUIRED);

        if(!vehicleType.contentEquals(VEHICLETYPE_VALUE_CAR) && !vehicleType.contentEquals(VEHICLETYPE_VALUE_MOTORCYCLE)) {
            ValidatorArgument.validateVehicleTypeWrongDate( String.format(MESSAGE_VEHICLETYPE_WRONG_DATA, vehicleType));
        }
        if (vehicleType.equalsIgnoreCase(VEHICLETYPE_VALUE_MOTORCYCLE)) {
            ValidatorArgument.validateDisplacementRequired(displacement, MESSAGE_DISPLACEMENT_REQUIRED);
            ValidatorArgument.validateDisplacementNumber(displacement, MESSAGE_DISPLACEMENT_WRONG_DATA);
        }

        this.id = id;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.displacement = displacement;
        this.dateArrival = dateArrival;
        this.dateDeparture = dateDeparture;
    }

    public Integer getId() {
        return id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }


    public String getDisplacement() {
        return displacement;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }
}
