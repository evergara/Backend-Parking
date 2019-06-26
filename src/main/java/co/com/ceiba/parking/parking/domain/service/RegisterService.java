package co.com.ceiba.parking.parking.domain.service;

import co.com.ceiba.parking.parking.domain.exception.ExceptionDuplicity;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.IRegistryRepository;
import java.util.List;

public class RegisterService {

    private static final String MESSAGE_RESTRICTED_PLATES_THAT_START_WITH_THE_LETTER = "A";
    private static final String MESSAGE_DAYS_NOT_ALLOWED = "El vehiculo no esta autorizado a ingresar los dias Domingo y Lunes";
    private static final String MESSAGE_THERE_IS_NOT_CUPO = "En este momento no hay cupo para el vehiculo %S";
    private static final String MESSAGE_THIS_UNRESOLVED_VEHICLE = "El vehiculo no se encuentra en el parqueadero";
    private static final String MESSAGE_ALREADY_EXISTS_VEHICLE = "El vehiculo ya ha ingresado";

    private static final int VEHICLETYPE_MAXIMO_CAR = 20;
    private static final int VEHICLETYPE_MAXIMO_MOTORCYCLE = 10;

    // Inyeccion de dependencia por contructor
    private IRegistryRepository registryRepository;

    public RegisterService(IRegistryRepository registryRepository){
        this.registryRepository = registryRepository;
    }

    public void SetEntry(Registry registry){
        validatePrevious(registry);
        this.registryRepository.create(registry);
    }

    public long SetExit(String licensePlate){
        return 0;
    }

    public List<Registry> listRegister() {
        return registryRepository.list();
    }

    private void validatePrevious(Registry registry) {
        boolean exists = registryRepository.exists(registry);
        if(exists) {
            throw new ExceptionDuplicity(MESSAGE_ALREADY_EXISTS_VEHICLE);
        }
    }

}

