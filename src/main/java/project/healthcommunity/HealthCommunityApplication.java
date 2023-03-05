package project.healthcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class HealthCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCommunityApplication.class, args);
	}
}

