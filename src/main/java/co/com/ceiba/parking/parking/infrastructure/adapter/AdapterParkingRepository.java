package co.com.ceiba.parking.parking.infrastructure.adapter;

import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.domain.repository.PortRegistryRepository;
import co.com.ceiba.parking.parking.infrastructure.entity.RegistryEntity;
import co.com.ceiba.parking.parking.infrastructure.repository.RepositoryParkingJPA;
import co.com.ceiba.parking.parking.infrastructure.mapper.RegistryMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdapterParkingRepository implements PortRegistryRepository {

    private RepositoryParkingJPA repositoryParkingJPA;
    private RegistryMapper registryMapper;

    public AdapterParkingRepository(RepositoryParkingJPA repositoryParkingJPA, RegistryMapper registryMapper){
        this.repositoryParkingJPA = repositoryParkingJPA;
        this.registryMapper = registryMapper;
    }

    @Override
    public Registry saveRegistro(Registry registry) {
        RegistryEntity registryEntity = repositoryParkingJPA.save(registryMapper.convertToEntity(registry));
        return registryMapper.convertToDomain(registryEntity);
    }

    @Override
    public List<Registry> list() {
        final List<RegistryEntity> listRegistryEntity = repositoryParkingJPA.listAll();
        return registryMapper.listConvertToDomain(listRegistryEntity);
    }

    @Override
    public int countVehicleType(String vehicleType) {
        return repositoryParkingJPA.countVehicleType(vehicleType);
    }

    @Override
    public Registry findByLicensePlate(String licensePlate) {
        RegistryEntity registryEntity = repositoryParkingJPA.findByLicensePlate(licensePlate);
        return registryEntity == null? null : registryMapper.convertToDomain(registryEntity);
    }

    @Override
    public boolean exists(Registry registry) {
        return repositoryParkingJPA.exists(registry.getLicensePlate());
    }
}
