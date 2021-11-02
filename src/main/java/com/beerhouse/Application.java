package com.beerhouse;

import java.util.Arrays;

// import com.beerhouse.model.Beer;
// import com.beerhouse.repository.BeerRepository;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	// private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

	// @Bean
	// public CommandLineRunner demo(BeerRepository repository) {
	// 	return (args) -> {
	// 		// save a few customers
	// 		repository.save(new Beer(1, "name", "ingredients", "alcoholContent", (float) 20.36, "category"));

	// 		// fetch all customers
	// 		log.info("Beer found with findAll():");
	// 		log.info("-------------------------------");
	// 		for (Beer beer : repository.findAll()) {
	// 			log.info(beer.toString());
	// 		}
	// 		log.info("");

	// 		// fetch an individual beer by ID
	// 		Beer beer = repository.findById(1);
	// 		log.info("Beer found with findById(1L):");
	// 		log.info("--------------------------------");
	// 		log.info(beer.toString());
	// 		log.info("");

	// 		log.info("");
	// 	};
	// }
}