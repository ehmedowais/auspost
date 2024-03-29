package com.auspost.codingtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.auspost.codingtest"))
				.paths(regex("/auspost.*")).build();
	}
	@SuppressWarnings("unchecked")
	private Predicate<String> postPaths() {
		return or(regex("auspost/suburbs"),regex("auspost/postcodes"),regex("auspost/locations/.*"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("JavaInUse API")
				.description("JavaInUse API reference for developers")
				.termsOfServiceUrl("http://ausposttest.com.au")
				.contact("ehmedowais@yahoo.com").license("JavaInUse License")
				.licenseUrl("ehmedowais@yahoo.com").version("1.0").build();
	}

}