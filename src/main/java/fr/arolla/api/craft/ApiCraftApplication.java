package fr.arolla.api.craft;

import fr.arolla.api.craft.etablissement.domain.service.EtablissementService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiCraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCraftApplication.class, args);
	}

	@Bean
	public EtablissementService etablissementService() {
		return new EtablissementService();
	}
}
