package pt.bayonne.sensei.RemoteChunking.job.config;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Collection;

@Configuration
@Profile("manager")
@Slf4j
@ComponentScan(basePackages = {"pt.bayonne.sensei.RemoteChunking.manager"})
public class ManagerConfig {

    public abstract class JobExecutionMixin {
        @JsonManagedReference
        private Collection<StepExecution> stepExecutions;
    }

    public abstract class StepExecutionMixin {
        @JsonBackReference
        private final JobExecution jobExecution = null;
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
        b.indentOutput(true).mixIn(JobExecution.class, JobExecutionMixin.class).mixIn(StepExecution.class, StepExecutionMixin.class);
        return b;
    }
}
