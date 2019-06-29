package co.com.ceiba.parking.parking.domain.repository;

import co.com.ceiba.parking.parking.domain.model.Registry;

import java.util.List;

public interface IPortRegistryRepository {

    /**
     *Registra el ingreso de un vehiculo
     *
     * @param  Objeto que tiene la informacion del registro a crear
     */
     void saveRegistro(Registry registry);

    /**
     * Permite listar los registros de vehiculos
     *
     * @return
     */
     List<Registry> list();

    /**
     * Permite contar la lista de vehiculos de un tipo
     *
     * @param String con el tipo de vehicluo
     * @return int con la candida de vehiculo
     */
     int countVehicleType(String vehicleType);

    /**
     * Permite buscar un vehiculo por su placa
     *  @param  Placa del vehiculo
     * @return Objeto de registro
     */
     Registry findByLicensePlate(String licensePlate);

    /**
     * Permite determinar si previamente se registro la placa
     * @param registry
     * @return si existe o no
     */
     boolean exists(Registry registry);

}
