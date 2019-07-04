package co.com.ceiba.parking.parking.domain.model;

import co.com.ceiba.parking.parking.domain.util.RegistryDomainConstant;

import java.util.Date;

public class Registry {

    private Integer id;
    private String vehicleType;
    private String licensePlate;
    private String displacement;
    private Date  dateArrival;
    private Date dateDeparture;
    private long value;


    public Registry(Integer id, String vehicleType, String licensePlate, String displacement, Date dateArrival, Date dateDeparture, long value) {
        ValidatorArgument.validateLicencePlate(licensePlate, RegistryDomainConstant.MESSAGE_LICENCEPLATE_REQUIRED);
        ValidatorArgument.validateNullVehicleType(vehicleType, RegistryDomainConstant. MESSAGE_VEHICLETYPE_REQUIRED);

        if(!vehicleType.contentEquals(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR) && !vehicleType.contentEquals(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)) {
            ValidatorArgument.validateVehicleTypeWrongDate(String.format(RegistryDomainConstant.MESSAGE_VEHICLETYPE_WRONG_DATA, vehicleType));
        }
        if (vehicleType.equalsIgnoreCase(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)) {
            ValidatorArgument.validateDisplacementRequired(displacement, RegistryDomainConstant.MESSAGE_DISPLACEMENT_REQUIRED);
            ValidatorArgument.validateDisplacementNumber(displacement, RegistryDomainConstant.MESSAGE_DISPLACEMENT_WRONG_DATA);
        }

        this.id = id;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.displacement = displacement;
        this.dateArrival = dateArrival;
        this.dateDeparture = dateDeparture;
        this.value = value;
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

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

}
