package co.com.ceiba.parking.parking.domain.service;

import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.PortRegistryRepository;

import java.util.List;

public class RegisterListService {
    // Inyeccion de dependencia por contructor
    private PortRegistryRepository registryRepository;

    public RegisterListService(PortRegistryRepository registryRepository){
        this.registryRepository = registryRepository;
    }

    public List<Registry> listRegister() {
        return registryRepository.list();
    }
}
