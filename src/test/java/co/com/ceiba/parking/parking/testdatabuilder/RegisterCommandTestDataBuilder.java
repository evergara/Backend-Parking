package co.com.ceiba.parking.parking.testdatabuilder;

import co.com.ceiba.parking.parking.application.command.RegisterCommand;
import co.com.ceiba.parking.parking.domain.model.Registry;

import java.util.Date;

public class RegisterCommandTestDataBuilder {

    private Integer id;
    private String vehicleType;
    private String licensePlate;
    private String displacement;
    private Date dateArrival;
    private Date dateDeparture;
    private long value;

    public RegisterCommandTestDataBuilder(){
        this.id = 1;
        this.vehicleType = "CAR";
        this.licensePlate = "B125";
        this.displacement = null;
        this.dateArrival = new Date();
        this.dateDeparture = null;
        this.value = 0;
    }

    public RegisterCommandTestDataBuilder withID(Integer id) {
        this.id = id;
        return this;
    }

    public RegisterCommandTestDataBuilder withVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public RegisterCommandTestDataBuilder withLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public RegisterCommandTestDataBuilder withDisplacement(String displacement) {
        this.displacement = displacement;
        return this;
    }

    public RegisterCommandTestDataBuilder withDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
        return this;
    }

    public RegisterCommandTestDataBuilder withDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
        return this;
    }
    public RegisterCommandTestDataBuilder withValue(long value) {
        this.value = value;
        return this;
    }

    public RegisterCommand build(){
        return new RegisterCommand(id, vehicleType, licensePlate, displacement, dateArrival, dateDeparture, value);
    }
}
