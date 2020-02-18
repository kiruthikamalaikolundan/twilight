package com.tw.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Ovitag Api List", "Your REST API for web app", "1.0", "Terms of service",
				new Contact("Pradeep", "http://techie-mixture.blogspot.com/", ""), "", "");
		return apiInfo;
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, "Authorization", "");
	}

	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("com.tw.controller")).build().apiInfo(apiInfo())
				.securitySchemes(Lists.newArrayList(apiKey())).apiInfo(metadata())//
				.useDefaultResponseMessages(false)//
				// .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token",
				// "Authorization", "Header"))))//
				// .tags(new Tag("users", "Operations about users"))//
				// .tags(new Tag("ping", "Just a ping"))//
				.genericModelSubstitutes(Optional.class);
	}
	
	private ApiInfo metadata() {
		return new ApiInfoBuilder()//
				.title("ovitag api list documentation")//
				.description("Api Documentation")//
				.version("1.0.0")//
				.license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
				.contact(new Contact(null, null, "trackerwave@gmail.com"))//
				.build();
	}

}
