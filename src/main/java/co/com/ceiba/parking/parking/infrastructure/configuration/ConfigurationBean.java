package co.com.ceiba.parking.parking.infrastructure.configuration;
import co.com.ceiba.parking.parking.application.command.handler.RegisterHandler;
import co.com.ceiba.parking.parking.application.consulta.RegisterListHandler;
import co.com.ceiba.parking.parking.domain.repository.IRegistryRepository;
import co.com.ceiba.parking.parking.domain.service.RegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {
    @Bean
    public RegisterService registerService(IRegistryRepository registryRepository){
        return new RegisterService(registryRepository);
    }

    @Bean
    public RegisterHandler registerHandler(RegisterService registerService) {
        return new RegisterHandler(registerService);
    }

    @Bean
    public RegisterListHandler registerListHandler(IRegistryRepository registryRepository) {
        return new RegisterListHandler(registryRepository);
    }
}
