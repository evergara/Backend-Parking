package co.com.ceiba.parking.parking.infrastructure.controller;

import co.com.ceiba.parking.parking.application.command.RegisterCommand;
import co.com.ceiba.parking.parking.application.command.handler.RegisterEntryHandler;
import co.com.ceiba.parking.parking.application.command.handler.RegisterExitHandler;
import co.com.ceiba.parking.parking.application.consulta.RegisterListHandler;
import co.com.ceiba.parking.parking.domain.model.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ParkingController {

    private RegisterEntryHandler registerHandler;
    private RegisterExitHandler registerExitHandler;
    private RegisterListHandler registerListHandler;

    @Autowired
    public ParkingController(RegisterEntryHandler registerHandler, RegisterExitHandler registerExitHandler, RegisterListHandler registerListHandler){
        this.registerHandler = registerHandler;
        this.registerExitHandler = registerExitHandler;
        this.registerListHandler = registerListHandler;
    }

    @GetMapping
    public List<Registry> list() {
        return this.registerListHandler.listRegister();
    }

    @PostMapping
    public void setEntry(@RequestBody RegisterCommand registerCommand) {
        this.registerHandler.setEntry(registerCommand);
    }

    @PutMapping("/{licensePlate}")
    public void setOutPut(@PathVariable("licensePlate") String licensePlate) {
        this.registerExitHandler.setExit(licensePlate);
    }
}
