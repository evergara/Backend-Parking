package co.com.ceiba.parking.parking.domain.service;

import co.com.ceiba.parking.parking.domain.exception.ExceptionRegistrationNotExist;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.IPortRegistryRepository;
import co.com.ceiba.parking.parking.domain.service.factory.FactoryChargePark;
import co.com.ceiba.parking.parking.domain.service.factory.IChargeParking;
import co.com.ceiba.parking.parking.domain.util.Parametrization;

import java.util.Date;

public class RegisterExitService {

    private IPortRegistryRepository registryRepository;

    // Inyeccion de dependencia por contructor
    public RegisterExitService(IPortRegistryRepository registryRepository){
        this.registryRepository = registryRepository;
    }

    public void setExit(String licensePlate) {
        Registry registry = registryRepository.findByLicensePlate(licensePlate);

        if (registry == null) {
            throw new ExceptionRegistrationNotExist(Parametrization.MESSAGE_THIS_UNRESOLVED_VEHICLE);
        }
        chargePark(registry);
        registryRepository.saveRegistro(registry);
    }

    private void chargePark(Registry registry) {
        FactoryChargePark factoryChargePark;
        registry.setDateDeparture(new Date());
        IChargeParking chargePark = FactoryChargePark.getInstance(registry.getVehicleType());
        chargePark.setCharge(registry);
    }

}
