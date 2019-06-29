package co.com.ceiba.parking.parking.domain.service;

import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.IPortRegistryRepository;

import java.util.List;

public class RegisterListService {
    // Inyeccion de dependencia por contructor
    private IPortRegistryRepository registryRepository;

    public RegisterListService(IPortRegistryRepository registryRepository){
        this.registryRepository = registryRepository;
    }

    public List<Registry> listRegister() {
        return registryRepository.list();
    }
}
