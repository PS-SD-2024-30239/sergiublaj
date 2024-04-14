package ro.ps.chefmgmtbackend.service.mail;

import ro.ps.chefmgmtbackend.dto.mail.MailRequestDTO;
import ro.ps.chefmgmtbackend.dto.mail.MailResponseDTO;

public interface MailService {

    MailResponseDTO sendMail(MailRequestDTO mailRequestDTO);
}
