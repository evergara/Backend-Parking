package co.com.ceiba.parking.parking.model;

import co.com.ceiba.parking.parking.domain.exception.*;
import co.com.ceiba.parking.parking.testbase.TestBase;
import co.com.ceiba.parking.parking.testdatabuilder.RegistryTestDataBuilder;
import org.junit.Test;

public class RegistryTest {

    @Test
    public void validateLicencePlateRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withLicensePlate(null);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionLicensePlate.class,"La placa es un dato requerido.");
    }

    @Test
    public void validateVehicleTypeRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType(null);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionNullVehicleType.class,"El campo tipo vehiculo es querido.");
    }

    @Test
    public void validateVehicleTypeWrongDate() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType("AUTO");

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionVehicleTypeWrongDate.class,"El campo tipo vehiculo no tiene valor (AUTO) valido.");
    }

    @Test
    public void validateDisplacementNumber() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();
        registryTestDataBuilder.withVehicleType("MOTORCYCLE");
        registryTestDataBuilder.withDisplacement("cero");

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionDisplacementNotNumber.class,"El campo cilindraje debe ser numerico.");
    }

    @Test
    public void validateDisplacementRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType("MOTORCYCLE");
        registryTestDataBuilder.withDisplacement(null);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionDisplacementRequired.class,"El campo cilindraje es requerido.");
    }

}

