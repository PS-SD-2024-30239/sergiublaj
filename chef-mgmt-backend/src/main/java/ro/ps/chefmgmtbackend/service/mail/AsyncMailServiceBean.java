package ro.ps.chefmgmtbackend.service.mail;

import org.springframework.jms.core.JmsTemplate;
import ro.ps.chefmgmtbackend.dto.mail.MailRequestDTO;
import ro.ps.chefmgmtbackend.dto.mail.MailResponseDTO;
import ro.ps.chefmgmtbackend.dto.mail.SendingStatus;
import ro.ps.chefmgmtbackend.jms.JmsMessageSenderBase;
import ro.ps.chefmgmtbackend.util.MailUtils;

public class AsyncMailServiceBean extends JmsMessageSenderBase<MailRequestDTO> implements MailService {

    public AsyncMailServiceBean(String destination, JmsTemplate jmsTemplate) {
        super(destination, jmsTemplate);
    }

    @Override
    public MailResponseDTO sendMail(MailRequestDTO mailRequestDTO) {
        SendingStatus status = sendMessage(mailRequestDTO);

        return MailUtils.getMailResponseDTO(mailRequestDTO, status);
    }
}

