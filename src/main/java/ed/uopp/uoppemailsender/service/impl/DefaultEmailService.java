package ed.uopp.uoppemailsender.service.impl;

import ed.uopp.uoppemailsender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultEmailService implements EmailService {

    @Value("${application.email.encoding}")
    protected String opportunityEncoding;
    @Value("${application.email.opportunity-notification.from}")
    protected String opportunityNotificationFrom;
    @Value("${application.email.opportunity-notification.subject}")
    protected String opportunityNotificationSubject;
    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(String emailTo, String emailContent) {
        log.info("Sending email");

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, opportunityEncoding);
            mimeMessageHelper.setTo(emailTo);
            mimeMessageHelper.setFrom(opportunityNotificationFrom);
            mimeMessageHelper.setSubject(opportunityNotificationSubject);
            mimeMessageHelper.setText(emailContent, true);
            emailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            log.error("Email was not send, reason:" + e.getMessage());
        }
    }

}
