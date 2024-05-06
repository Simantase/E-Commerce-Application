package com.retail.ECommerceApplication.utility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
@Configuration
@OpenAPIDefinition
public class AplicationDoc {
	Contact contact() {
		return new Contact().name("SIMANTA SEN")
				.email("simantasen96@gmail.com")
				.url("http://localhost:5173/");
	}
	@Bean
	Info info() {
		return new Info()
				.title("Online Shopping Application")
				.description("An Advance Level Online shopping Application using Spring"
						+ " Boot RESTful APIs and React, dealing with several real world"
						+ " scenarios and workflows. The application equips the complete"
						+ " authentication and authorization workflows, Seller Dashboard Management,"
						+ " product catalog, cart and order processing and search operations based on"
						+ " several criteria's. ")
				.version("v1").contact(contact());
	}
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
}
