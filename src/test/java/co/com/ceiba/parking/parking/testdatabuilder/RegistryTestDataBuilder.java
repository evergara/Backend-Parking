package co.com.ceiba.parking.parking.testdatabuilder;

import co.com.ceiba.parking.parking.domain.model.Registry;

import java.text.ParseException;
import java.util.Date;

public class RegistryTestDataBuilder {

    private Integer id;
    private String vehicleType;
    private String licensePlate;
    private String displacement;
    private Date dateArrival;
    private Date dateDeparture;
    private long value;

    public  RegistryTestDataBuilder(){
        this.id = 1;
        this.vehicleType = "CAR";
        this.licensePlate = "B25";
        this.displacement = null;
        this.dateArrival = new Date();
        this.dateDeparture = null;
        this.value = 0;
    }

    public RegistryTestDataBuilder withID(Integer id) {
        this.id = id;
        return this;
    }

    public RegistryTestDataBuilder withVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public RegistryTestDataBuilder withLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public RegistryTestDataBuilder withDisplacement(String displacement) {
        this.displacement = displacement;
        return this;
    }

    public RegistryTestDataBuilder withDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
        return this;
    }

    public RegistryTestDataBuilder withDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
        return this;
    }
    public RegistryTestDataBuilder withValue(long value) {
        this.value = value;
        return this;
    }

    public Registry build(){
        return new Registry(id, vehicleType, licensePlate, displacement, dateArrival, dateDeparture, value);
    }
}
