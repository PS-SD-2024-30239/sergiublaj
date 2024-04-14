package ro.ps.chefmgmtmail.service.mail;

import ro.ps.chefmgmtmail.dto.mail.MailRequestDTO;
import ro.ps.chefmgmtmail.dto.mail.MailResponseDTO;

public interface MailService {

    MailResponseDTO sendMail(MailRequestDTO mailRequestDTO);
}
