package pt.bayonne.sensei.RemoteChunking.job.config;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Collection;

@Configuration
@Profile("worker")
@ComponentScan(basePackages = {"pt.bayonne.sensei.RemoteChunking.worker"})
public class WorkerConfig {

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
        b.indentOutput(true).mixIn(JobExecution.class, WorkerConfig.JobExecutionMixin.class).mixIn(StepExecution.class, WorkerConfig.StepExecutionMixin.class);
        return b;
    }
}
