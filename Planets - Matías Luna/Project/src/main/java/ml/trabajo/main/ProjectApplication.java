package ml.trabajo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ml.trabajo.main.dtos.PlanetDTO;
import ml.trabajo.main.entities.Planet;
import ml.trabajo.main.services.PlanetService;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {		
		SpringApplication.run(ProjectApplication.class, args);	
	}

}

