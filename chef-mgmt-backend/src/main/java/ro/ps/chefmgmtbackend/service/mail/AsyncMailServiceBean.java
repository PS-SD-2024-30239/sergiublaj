package ro.ps.chefmgmtbackend.service.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import ro.ps.chefmgmtbackend.dto.mail.MailRequestDTO;
import ro.ps.chefmgmtbackend.dto.mail.MailResponseDTO;
import ro.ps.chefmgmtbackend.dto.mail.SendingStatus;
import ro.ps.chefmgmtbackend.jms.JmsMessageSenderBase;
import ro.ps.chefmgmtbackend.util.MailUtils;

public class AsyncMailServiceBean extends JmsMessageSenderBase<MailRequestDTO> implements MailService {

    public AsyncMailServiceBean(String destination, JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        super(destination, jmsTemplate, objectMapper);
    }

    @Override
    public MailResponseDTO sendMail(MailRequestDTO mailRequestDTO) {
        SendingStatus status = sendMessage(mailRequestDTO);

        return MailUtils.getMailResponseDTO(mailRequestDTO, status);
    }
}

