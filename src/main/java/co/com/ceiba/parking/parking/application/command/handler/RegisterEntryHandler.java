package co.com.ceiba.parking.parking.application.command.handler;
import co.com.ceiba.parking.parking.application.command.RegisterCommand;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.service.RegisterEntryService;

import java.util.Date;

public class RegisterEntryHandler {

    private final RegisterEntryService registerService;

    public RegisterEntryHandler(RegisterEntryService registerService){
        this.registerService = registerService;
    }

    public  void setEntry(RegisterCommand registerCommand){
        this.registerService.setEntry(new Registry(registerCommand.getId(), registerCommand.getVehicleType(), registerCommand.getLicensePlate()
                                                   ,registerCommand.getDisplacement(),new Date()
                                                   ,registerCommand.getDateDeparture(),registerCommand.getValue()));
    }

}
