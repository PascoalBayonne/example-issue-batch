package pt.bayonne.sensei.RemoteChunking.job.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("worker")
@Slf4j
@ComponentScan(basePackages = {"pt.bayonne.sensei.RemoteChunking.worker"})
public class WorkerConfig {

}
