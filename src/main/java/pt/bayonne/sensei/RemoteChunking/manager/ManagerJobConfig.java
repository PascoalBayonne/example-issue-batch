package pt.bayonne.sensei.RemoteChunking.manager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.batch.integration.chunk.RemoteChunkingManagerStepBuilderFactory;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.StringUtils;
import pt.bayonne.sensei.RemoteChunking.dto.ClientDTO;

import reactor.core.publisher.Sinks;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@Profile("!worker")
@Slf4j
@Configuration
@EnableBatchProcessing
@EnableBatchIntegration
@EnableBinding(MessageChannelIntegration.class)
@RequiredArgsConstructor
public class ManagerJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
   private final MessageChannelIntegration messageChannel;

    private final RemoteChunkingManagerStepBuilderFactory remoteChunkingManagerStepBuilderFactory;

    @Bean
    public Job clientJob(){
        return jobBuilderFactory.get("client-job")
                .incrementer(new RunIdIncrementer())
                .start(dispatchStep())
                .build();
    }



    @Bean
    public FlatFileItemReader<ClientDTO> reader(){
        return new FlatFileItemReaderBuilder<ClientDTO>()
                .resource(new ClassPathResource("/data/client.csv"))
                .saveState(false)
                .delimited()
                .delimiter(",")
                .names("taxNumber","fistName","lastName","email")
                .linesToSkip(1)
                .encoding(StandardCharsets.UTF_8.name())
                .strict(false)
                .targetType(ClientDTO.class)
                .build();
    }

    //TODO: MANAGER IS SENDING ITEMS INSTEAD OF CLIENT DTO


    public TaskletStep dispatchStep(){
       return this.remoteChunkingManagerStepBuilderFactory.get("dispatch-step")
                .<ClientDTO,ClientDTO>chunk(100)
                .reader(reader())
                .outputChannel(messageChannel.clientRequests())
                .inputChannel(replies()) //how to use the functional style here?
                .build();
    }


    @Bean
    public QueueChannel replies() {
        return new QueueChannel();
    }
    @Bean
    public IntegrationFlow integrationFlow(){
        return IntegrationFlows.from(messageChannel.clientReplies())
                .channel(replies())
                .get();
    }


}
