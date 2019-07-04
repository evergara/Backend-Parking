package co.com.ceiba.parking.parking.domain.service.factory;

import co.com.ceiba.parking.parking.domain.model.Registry;

public class ChargeCar implements ChargeParking {

    private static final int HOURS_CASH_BY_DAY_CAR = 9;
    private static final int VALUE_PER_HOUR_CAR = 1000;
    private static final int VALUE_BY_DAY_CAR = 8000;
    private static final int MILISEGUND_PER_HOUR_CAR = 3600000;
    private static final int MILISEGUND_PER_MINUTE_CAR = 60000;
    private static final int MINIMUN_MINUTE_CAR = 10;
    private static final int HOUR_PER_DAY_CAR = 24;

    @Override
    public void setCharge(Registry registry) {
        long value;
        double milisegundCar = (registry.getDateDeparture().getTime() - registry.getDateArrival().getTime());
        double hourCar = (milisegundCar/MILISEGUND_PER_HOUR_CAR);
        double minuteCar = (milisegundCar/MILISEGUND_PER_MINUTE_CAR);
        long totalHourCar = Math.round(hourCar);
        long totalMinute = Math.round(minuteCar);
        int totalDay = (int)  totalHourCar / HOUR_PER_DAY_CAR;
        int  totalHourNewDay = (int) totalHourCar % HOUR_PER_DAY_CAR;


        if(totalHourCar < HOURS_CASH_BY_DAY_CAR){
            if((totalMinute >= MINIMUN_MINUTE_CAR) && (totalHourCar == 0)){
                value = VALUE_PER_HOUR_CAR;
            }else{
                value = totalHourCar * VALUE_PER_HOUR_CAR;
            }

        }else if(totalHourNewDay == 0 || (totalHourNewDay >= HOURS_CASH_BY_DAY_CAR && totalHourNewDay < HOUR_PER_DAY_CAR)){
             value = (VALUE_BY_DAY_CAR * (totalDay == 0 ? 1:totalDay));
        }else{
            value = ((VALUE_BY_DAY_CAR * totalDay) + (totalHourNewDay * VALUE_PER_HOUR_CAR));
        }
        registry.setValue(value);
    }
}
