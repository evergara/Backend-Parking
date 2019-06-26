package co.com.ceiba.parking.parking.application.command.handler;
import co.com.ceiba.parking.parking.application.command.RegisterCommand;
import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.service.RegisterService;
import org.springframework.stereotype.Component;

public class RegisterHandler {

    private final RegisterService registerService;

    public RegisterHandler(RegisterService registerService){
        this.registerService = registerService;
    }

    public  void SetEntry(RegisterCommand registerCommand){
        this.registerService.SetEntry(new Registry(registerCommand.getId(), registerCommand.getVehicleType(), registerCommand.getLicensePlate()
                                                   ,registerCommand.getDisplacement(),registerCommand.getDateArrival()
                                                   ,registerCommand.getDateDeparture(),registerCommand.getValue()));
    }

}
