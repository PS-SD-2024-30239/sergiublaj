package ro.ps.chefmgmtbackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import ro.ps.chefmgmtbackend.mapper.ChefMapper;
import ro.ps.chefmgmtbackend.mapper.UserMapper;
import ro.ps.chefmgmtbackend.repository.ChefRepository;
import ro.ps.chefmgmtbackend.repository.UserRepository;
import ro.ps.chefmgmtbackend.service.chef.ChefService;
import ro.ps.chefmgmtbackend.service.chef.ChefServiceBean;
import ro.ps.chefmgmtbackend.service.mail.AsyncMailServiceBean;
import ro.ps.chefmgmtbackend.service.mail.MailService;
import ro.ps.chefmgmtbackend.service.mail.SyncMailServiceBean;
import ro.ps.chefmgmtbackend.service.user.UserService;
import ro.ps.chefmgmtbackend.service.user.UserServiceBean;

@EnableJms
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

    @Bean
    public UserService userServiceBean(
            UserRepository userRepository,
            UserMapper userMapper
    ) {
        return new UserServiceBean(userRepository, userMapper);
    }

    @Bean
    public MailService syncMailServiceBean(
            @Value("${mail-sender-app.url}") String url,
            RestTemplateBuilder restTemplateBuilder
    ) {
        return new SyncMailServiceBean(url, restTemplateBuilder.build());
    }

    @Bean
    public MailService asyncMailServiceBean(
            @Value("${queues.async-mail-sender-request}") String destination,
            JmsTemplate jmsTemplate,
            ObjectMapper objectMapper
    ) {
        return new AsyncMailServiceBean(destination, jmsTemplate, objectMapper);
    }
}
