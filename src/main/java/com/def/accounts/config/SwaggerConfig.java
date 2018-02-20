package com.def.accounts.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
		public Docket NotificationApi() {
			return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
					.apiInfo(apiInfo()).select().paths(regex("/sendMail")).build();
		}
	 @Bean
	    public Docket Accountapi() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(regex("/account/*"))                          
	          .build();                                           
	    }
/*	 private Predicate<String> postPaths() {
			return (Predicate<String>) springfox.documentation.builders.PathSelectors.regex("/");
		}*/

		private ApiInfo apiInfo() {
			return new ApiInfoBuilder().title("Rest API")
					.description("Rest API reference for developers")
					.termsOfServiceUrl("http://javainuse.com")
					.contact("demo@gmail.com").license(" License")
					.licenseUrl("demo@gmail.com").version("1.0").build();
		}
		
}
