package com.isolisduran.springboot;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.WildcardType;

@SpringBootApplication
@EnableSwagger2
public class SpringbootSpringfoxApplication {

	@Autowired
	private TypeResolver typeResolver;
	
	@Value("${app.version}")
	private String appVersion;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootSpringfoxApplication.class, args);
	}
	
	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors
						.basePackage("com.isolisduran.springboot.controller"))
					.paths(PathSelectors.regex("/api/.*"))
					.build()
				.pathMapping("/")
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(
						AlternateTypeRules.newRule(typeResolver.resolve(DeferredResult.class,
												   typeResolver.resolve(ResponseEntity.class,WildcardType.class)), 
												   typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {

		return new ApiInfo("API Title",
						   "API Description",
						   appVersion, 
						   "urn:tos", 
						   new Contact("API Contact Name", "http://www.none.com", "test@test.com"), 
						   "API License",
						   "http://www.api-license-url.com");
	}
	
}
