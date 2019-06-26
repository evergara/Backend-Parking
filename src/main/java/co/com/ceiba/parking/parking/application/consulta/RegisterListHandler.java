package co.com.ceiba.parking.parking.application.consulta;

import co.com.ceiba.parking.parking.application.command.RegisterCommand;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.IRegistryRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public class RegisterListHandler {

    private final IRegistryRepository registryRepository;

    public RegisterListHandler(IRegistryRepository registryRepository){
        this.registryRepository = registryRepository;
    }

    public List<Registry> listRegister(){
        return this.registryRepository.list();
    }
}
