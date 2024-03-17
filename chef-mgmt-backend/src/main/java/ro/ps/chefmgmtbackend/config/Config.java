package ro.ps.chefmgmtbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ps.chefmgmtbackend.mapper.ChefMapper;
import ro.ps.chefmgmtbackend.repository.ChefRepository;
import ro.ps.chefmgmtbackend.service.ChefService;
import ro.ps.chefmgmtbackend.service.ChefServiceBean;

@Configuration
public class Config {

    @Bean
    public ChefService chefServiceBean(
            ChefRepository chefRepository,
            ChefMapper chefMapper,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new ChefServiceBean(chefRepository, chefMapper, applicationName);
    }
}
