package ro.ps.chefmgmtbackend.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import ro.ps.chefmgmtbackend.dto.mail.SendingStatus;

@Slf4j
public abstract class JmsMessageSenderBase<Request> implements MessageSender<Request> {

    protected final String destination;
    protected final JmsTemplate jmsTemplate;

    protected JmsMessageSenderBase(String destination, JmsTemplate jmsTemplate) {
        this.destination = destination;
        this.jmsTemplate = jmsTemplate;
    }

    public SendingStatus sendMessage(Request request) {
        try {
            jmsTemplate.convertAndSend(destination, request);

            return SendingStatus.SUCCESS;
        } catch (Exception e) {
            log.error("Error while sending message");
        }

        return SendingStatus.FAILURE;
    }
}
