package pt.bayonne.sensei.RemoteChunking.worker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
@Profile("!manager")
public interface MessageChannelIntegration {
    @Input
    SubscribableChannel clientRequests();

    @Output
    MessageChannel clientReplies();
}
