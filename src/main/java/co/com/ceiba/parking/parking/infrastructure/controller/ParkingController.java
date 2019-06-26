package co.com.ceiba.parking.parking.infrastructure.controller;

import co.com.ceiba.parking.parking.application.command.RegisterCommand;
import co.com.ceiba.parking.parking.application.command.handler.RegisterHandler;
import co.com.ceiba.parking.parking.application.consulta.RegisterListHandler;
import co.com.ceiba.parking.parking.domain.model.Registry;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Collection;

@RestController
@RequestMapping("/Parking")
@CrossOrigin("*")
public class ParkingController {

    private RegisterHandler registerHandler;
    private RegisterListHandler registerListHandler;

    @Autowired
    public ParkingController(RegisterHandler registerHandler,RegisterListHandler registerListHandler){
        this.registerHandler = registerHandler;
        this.registerListHandler = registerListHandler;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Registry> List() {
        return this.registerListHandler.listRegister();
    }

    @PostMapping
    public void created(@RequestBody RegisterCommand registerCommand) {
        this.registerHandler.SetEntry(registerCommand);
    }

}
