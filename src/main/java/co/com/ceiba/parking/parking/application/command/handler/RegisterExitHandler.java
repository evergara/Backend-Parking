package co.com.ceiba.parking.parking.application.command.handler;
import co.com.ceiba.parking.parking.domain.service.RegisterExitService;

public class RegisterExitHandler {

    private final RegisterExitService registerExitService;

    public RegisterExitHandler(RegisterExitService registerExitService){
        this.registerExitService = registerExitService;
    }

    public  void setExit(String licensePlate){
        this.registerExitService.SetExit(licensePlate);
    }

}
