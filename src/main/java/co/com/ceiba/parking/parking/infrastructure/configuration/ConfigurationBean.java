package co.com.ceiba.parking.parking.infrastructure.configuration;
import co.com.ceiba.parking.parking.application.command.handler.RegisterEntryHandler;
import co.com.ceiba.parking.parking.application.command.handler.RegisterExitHandler;
import co.com.ceiba.parking.parking.application.consulta.RegisterListHandler;
import co.com.ceiba.parking.parking.domain.repository.PortRegistryRepository;
import co.com.ceiba.parking.parking.domain.service.RegisterEntryService;
import co.com.ceiba.parking.parking.domain.service.RegisterExitService;
import co.com.ceiba.parking.parking.domain.service.RegisterListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {
    @Bean
    public RegisterEntryHandler registerHandler(RegisterEntryService registerService) {
        return new RegisterEntryHandler(registerService);
    }

    @Bean
    public RegisterEntryService registerService(PortRegistryRepository registryRepository){
        return new RegisterEntryService(registryRepository);
    }

    @Bean
    public RegisterListService registerListService(PortRegistryRepository registryRepository){
        return new RegisterListService(registryRepository);
    }
    @Bean
    public RegisterListHandler registerListHandler(RegisterListService registerListService) {
        return new RegisterListHandler(registerListService);
    }

    @Bean
    public RegisterExitService registerExitService(PortRegistryRepository registryRepository){
        return new RegisterExitService(registryRepository);
    }
    @Bean
    public RegisterExitHandler registerExitHandler(RegisterExitService registerExitService) {
        return new RegisterExitHandler(registerExitService);
    }
}
