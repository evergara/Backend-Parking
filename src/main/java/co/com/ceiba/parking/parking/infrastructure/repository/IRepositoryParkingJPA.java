package co.com.ceiba.parking.parking.infrastructure.repository;

import co.com.ceiba.parking.parking.infrastructure.entity.RegistryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IRepositoryParkingJPA extends CrudRepository<RegistryEntity, Integer> {

    @Query("SELECT COUNT(id) FROM RegistryEntity r WHERE r.vehicleType = :vehicleType AND r.dateDeparture IS NULL")
    int countVehicleType(@Param("vehicleType") String vehicleType);

    @Query("SELECT r FROM RegistryEntity r WHERE r.licensePlate = :licensePlate")
    RegistryEntity findByLicensePlate(@Param("licensePlate") String licensePlate);

    @Query("SELECT r FROM RegistryEntity r WHERE r.dateDeparture IS NULL")
    List<RegistryEntity> listAll();

    @Query("SELECT CASE WHEN COUNT(r.id) > 0 THEN true ELSE false END FROM RegistryEntity r WHERE r.licensePlate = :licensePlate AND r.dateDeparture IS NULL")
    boolean exists(@Param("licensePlate") String licensePlate);
}
