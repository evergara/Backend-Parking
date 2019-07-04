package co.com.ceiba.parking.parking.domain.service;

import co.com.ceiba.parking.parking.domain.exception.ExceptionRegistrationNotExist;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.PortRegistryRepository;
import co.com.ceiba.parking.parking.domain.service.factory.FactoryChargePark;
import co.com.ceiba.parking.parking.domain.service.factory.ChargeParking;
import co.com.ceiba.parking.parking.domain.util.RegistryDomainConstant;

import java.util.Date;

public class RegisterExitService {

    private PortRegistryRepository registryRepository;

    // Inyeccion de dependencia por contructor
    public RegisterExitService(PortRegistryRepository registryRepository){
        this.registryRepository = registryRepository;
    }

    public void setExit(String licensePlate) {
        Registry registry = registryRepository.findByLicensePlate(licensePlate);

        if (registry == null) {
            throw new ExceptionRegistrationNotExist(RegistryDomainConstant.MESSAGE_THIS_UNRESOLVED_VEHICLE);
        }
        chargePark(registry);
        registryRepository.saveRegistro(registry);
    }

    private void chargePark(Registry registry) {
        registry.setDateDeparture(new Date());
        ChargeParking chargePark = FactoryChargePark.getInstance(registry.getVehicleType());
        chargePark.setCharge(registry);
    }

}
