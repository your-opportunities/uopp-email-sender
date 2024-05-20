package ed.uopp.uoppemailsender.service.impl;

import ed.uopp.uoppemailsender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultEmailService implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(Object object) {
        log.info("Sending something {}", object);

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(object.toString(), true);
            mimeMessageHelper.setTo("test@mail.com");
            mimeMessageHelper.setSubject("Opportunity notification");
            mimeMessageHelper.setFrom("uopp@education.com");
            // TODO: configure mail sender
            emailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            log.error("not send");
        }
    }

}
