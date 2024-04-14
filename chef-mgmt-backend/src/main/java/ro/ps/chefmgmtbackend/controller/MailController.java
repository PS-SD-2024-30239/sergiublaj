package ro.ps.chefmgmtbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ps.chefmgmtbackend.dto.mail.MailRequestDTO;
import ro.ps.chefmgmtbackend.dto.mail.MailResponseDTO;
import ro.ps.chefmgmtbackend.service.mail.MailService;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/mail/v1")
@RequiredArgsConstructor
public class MailController {

    private final MailService syncMailServiceBean;
    private final MailService asyncMailServiceBean;

    @PostMapping("sync")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MailResponseDTO> sendSyncMail(@RequestBody MailRequestDTO mailRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        mailRequestDTO.setFrom(email);

        return new ResponseEntity<>(
                syncMailServiceBean.sendMail(mailRequestDTO),
                HttpStatus.OK
        );
    }

    @PostMapping("async")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MailResponseDTO> sendAsyncMail(@RequestBody MailRequestDTO mailRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        mailRequestDTO.setFrom(email);

        return new ResponseEntity<>(
                asyncMailServiceBean.sendMail(mailRequestDTO),
                HttpStatus.OK
        );
    }
}
