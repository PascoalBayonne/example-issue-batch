package pt.bayonne.sensei.RemoteChunking.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.step.item.ChunkProcessor;
import org.springframework.batch.core.step.item.SimpleChunkProcessor;
import org.springframework.batch.integration.chunk.ChunkProcessorChunkHandler;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.batch.integration.chunk.RemoteChunkingWorkerBuilder;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.Message;
import pt.bayonne.sensei.RemoteChunking.dto.ClientDTO;

import java.nio.charset.StandardCharsets;

@Profile("!manager")
@Slf4j
@EnableBatchProcessing
@EnableBatchIntegration
@Configuration
@RequiredArgsConstructor
@EnableBinding(MessageChannelIntegration.class)
public class WorkerJobConfig {

    private final MessageChannelIntegration messageChannel;
    private final RecordProcessor recordProcessor;

    @Bean
    public RemoteChunkingWorkerBuilder<Object,Object> remoteChunkingWorker(){
        return new RemoteChunkingWorkerBuilder<>();
    }
//
    @Bean
    public ItemWriter<Object> writer(){
        return items -> items.forEach(item-> log.info("Item writer: {}",item));
    }


//    @Bean
//    public IntegrationFlow workerFlow() {
//        return this.remoteChunkingWorker()
//                .itemProcessor(recordProcessor)
//                .itemWriter(writer())
//                .inputChannel(requests()) // requests received from the manager
//                 //.outputChannel(replies()) // replies sent to the manager
//                .build();
//    }

//    @Bean
//   public RecordMessageConverter messageConverter() {  return new StringJsonMessageConverter();  }


    @Bean
    public QueueChannel requests() {
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow inboundFlow() {
        return IntegrationFlows
                .from(messageChannel.clientRequests())
                .channel(requests())
                .log()
                .transform(s->{
                    var some = s;

                    return some;
                })
                .get();
    }

    public ChunkRequest<ClientDTO> compute(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ChunkDeserializer chunkDeserializer = new ChunkDeserializer();
           Object ob = chunkDeserializer.deserialize(null,json.getBytes(StandardCharsets.UTF_8));
          return  mapper.readValue(json.getBytes(StandardCharsets.UTF_8), ChunkRequest.class);
        } catch (Exception e) {

            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public QueueChannel replies() {
        return new QueueChannel();
    }

//    @Bean
//    public IntegrationFlow outboundFlow() {
//        return IntegrationFlows
//                .from(replies())
//                .channel(messageChannel.clientReplies())
//                .get();
//    }

//    @Bean
//    @ServiceActivator(inputChannel = "requests", outputChannel = "replies")
//    public ChunkProcessorChunkHandler<Object> chunkProcessorChunkHandler() {
//        ChunkProcessor<Object> chunkProcessor
//                = new SimpleChunkProcessor<>(recordProcessor, writer());
//        ChunkProcessorChunkHandler<Object> chunkProcessorChunkHandler
//                = new ChunkProcessorChunkHandler<>();
//        chunkProcessorChunkHandler.setChunkProcessor(chunkProcessor);
//        return chunkProcessorChunkHandler;
//    }
}
