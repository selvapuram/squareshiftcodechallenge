package com.test.squareshift;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SquareshiftApplication {
    
    /**
     * API_VERSION
     */
    private static final String API_VERSION = "1.0.0";

	public static void main(String[] args) {
		SpringApplication.run(SquareshiftApplication.class, args);
	}
	
	/**
	   * Api.
	   * @return the docket
	   */
	  @Bean
	  public Docket api() {
	    Set<String> contentType = new HashSet<>();
	    contentType.add("application/json");
	    return new Docket(DocumentationType.SWAGGER_2).select()
	      .apis(RequestHandlerSelectors.basePackage("com.test.squareshift.web")).paths(PathSelectors.any()).build()
	      .produces(contentType)
	      .apiInfo(apiInfo());
	  }

	  /**
	   * @return The API Information for the seating service.
	   */
	  private static ApiInfo apiInfo() {
	    return new ApiInfo("Airplane Seating Service",
	      "creates seats and allocates passengers",
	      API_VERSION, null, null, null, null,
	      Collections.emptyList());
	  }

}
