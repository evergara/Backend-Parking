package co.com.ceiba.parking.parking.application.consulta;

import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.service.RegisterListService;

import java.util.List;


public class RegisterListHandler {

    private final RegisterListService registerListService;

    public RegisterListHandler(RegisterListService registerListService){
        this.registerListService = registerListService;
    }

    public List<Registry> listRegister(){
        return this.registerListService.listRegister();
    }
}
