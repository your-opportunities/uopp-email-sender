package ed.uopp.uoppemailsender.consumer;

import ed.uopp.uoppemailsender.data.mq.NotificationDTO;
import ed.uopp.uoppemailsender.processor.NotificationMessageProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationMessageConsumerTest {

    @InjectMocks
    private NotificationMessageConsumer notificationMessageConsumer;

    @Mock
    private NotificationMessageProcessor notificationMessageProcessor;

    @Mock
    private NotificationDTO notificationDTO;

    @Test
    void shouldProcessNotification() {
        notificationMessageConsumer.consume(notificationDTO);

        verify(notificationMessageProcessor).processNotification(notificationDTO);
        verifyNoMoreInteractions(notificationMessageProcessor);
    }

}
