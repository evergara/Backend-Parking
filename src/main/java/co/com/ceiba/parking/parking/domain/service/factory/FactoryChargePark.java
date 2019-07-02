package co.com.ceiba.parking.parking.domain.service.factory;

import co.com.ceiba.parking.parking.domain.exception.ExceptionVehicleTypeWrongDate;
import co.com.ceiba.parking.parking.domain.util.Parametrization;

public class FactoryChargePark {

    private FactoryChargePark(){}

    public static IChargeParking getInstance(String vehicleType) {
        if(vehicleType.equalsIgnoreCase(Parametrization.VEHICLETYPE_VALUE_CAR)){
            return  new ChargeCar();
        }
        else if(vehicleType.equalsIgnoreCase(Parametrization.VEHICLETYPE_VALUE_MOTORCYCLE) ){
            return  new ChargeMotorCycle();
        }
        else{
            throw new ExceptionVehicleTypeWrongDate(String.format(Parametrization.MESSAGE_VEHICLETYPE_WRONG_DATA, vehicleType));
        }
    }
}
