package com.musala.droneservice.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger {
	@Bean
	public Docket apiDocket() {

		Contact contact = new Contact("Musala", "https://www.musala.com/", "abdullahelbaz11@gmail.com");

		List<VendorExtension> vendorExtensions = new ArrayList<>();

		ApiInfo apiInfo = new ApiInfo("Drone Service Documentation", "This pages documents Service endpoints", "1.0",
				"https://www.musala.com/", contact, "", "", vendorExtensions);

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.musala.droneservice.controller"))
				.paths(PathSelectors.any())
				.build();

		return docket;

	}
}
