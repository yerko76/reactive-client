package com.example.customerclient;

import com.example.customerclient.domain.Customer;
import com.example.customerclient.domain.CustomerEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CustomerClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerClientApplication.class, args);
	}

	@Bean
    WebClient client(){
	    return WebClient.create();
    }

	@Bean
    CommandLineRunner demo(WebClient client){
	    return args ->{
            client.get()
                    .uri("http://localhost:8080/customer/all")
                    .exchange()
                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Customer.class))
                    .filter(customer -> customer.getFirstName().equalsIgnoreCase("maria"))
                    .subscribe(customer -> client.get()
                            .uri("http://localhost:8080/customer/{id}/events", customer.getId())
                            .exchange()
                            .flatMapMany(clientResponse -> clientResponse.bodyToFlux(CustomerEvent.class))
                            .subscribe(System.out::println));

        };
    }
}
