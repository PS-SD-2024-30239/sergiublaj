package ro.ps.chefmgmtbackend.util;

import lombok.experimental.UtilityClass;
import ro.ps.chefmgmtbackend.dto.mail.MailRequestDTO;
import ro.ps.chefmgmtbackend.dto.mail.MailResponseDTO;
import ro.ps.chefmgmtbackend.dto.mail.SendingStatus;

@UtilityClass
public class MailUtils {

    public MailResponseDTO getMailResponseDTO(MailRequestDTO mailRequestDTO, SendingStatus status) {
        return MailResponseDTO.builder()
                .from(mailRequestDTO.getFrom())
                .to(mailRequestDTO.getTo())
                .status(status)
                .build();
    }
}
