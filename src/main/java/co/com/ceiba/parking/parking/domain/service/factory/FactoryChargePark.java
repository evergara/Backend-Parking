package co.com.ceiba.parking.parking.domain.service.factory;

import co.com.ceiba.parking.parking.domain.exception.ExceptionVehicleTypeWrongDate;
import co.com.ceiba.parking.parking.domain.util.RegistryDomainConstant;

public final class FactoryChargePark {

    private FactoryChargePark(){}

    public static ChargeParking getInstance(String vehicleType) {
        if(vehicleType.equalsIgnoreCase(RegistryDomainConstant.VEHICLETYPE_VALUE_CAR)){
            return  new ChargeCar();
        }
        else if(vehicleType.equalsIgnoreCase(RegistryDomainConstant.VEHICLETYPE_VALUE_MOTORCYCLE) ){
            return  new ChargeMotorCycle();
        }
        else{
            throw new ExceptionVehicleTypeWrongDate(String.format(RegistryDomainConstant.MESSAGE_VEHICLETYPE_WRONG_DATA, vehicleType));
        }
    }
}
