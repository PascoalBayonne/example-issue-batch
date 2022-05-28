package pt.bayonne.sensei.RemoteChunking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pt.bayonne")
public class RemoteChunkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteChunkingApplication.class, args);
	}

}
