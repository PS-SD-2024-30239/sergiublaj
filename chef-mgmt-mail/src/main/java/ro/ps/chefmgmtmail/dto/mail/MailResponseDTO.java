package ro.ps.chefmgmtmail.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailResponseDTO {

    private String from;
    private String to;
    private SendingStatus status;
}
