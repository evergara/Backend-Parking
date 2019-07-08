package co.com.ceiba.parking.parking.integration;

import co.com.ceiba.parking.parking.application.command.RegisterCommand;
import co.com.ceiba.parking.parking.infrastructure.controller.ParkingController;
import co.com.ceiba.parking.parking.infrastructure.exception.ExceptionHandler;
import co.com.ceiba.parking.parking.testdatabuilder.RegisterCommandTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingControllerTest {

    @Autowired
    private ExceptionHandler exceptionHandler;
    @Autowired
    private ParkingController parkingController;
    private MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = MockMvcBuilders.standaloneSetup(parkingController, exceptionHandler).build();
    }

    @Test
    public  void listRegisterTest() throws Exception{
        // Arrange , Act y Assert
      mockMvc.perform(get("/parking").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getIntoVehicleTest() throws Exception {
        // Arrange
        RegisterCommandTestDataBuilder registerCommandTestDataBuilder = new RegisterCommandTestDataBuilder();
        RegisterCommand registerCommand = registerCommandTestDataBuilder.build();

        // Act y Assert
        mockMvc.perform(post("/parking").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(registerCommand))).andExpect(status().isOk());

    }

    @Test
    public void getOutVehicleTest() throws Exception{
        // Arrange
        String  licensePlate = "B125";
        // Act y Assert
        mockMvc.perform(put("/parking/" + licensePlate).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private String jsonToString(RegisterCommand json) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(json);
    }
}
