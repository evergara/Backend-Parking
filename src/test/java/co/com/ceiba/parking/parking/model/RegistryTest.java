package co.com.ceiba.parking.parking.model;

import co.com.ceiba.parking.parking.domain.exception.*;
import co.com.ceiba.parking.parking.domain.util.RegistryDomainConstant;
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
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionLicensePlate.class, RegistryDomainConstant.MESSAGE_LICENCEPLATE_REQUIRED);
    }

    @Test
    public void validateVehicleTypeRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType(null);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionNullVehicleType.class, RegistryDomainConstant.MESSAGE_VEHICLETYPE_REQUIRED);
    }

    @Test
    public void validateVehicleTypeWrongDate() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();
        String VehicleType = "AUTO";
        registryTestDataBuilder.withVehicleType(VehicleType);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionVehicleTypeWrongDate.class,String.format(RegistryDomainConstant.MESSAGE_VEHICLETYPE_WRONG_DATA, VehicleType));
    }

    @Test
    public void validateDisplacementNumber() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();
        registryTestDataBuilder.withVehicleType("MOTORCYCLE");
        registryTestDataBuilder.withDisplacement("cero");

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionDisplacementNotNumber.class, RegistryDomainConstant.MESSAGE_DISPLACEMENT_WRONG_DATA);
    }

    @Test
    public void validateDisplacementRequired() {
        //Arrange
        RegistryTestDataBuilder registryTestDataBuilder = new RegistryTestDataBuilder();

        registryTestDataBuilder.withVehicleType("MOTORCYCLE");
        registryTestDataBuilder.withDisplacement(null);

        //Act - Assert
        TestBase.assertThrows(() -> registryTestDataBuilder.build(), ExceptionDisplacementRequired.class, RegistryDomainConstant.MESSAGE_DISPLACEMENT_REQUIRED);
    }

}

