package com.def.accounts.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.def.accounts.controllers.QueueController;

@Configuration
@ComponentScan(basePackageClasses = QueueController.class)
public class WebConfig extends WebMvcConfigurationSupport {

/*	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {

		MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJacksonHttpMessageConverter.setSupportedMediaTypes(Arrays
				.asList(MediaType.APPLICATION_JSON));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		mappingJacksonHttpMessageConverter.getObjectMapper().setDateFormat(
				format);

		converters.add(mappingJacksonHttpMessageConverter);
	}*/
	
	 /*@Bean
	    public InternalResourceViewResolver resolver() {
	        InternalResourceViewResolver vr = new InternalResourceViewResolver();
	        vr.setPrefix("/WEB-INF/jsp/");
	        vr.setSuffix(".jsp");
	        return vr;
	    }*/

	@Override
	public void configureViewResolvers(final ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
