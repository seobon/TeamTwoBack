package TeamTwo.TeamTwoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeamTwoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamTwoProjectApplication.class, args);
	}

}
