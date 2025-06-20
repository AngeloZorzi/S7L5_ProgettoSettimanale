package it.epicode.S7L5_ProgettoSettimanale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.ResourcePropertySource;

@SpringBootApplication
public class S7L5ProgettoSettimanaleApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(S7L5ProgettoSettimanaleApplication.class);
		app.addInitializers(context -> {
			try {
				ConfigurableEnvironment env = context.getEnvironment();
				env.getPropertySources().addFirst(
						new ResourcePropertySource(new FileSystemResource("./env.properties"))
				);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		app.run(args);
	}
}
