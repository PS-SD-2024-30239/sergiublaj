package ro.ps.chefmgmtmail.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import ro.ps.chefmgmtmail.jms.MailMessageReceiver;
import ro.ps.chefmgmtmail.jms.MessageReceiver;
import ro.ps.chefmgmtmail.service.mail.MailService;
import ro.ps.chefmgmtmail.service.mail.MailServiceBean;

@Configuration
public class Config {

    @Bean
    public MailService mailService(JavaMailSender javaMailSender) {
        return new MailServiceBean(javaMailSender);
    }

    @Bean
    public MessageReceiver mailMessageReceiver(MailService mailService, ObjectMapper objectMapper) {
        return new MailMessageReceiver(mailService, objectMapper);
    }
}
