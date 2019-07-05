package co.com.ceiba.parking.parking.infrastructure.mapper;

import co.com.ceiba.parking.parking.domain.model.Registry;
import co.com.ceiba.parking.parking.infrastructure.entity.RegistryEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistryMapper {

    public Registry convertToDomain(RegistryEntity registryEntity){

        return new Registry(registryEntity.getId(),registryEntity.getVehicleType(),registryEntity.getLicensePlate()
                    ,registryEntity.getDisplacement(), registryEntity.getDateArrival(), registryEntity.getDateDeparture()
                    ,registryEntity.getValue());

    }

    public RegistryEntity convertToEntity(Registry registry){
        return new RegistryEntity(registry.getId(),registry.getVehicleType(),registry.getLicensePlate()
                                                ,registry.getDisplacement(),registry.getDateArrival(),registry.getDateDeparture(), registry.getValue());
    }

    public List<Registry> listConvertToDomain(List<RegistryEntity> listRegistryEntity) {
        final List<Registry> listRegistry = new ArrayList<>();

        listRegistryEntity.forEach(registryEntity -> listRegistry.add(new Registry(registryEntity.getId()
                ,registryEntity.getVehicleType(),registryEntity.getLicensePlate()
                ,registryEntity.getDisplacement(),registryEntity.getDateArrival(),registryEntity.getDateDeparture()
                ,registryEntity.getValue())));

        return  listRegistry;
    }
}
