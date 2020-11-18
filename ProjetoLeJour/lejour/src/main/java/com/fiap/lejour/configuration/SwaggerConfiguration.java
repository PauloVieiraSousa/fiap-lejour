package com.fiap.lejour.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	Contact contato = new Contact("Paulo Gabriel", "http://lejour.dashboard.com", "paulogabrielvieira@gmail.com");

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.fiap.lejour"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(new ApiInfoBuilder()
						.title("Lejour Api Documentação")
						.description("Esta é uma documentação interativa da Rest Api do Lejour Dashboard")
						.version("1.0")
						.contact(contato)
						.build()
						);
	}
}
