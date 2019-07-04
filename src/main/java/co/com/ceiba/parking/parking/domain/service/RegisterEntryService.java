package co.com.ceiba.parking.parking.domain.service;

import co.com.ceiba.parking.parking.domain.exception.ExceptionDuplicity;
import co.com.ceiba.parking.parking.domain.exception.ExceptionLicensePlaceDateDay;
import co.com.ceiba.parking.parking.domain.exception.ExceptionNotSpaceTypeVehicle;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.PortRegistryRepository;
import co.com.ceiba.parking.parking.domain.util.RegistryDomainConstant;

import java.util.Calendar;
import java.util.Date;

public class RegisterEntryService {


    private PortRegistryRepository registryRepository;

    public RegisterEntryService(PortRegistryRepository registryRepository){
        this.registryRepository = registryRepository;
    }

    public Registry setEntry(Registry registry){
        validatePrevious(registry);
        validateAmountvehicleType(registry.getVehicleType());
        validateLicencePlaceDateDay(registry.getLicensePlate(), registry.getDateArrival());
        return registryRepository.saveRegistro(registry);
    }

    private void validateLicencePlaceDateDay(String licensePlate, Date dateArrival) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateArrival);

        if( (licensePlate.toUpperCase().startsWith(RegistryDomainConstant.MESSAGE_RESTRICTED_PLATES_THAT_START_WITH_THE_LETTER))
                && (cal.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY)){
            throw new ExceptionLicensePlaceDateDay(RegistryDomainConstant.MESSAGE_DAYS_NOT_ALLOWED);
        }
    }

    private void validateAmountvehicleType(String vehicleType) {
        int amountVehicleType = registryRepository.countVehicleType(vehicleType);

        if((vehicleType.contentEquals(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR) && RegistryDomainConstant.VEHICLETYPE_MAXIMO_CAR == amountVehicleType)
                || (vehicleType.contentEquals(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE) && RegistryDomainConstant.VEHICLETYPE_MAXIMO_MOTORCYCLE == amountVehicleType)){
            throw new ExceptionNotSpaceTypeVehicle(String.format(RegistryDomainConstant.MESSAGE_THERE_IS_NOT_SPACE, vehicleType));
        }

    }

    private void validatePrevious(Registry registry) {
        boolean exists = registryRepository.exists(registry);
        if(exists) {
            throw new ExceptionDuplicity(RegistryDomainConstant.MESSAGE_ALREADY_EXISTS_VEHICLE);
        }
    }

}