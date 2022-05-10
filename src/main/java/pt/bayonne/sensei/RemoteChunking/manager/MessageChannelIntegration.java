package pt.bayonne.sensei.RemoteChunking.manager;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageChannelIntegration {
    @Input
    SubscribableChannel clientReplies();

    @Output
    MessageChannel clientRequests();
}
