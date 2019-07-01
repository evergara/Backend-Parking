package co.com.ceiba.parking.parking.service;

import co.com.ceiba.parking.parking.domain.exception.ExceptionLicensePlaceDateDay;
import co.com.ceiba.parking.parking.domain.exception.ExceptionNotSpaceTypeVehicle;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.IPortRegistryRepository;
import co.com.ceiba.parking.parking.domain.service.RegisterEntryService;
import co.com.ceiba.parking.parking.domain.util.Parametrization;
import co.com.ceiba.parking.parking.testdatabuilder.RegistryTestDataBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ServicesTest {

    private IPortRegistryRepository portRegistryRepository;

    @Before
    public void StartMocks() {
        portRegistryRepository = mock(IPortRegistryRepository.class);
    }

    @Test
    public void  ParkingWithoutSpaceForCarTest(){
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(Parametrization.VEHICLETYPE_VALUE_CAR);

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);

        when(portRegistryRepository.countVehicleType(Parametrization.VEHICLETYPE_VALUE_CAR)).thenReturn(20);

        //Act

        try {
            registerEntryService.SetEntry(registry);
            fail();
        }catch (ExceptionNotSpaceTypeVehicle ex){
            // Assert
            assertEquals(String.format(Parametrization.MESSAGE_THERE_IS_NOT_SPACE, Parametrization.VEHICLETYPE_VALUE_CAR), ex.getMessage());
        }
    }

    @Test
    public void  ParkingWithoutSpaceForMotorbikeTest(){
//Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(Parametrization.VEHICLETYPE_VALUE_MOTORCYCLE)
                .withDisplacement("500");

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);

        when(portRegistryRepository.countVehicleType(Parametrization.VEHICLETYPE_VALUE_MOTORCYCLE)).thenReturn(10);

        //Act

        try {
            registerEntryService.SetEntry(registry);
            fail();
        }catch (ExceptionNotSpaceTypeVehicle ex){
            // Assert
            assertEquals(String.format(Parametrization.MESSAGE_THERE_IS_NOT_SPACE, Parametrization.VEHICLETYPE_VALUE_MOTORCYCLE), ex.getMessage());
        }
    }

    @Test
    public void doNotLetTheVehicleEnterForToTheRestrictionPlateTest(){
        //Arrange
        Calendar dateArrival =Calendar.getInstance();
        dateArrival.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        RegistryTestDataBuilder register = new RegistryTestDataBuilder().withLicensePlate("A123").withDateArrival(dateArrival.getTime());
        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        //Act

        try {
            registerEntryService.SetEntry(registry);
            fail();
        }catch (ExceptionLicensePlaceDateDay ex){
            // Assert
            assertEquals(Parametrization.MESSAGE_DAYS_NOT_ALLOWED, ex.getMessage());
        }
    }

}
