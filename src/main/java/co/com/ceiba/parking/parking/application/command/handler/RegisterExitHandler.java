package co.com.ceiba.parking.parking.application.command.handler;
import co.com.ceiba.parking.parking.application.command.RegisterCommand;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.service.RegisterEntryService;
import co.com.ceiba.parking.parking.domain.service.RegisterExitService;

import java.util.Date;

public class RegisterExitHandler {

    private final RegisterExitService registerExitService;

    public RegisterExitHandler(RegisterExitService registerExitService){
        this.registerExitService = registerExitService;
    }

    public  void SetExit(String licensePlate){
        this.registerExitService.SetExit(licensePlate);
    }

}
