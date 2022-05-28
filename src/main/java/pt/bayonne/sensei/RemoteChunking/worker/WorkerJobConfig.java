package pt.bayonne.sensei.RemoteChunking.worker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.integration.chunk.RemoteChunkingWorkerBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import pt.bayonne.sensei.RemoteChunking.dto.ClientDTO;

@Profile("!manager")
@Slf4j
@EnableBatchProcessing
@EnableIntegration
@Configuration
@RequiredArgsConstructor
@EnableBinding(MessageChannelIntegration.class)
public class WorkerJobConfig {

    private final MessageChannelIntegration messageChannel;
    private final RecordProcessor recordProcessor;


    @Bean
    public RemoteChunkingWorkerBuilder<ClientDTO,ClientDTO> remoteChunkingWorkerBuilder(){
        return new RemoteChunkingWorkerBuilder<>();
    }


    @Bean
    public ItemWriter<ClientDTO> itemWriter() {
        return items -> {
            for (ClientDTO item : items) {
                log.debug("--> writing item {}", item);
            }
        };
    }


    @Bean
    public IntegrationFlow workerFlow() {
        return remoteChunkingWorkerBuilder()
                .inputChannel(messageChannel.clientRequests()) // requests received from the manager
                .itemProcessor(recordProcessor)
                .itemWriter(itemWriter())
                .outputChannel(messageChannel.clientReplies()) // replies sent to the manager
                .build();
    }

}
