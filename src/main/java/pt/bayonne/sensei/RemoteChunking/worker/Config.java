package pt.bayonne.sensei.RemoteChunking.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
@Slf4j
public class Config {

    @Bean
    public JsonMessageConverter jsonMessageConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public KafkaListenerErrorHandler myTopicErrorHandler() {
        return (m, e) -> {
            log.error("Got an error {}", e.getMessage());
            return "some info about the failure";
        };
    }
}
