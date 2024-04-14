package ro.ps.chefmgmtmail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import ro.ps.chefmgmtmail.service.mail.MailService;
import ro.ps.chefmgmtmail.service.mail.MailServiceBean;

@Configuration
public class Config {

    @Bean
    public MailService mailService(JavaMailSender javaMailSender) {
        return new MailServiceBean(javaMailSender);
    }
}
