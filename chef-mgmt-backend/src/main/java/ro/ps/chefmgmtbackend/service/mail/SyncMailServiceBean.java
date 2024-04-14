package ro.ps.chefmgmtbackend.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import ro.ps.chefmgmtbackend.dto.mail.MailRequestDTO;
import ro.ps.chefmgmtbackend.dto.mail.MailResponseDTO;
import ro.ps.chefmgmtbackend.resttemplate.RestTemplateBase;

@Slf4j
public class SyncMailServiceBean extends RestTemplateBase<MailRequestDTO, MailResponseDTO> implements MailService {

    private final String url;

    public SyncMailServiceBean(String url, RestTemplate restTemplate) {
        super(restTemplate);
        this.url = url;
    }

    @Override
    public MailResponseDTO sendMail(MailRequestDTO mailRequestDTO) {
        return postForEntity(url, mailRequestDTO);
    }

    @Override
    public Class<MailResponseDTO> getResponseType() {
        return MailResponseDTO.class;
    }

    @Override
    public String getExceptionMessage(MailRequestDTO mailRequestDTO) {
        return String.format("%s ---> %s (FAIL)", mailRequestDTO.getFrom(), mailRequestDTO.getTo());
    }
}
