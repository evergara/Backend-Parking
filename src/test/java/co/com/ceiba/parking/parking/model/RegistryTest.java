package co.com.ceiba.parking.parking.model;

import co.com.ceiba.parking.parking.domain.exception.*;
import co.com.ceiba.parking.parking.domain.util.Parametrization;
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
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionLicensePlate.class, Parametrization.MESSAGE_LICENCEPLATE_REQUIRED);
    }

    @Test
    public void validateVehicleTypeRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType(null);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionNullVehicleType.class,Parametrization.MESSAGE_VEHICLETYPE_REQUIRED);
    }

    @Test
    public void validateVehicleTypeWrongDate() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();
        String VehicleType = "AUTO";
        registryTestDataBuilder.withVehicleType(VehicleType);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionVehicleTypeWrongDate.class,String.format(Parametrization.MESSAGE_VEHICLETYPE_WRONG_DATA, VehicleType));
    }

    @Test
    public void validateDisplacementNumber() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();
        registryTestDataBuilder.withVehicleType("MOTORCYCLE");
        registryTestDataBuilder.withDisplacement("cero");

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionDisplacementNotNumber.class, Parametrization.MESSAGE_DISPLACEMENT_WRONG_DATA);
    }

    @Test
    public void validateDisplacementRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType("MOTORCYCLE");
        registryTestDataBuilder.withDisplacement(null);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionDisplacementRequired.class,Parametrization.MESSAGE_DISPLACEMENT_REQUIRED);
    }

}

