package co.com.ceiba.parking.parking.domain.service.factory;

import co.com.ceiba.parking.parking.domain.model.Registry;

public class ChargeCar implements IChargeParking {

    private static final int HOURS_CASH_BY_DAY = 9;
    private static final int VALUE_PER_HOUR = 1000;
    private static final int VALUE_BY_DAY = 8000;
    private static final int MILISEGUND_PER_HOUR = 3600000;
    private static final int MILISEGUND_PER_MINUTE = 60000;
    private static final int MINIMUN_MINUTE = 10;
    private static final int HOUR_PER_DAY = 24;

    @Override
    public void setCharge(Registry registry) {
        long value;
        double milisegund = (registry.getDateDeparture().getTime() - registry.getDateArrival().getTime());
        double hour = (milisegund/MILISEGUND_PER_HOUR);
        double minute = (milisegund/MILISEGUND_PER_MINUTE);
        long totalHour = Math.round(hour);
        long totalMinute = Math.round(minute);
        int totalDay = (int)  totalHour / HOUR_PER_DAY;
        int  totalHourNewDay = (int) totalHour % HOUR_PER_DAY;


        if(totalHour < HOURS_CASH_BY_DAY){
            if((totalMinute >= MINIMUN_MINUTE) && (totalHour == 0)){
                value = VALUE_PER_HOUR;
            }else{
                value = totalHour * VALUE_PER_HOUR;
            }

        }else if(totalHourNewDay == 0 || (totalHourNewDay >= HOURS_CASH_BY_DAY && totalHourNewDay < HOUR_PER_DAY)){
             value = (VALUE_BY_DAY * (totalDay == 0 ? 1:totalDay));
        }else{
            value = ((VALUE_BY_DAY * totalDay) + (totalHourNewDay * VALUE_PER_HOUR));
        }
        registry.setValue(value);
    }
}
