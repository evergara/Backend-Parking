package co.com.ceiba.parking.parking.service;

import co.com.ceiba.parking.parking.domain.exception.ExceptionDuplicity;
import co.com.ceiba.parking.parking.domain.exception.ExceptionLicensePlaceDateDay;
import co.com.ceiba.parking.parking.domain.exception.ExceptionNotSpaceTypeVehicle;
import co.com.ceiba.parking.parking.domain.exception.ExceptionRegistrationNotExist;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.PortRegistryRepository;
import co.com.ceiba.parking.parking.domain.service.RegisterEntryService;
import co.com.ceiba.parking.parking.domain.service.RegisterExitService;
import co.com.ceiba.parking.parking.domain.util.RegistryDomainConstant;
import co.com.ceiba.parking.parking.testdatabuilder.RegistryTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ServicesTest {

    private PortRegistryRepository portRegistryRepository;
    private Date fecha;

    @Before
    public void StartMocks() {
        this.portRegistryRepository = mock(PortRegistryRepository.class);
        this.fecha = Calendar.getInstance().getTime();

    }

    @Test
    public void  RegisterCarTest() {
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR);

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);

        when(portRegistryRepository.saveRegistro(registry)).thenReturn(registry);

        //Act
        Registry registerCopy =  registerEntryService.setEntry(registry);

        //Assert
        assertEquals(registerCopy.getId(),registry.getId());

    }

    @Test
    public void  RegisterMotorbikeTest(){
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)
                .withDisplacement("500");

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);

        when(portRegistryRepository.saveRegistro(registry)).thenReturn(registry);

        //Act
        Registry registerCopy =  registerEntryService.setEntry(registry);

        //Assert
        assertEquals(registerCopy.getId(),registry.getId());
    }


    @Test
    public void  ParkingWithoutSpaceForCarTest(){
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR);

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);

        when(portRegistryRepository.countVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR)).thenReturn(20);

        //Act

        try {
            registerEntryService.setEntry(registry);
            fail();
        }catch (ExceptionNotSpaceTypeVehicle ex){
            // Assert
            assertEquals(String.format(RegistryDomainConstant.MESSAGE_THERE_IS_NOT_SPACE, RegistryDomainConstant.VEHICLETYPE_VALUE_CAR), ex.getMessage());
        }
    }

    @Test
    public void  ParkingWithoutSpaceForMotorbikeTest(){
//Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)
                .withDisplacement("500");

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);

        when(portRegistryRepository.countVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)).thenReturn(10);

        //Act

        try {
            registerEntryService.setEntry(registry);
            fail();
        }catch (ExceptionNotSpaceTypeVehicle ex){
            // Assert
            assertEquals(String.format(RegistryDomainConstant.MESSAGE_THERE_IS_NOT_SPACE, RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE), ex.getMessage());
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
            registerEntryService.setEntry(registry);
            fail();
        }catch (ExceptionLicensePlaceDateDay ex){
            // Assert
            assertEquals(RegistryDomainConstant.MESSAGE_DAYS_NOT_ALLOWED, ex.getMessage());
        }
    }

    @Test
    public void  vehicleDoesNotExistTest(){
       //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR);

        Registry registry = register.build();

        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);

        when(portRegistryRepository.saveRegistro(registry)).thenReturn(null);

        //Act

        try {
            registerExitService.setExit(registry.getLicensePlate());
            fail();
        }catch (ExceptionRegistrationNotExist ex){
            // Assert
            assertEquals(RegistryDomainConstant.MESSAGE_THIS_UNRESOLVED_VEHICLE, ex.getMessage());
        }
    }

    @Test
    public void  RegisterCarExistTest(){
        //Arrange
        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR);

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);

        when(portRegistryRepository.saveRegistro(registry)).thenReturn(registry);

        //Act
        try {
            registerEntryService.setEntry(registry);

        }catch (ExceptionDuplicity ex){
            // Assert
            assertEquals(RegistryDomainConstant.MESSAGE_ALREADY_EXISTS_VEHICLE, "hola");
        }
    }

    @Test
    public void  RegisterChargeHourCarTest() {
        //Arrange
        int valueForHour = 1000;
        int hour = 6;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR)
                .withDateArrival(cal.getTime());

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueForHour * hour),registry.getValue(),0);

    }

    @Test
    public void  RegisterChargeHourMotorCycleTest() {
        //Arrange
        int valueForHour = 500;
        int hour = 6;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("500");

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueForHour * hour),registry.getValue(),0);

    }

    @Test
    public void  RegisterChargeHourDisplacementMaximoMotorCycleTest() {
        //Arrange
        int valueForHour = 500;
        int hour = 6;
        int valueAdictional = 2000;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("600");

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueForHour * hour)+valueAdictional,registry.getValue(),0);

    }

    @Test
    public void  RegisterChargeDayCarTest() {
        //Arrange
        int valueDay = 8000;
        int hour = 9;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR)
                .withDateArrival(cal.getTime());

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals(valueDay,registry.getValue(),0);

    }

    @Test
    public void RegisterChargeDayDisplacementMaximoMotorCycleTest() {
        //Arrange
        int valueDay = 4000;
        int hour = 9;
        int valueAdictional = 2000;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("600");

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals(valueDay + valueAdictional,registry.getValue(),0);

    }

    @Test
    public void RegisterChargeDayMotorCycleTest() {
        //Arrange
        int valueDay = 4000;
        int hour = 9;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("500");

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals(valueDay,registry.getValue(),0);

    }

    @Test
    public void  RegisterChargeDayMoreHourCarTest() {
        //Arrange
        int valueDay = 8000;
        int valueHour = 1000;
        int hour = 27;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR)
                .withDateArrival(cal.getTime());

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueHour * 3) + valueDay,registry.getValue(),0);

    }

    @Test
    public void RegisterChargeDayMoreHourMotorCycleTest() {
        //Arrange
        int valueDay = 4000;
        int valueHour = 500;
        int hour = 27;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - hour);

        RegistryTestDataBuilder register = new RegistryTestDataBuilder()
                .withVehicleType(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE)
                .withDateArrival(cal.getTime())
                .withDisplacement("500");

        Registry registry = register.build();

        RegisterEntryService registerEntryService = new RegisterEntryService(portRegistryRepository);
        RegisterExitService registerExitService = new RegisterExitService(portRegistryRepository);
        when(portRegistryRepository.findByLicensePlate(registry.getLicensePlate())).thenReturn(registry);

        //Act
        registerExitService.setExit(registry.getLicensePlate());

        //Assert
        assertEquals((valueHour * 3) + valueDay,registry.getValue(),0);

    }

}
