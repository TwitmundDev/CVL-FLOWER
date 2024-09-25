package fr.twitmund.cvlapi;

import fr.twitmund.cvlapi.db.entity.CustomerEntity;
import fr.twitmund.cvlapi.db.repository.CustomerRepository;
import fr.twitmund.cvlapi.db.service.CustomerService;
import fr.twitmund.cvlapi.utils.DbError;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CvlApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvlApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository,
                             CustomerService customerService) {
        return args -> {
            CustomerEntity entity = new CustomerEntity(
                    "Surname",
                    "Name",
                    "Class",
                    "TargetSurname",
                    "TargetName",
                    "TargetClass",
                    "Message",
                    3,
                    "Customer",
                    false
            );
            if (customerService.createCustomer(entity) == DbError.Success){
                System.out.println("Customer created successfully");
            }

        };
    }

}
