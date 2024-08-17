package ed.uopp.uoppemailsender.service.impl;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultEmailServiceTest {

    private static final String EMAIL_ENCODING = "utf-8";
    private static final String EMAIL_FROM = "uopp@education.com";
    private static final String EMAIL_SUBJECT = "Opportunity notification";
    private static final String EMAIL_TO = "test@example.com";
    private static final String EMAIL_CONTENT = "Hello, this is a test email!";

    @Mock
    private JavaMailSender emailSender;
    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private DefaultEmailService testInstance;

    @BeforeEach
    void setUp() {
        when(emailSender.createMimeMessage()).thenReturn(mimeMessage);
        testInstance.opportunityEncoding = EMAIL_ENCODING;
        testInstance.opportunityNotificationFrom = EMAIL_FROM;
        testInstance.opportunityNotificationSubject = EMAIL_SUBJECT;
    }

    @Test
    void shouldSendEmailSuccessfully() throws Exception {
        testInstance.sendEmail(EMAIL_TO, EMAIL_CONTENT);

        verify(emailSender).send(mimeMessage);
        verify(emailSender).createMimeMessage();
    }

}